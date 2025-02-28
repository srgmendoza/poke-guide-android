package com.sm.core.commons

import android.util.Log
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent

fun Modifier.onFocusLogger(
    focusableName: String
): Modifier {
    return this.onFocusEvent { focusState ->
        if (focusState.isFocused) {
            Log.d("onFocusEvent", "$focusableName Focused")
        } else {
            Log.d("onFocusEvent", "$focusableName Unfocused")
        }
    }
}