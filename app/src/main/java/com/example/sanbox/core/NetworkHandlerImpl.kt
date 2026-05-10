package com.example.sanbox.core

import android.content.Context
import android.widget.Toast
import com.example.network.interceptor.NetworkHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkHandlerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : NetworkHandler {
    override fun showToast() {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, "lalala", Toast.LENGTH_SHORT).show()
        }
    }
}