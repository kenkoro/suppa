package dev.kenkoro.utils.binding

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LifecycleOwner
import dev.kenkoro.utils.flow.binding.bind
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.flow.MutableStateFlow

fun EditText.bindTextTwoWay(
    lifecycleOwner: LifecycleOwner,
    flow: MutableStateFlow<String>
): DisposableHandle {
    val readDisposable: DisposableHandle = flow.bind(lifecycleOwner) { value ->
        if (this.text.toString() == value) return@bind

        this.setText(value)
        this.setSelection(value.length)
    }

    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val str = s.toString()
            if (str == flow.value) return

            flow.value = str
        }

        override fun afterTextChanged(s: Editable?) = Unit
    }
    this.addTextChangedListener(watcher)

    return DisposableHandle {
        readDisposable.dispose()
        this.removeTextChangedListener(watcher)
    }
}
