package dev.kenkoro.feature.utils.di.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BoundFragment<VB : ViewBinding> : DaggerFragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding) { "Couldn't read the binding on creation" }

    protected abstract val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
