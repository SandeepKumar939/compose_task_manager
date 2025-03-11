package com.example.task_manager.components

import android.content.Context
import android.content.Intent
import android.os.Process

fun restartApp(context: Context) {
    val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
    intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    Process.killProcess(Process.myPid()) // Kill the current process
}
