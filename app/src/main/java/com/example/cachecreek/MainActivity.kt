package com.example.cachecreek

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.storage.StorageManager
import android.provider.Settings
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


const val CURRENT_STORAGE_SPACE = "com.example.cachecreek.STORAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var availSpace = File(getExternalFilesDir(null).toString()).freeSpace / (1000*1000)
        findViewById<TextView>(R.id.currentStorageValue).apply {
            text = """$availSpace MB"""
        }
        openSettingsAllFilesAccess(this)
    }

    private fun openNativeFileExplorer(activity: AppCompatActivity) {
        val intent = Intent(StorageManager.ACTION_MANAGE_STORAGE)
        activity.startActivity(intent)
    }

    private fun clearAppCacheFiles(activity: AppCompatActivity) {
        val intent = Intent(StorageManager.ACTION_CLEAR_APP_CACHE)
        activity.startActivity(intent)
    }

    private fun openSettingsAllFilesAccess(activity: AppCompatActivity) {
        val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
        activity.startActivity(intent)
    }


    fun clearCache(view: View) {
        var availSpace = File(getExternalFilesDir(null).toString()).freeSpace / (1000*1000)
//        openNativeFileExplorer(this)
//        clearAppCacheFiles(this)
        this.cacheDir.deleteRecursively()
//        deleteCache(this)
        val intentDisplay = Intent(this, DisplayClearedCacheActivity::class.java).apply {
            putExtra(CURRENT_STORAGE_SPACE, availSpace)
        }
        startActivity(intentDisplay)
    }

//    fun deleteCache(context: Context) {
//        try {
//            val dir: File = context.getCacheDir()
//            if (dir != null && dir.isDirectory) {
//                deleteDir(dir)
//            }
//        } catch (e: Exception) {
//        }
//    }
//
//    private fun deleteDir(dir: File?): Boolean {
//        if (dir != null && dir.isDirectory) {
//            val children = dir.list()
//            for (i in children.indices) {
//                val success = deleteDir(File(dir, children[i]))
//                if (!success) {
//                    return false
//                }
//            }
//        }
//        return dir!!.delete()
//    }








    /*
    * @RequiresPermission(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE)
    @SdkConstant(SdkConstant.SdkConstantType.ACTIVITY_INTENT_ACTION)
    public static final String ACTION_CLEAR_APP_CACHE = "android.os.storage.action.CLEAR_APP_CACHE";
    * */
}