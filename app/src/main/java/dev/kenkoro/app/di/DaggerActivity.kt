package dev.kenkoro.app.di

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dagger.android.AndroidInjection
import javax.inject.Inject

internal open class DaggerActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> viewModels(
        noinline extrasProducer: (() -> CreationExtras)? = null,
    ): Lazy<VM> {
        return viewModels(extrasProducer) { viewModelFactory }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}
