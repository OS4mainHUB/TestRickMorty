package com.example.rickmorty.presentation.ui.base

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast

import java.io.File

class DownloadImage(
    private val context: Context
) {
    fun saveOnDevice(url: String, fileName: String) {
        try {
            val downloadManager =
                context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val imageLink = Uri.parse(url)
            val request = DownloadManager.Request(imageLink)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setTitle(fileName)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + fileName
                )
            downloadManager.enqueue(request)
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка при сохранении", Toast.LENGTH_LONG).show()
        }
    }
}