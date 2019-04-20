package com.arctouch.codechallenge.android.screens.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.infra.api.TmdbApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
