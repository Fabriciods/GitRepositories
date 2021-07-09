package br.com.fao.gitshare.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import br.com.fao.gitshare.R
import br.com.fao.gitshare.createDialog
import br.com.fao.gitshare.createProgressDialog
import br.com.fao.gitshare.databinding.ActivityMainBinding
import br.com.fao.gitshare.hideSoftKeyboard
import br.com.fao.gitshare.presentation.MainViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val viewModel by viewModel<MainViewModel>()
    private val dialog by lazy { createProgressDialog() }
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.tbMain)
        binding.rvRepos.adapter = adapter


        viewModel.repos.observe(this) {
            when (it) {
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message).show()
                    }
                    dialog.dismiss()

                }
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        var searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.getRepoList(it)
        }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextSubmit: $newText")
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }
}