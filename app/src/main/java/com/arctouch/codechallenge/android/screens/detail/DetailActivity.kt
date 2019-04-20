package com.arctouch.codechallenge.android.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.android.screens.base.MvvmActivity
import com.arctouch.codechallenge.injection.ActivityComponent

/**
 * Created by Rafael Decker on 2019-04-20.
 */

class DetailActivity : MvvmActivity<DetailViewModel>() {

    override val viewModel: DetailViewModel by lazy {
            viewModelFactory.get<DetailViewModel>(this)
    }

    private val movieId by lazy { intent.getIntExtra(ID_KEY, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setupViews()
        setupViewModel()
    }

    override fun assignDependencies() {
        ActivityComponent.init(this).inject(this)
    }

    private fun setupViews() {
        setupToolBar()
    }

    private fun setupToolBar() {
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewModel() {
        viewModel.run(movieId)
    }

    companion object {

        private const val ID_KEY = "ID_KEY"

        fun launch(context: Context, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ID_KEY, id)
            context.startActivity(intent)
        }

    }

}