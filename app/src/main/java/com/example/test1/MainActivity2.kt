package com.example.test1
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.*
class MainActivity2 : AppCompatActivity() {
    private lateinit var manager: LinearLayoutManager
    private lateinit var myAdapter: FilmAdapter
    private lateinit var recyclerView: RecyclerView
  private lateinit var progressBar:ProgressBar
  private lateinit var view:ViewModelUpdate
  private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        manager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
         recyclerView=findViewById(R.id.rec)
        recyclerView.layoutManager=manager
        view = ViewModelProvider(this@MainActivity2).get(ViewModelUpdate::class.java)
            progressBar=findViewById(R.id.progressBar)
    }
    override fun onStart() {
        super.onStart()
        runBlocking {
            launch {
                long()
            }}
    }
    override fun onResume() {
        super.onResume()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = recyclerView.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                        if (CheckEndScreen(visibleItemCount,totalItemCount,firstVisibleItemPosition)) {
                            loadMoreItems()
                        }}
            }
        })
    }
    fun loadMoreItems() {
        isLoading = true
        progressBar.visibility=View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            view.save()
            delay(3000)
            getMoreItems()
            view.plus()
        }
    }
    fun getMoreItems(){
        view.liveData1.observe(this@MainActivity2, Observer {
           myAdapter.addData(it)
           })
            isLoading = false
            progressBar.visibility=View.INVISIBLE
        }
    suspend fun long()  {
        delay(3000)
         view.liveData.observe(this, Observer {
             myAdapter = FilmAdapter(it)
            recyclerView.adapter = myAdapter
         })
    }
    fun CheckEndScreen(x: Int, y: Int, z: Int): Boolean {
        return (x + z) >= y && z >= 0
    }
}