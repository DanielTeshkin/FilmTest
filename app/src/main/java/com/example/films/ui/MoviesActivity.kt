package com.example.films.ui
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.films.ui.utils.MoviesAdapter
import com.example.films.ui.utils.MoviesLoaderStateAdapter
import com.example.test1.R
import com.example.test1.databinding.MoviesActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    private val viewModel:MoviesViewModel by lazy {ViewModelProvider(this)[MoviesViewModel::class.java]}
    private val binding:MoviesActivityBinding by lazy {MoviesActivityBinding.inflate(layoutInflater)  }
    private val adapter :MoviesAdapter by lazy ( LazyThreadSafetyMode.NONE ){MoviesAdapter(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
      initUI()


    }
   private fun initUI(){
       val recyclerView=binding.rvMovies
       recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter.withLoadStateHeaderAndFooter(header = MoviesLoaderStateAdapter(), footer=MoviesLoaderStateAdapter() )
        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
        adapter.addLoadStateListener { loadState->
            if (loadState.refresh==LoadState.Loading)
                binding.progressBar.visibility=View.VISIBLE
            else binding.progressBar.visibility=View.GONE
        }

    }



}