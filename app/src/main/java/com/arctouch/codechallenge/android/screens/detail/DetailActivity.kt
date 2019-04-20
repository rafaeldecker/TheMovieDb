package com.arctouch.codechallenge.android.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.android.screens.base.MvvmActivity
import com.arctouch.codechallenge.android.screens.base.ViewModelState
import com.arctouch.codechallenge.injection.ActivityComponent
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.include_error_layout.*

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class DetailActivity : MvvmActivity<DetailViewModel>() {

    override val viewModel: DetailViewModel by lazy {
            viewModelFactory.get<DetailViewModel>(this)
    }

    private val movieId by lazy { intent.getLongExtra(ID_KEY, 0) }

    private val adapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupViews()
        setupViewModel()
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    private fun setupViews() {
        setupToolBar()
        retryButton.setOnClickListener { viewModel.run(movieId) }
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun setupToolBar() {
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewModel() {
        viewModel.run(movieId)
    }

    override fun onViewModelStateChanged(state: ViewModelState) {
        when(state) {
            ViewModelState.Loading -> showLoadingState()
            is ViewModelState.Error -> showErrorState(state.throwable)
            is ViewModelState.Data<*> -> showData(state.data as DetailModel)
        }
    }

    private fun showData(data: DetailModel) {
        supportActionBar?.title = data.title
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.GONE
        data.backdropImageUrl?.let {
            Glide.with(this)
                .load(it)
                .into(backgroundImageView)
        }
        adapter.dataSource = data.items
    }

    private fun showLoadingState() {
        progressBar.visibility = View.VISIBLE
        errorLayout.visibility = View.GONE
    }

    private fun showErrorState(throwable: Throwable) {
        progressBar.visibility = View.GONE
        errorLayout.visibility = View.VISIBLE
    }

    companion object {

        private const val ID_KEY = "ID_KEY"

        fun launch(context: Context, id: Long) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ID_KEY, id)
            context.startActivity(intent)
        }

    }

}