package com.timothy.iline

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

fun formatTime(time:Long):String{
    val now = System.currentTimeMillis()
    val minute = SimpleDateFormat("m", Locale.getDefault()).format(time).toIntOrNull() ?: 0
    val thisMinute = SimpleDateFormat("m", Locale.getDefault()).format(now).toIntOrNull() ?: 0
    return if (minute == thisMinute){
        "now"
    }else {
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)
    }
}

fun whenFormat(time:Long):String{
    val today_milli = System.currentTimeMillis()
    val day = SimpleDateFormat("dd", Locale.getDefault()).format(time).toIntOrNull() ?: 0
    val today = SimpleDateFormat("dd", Locale.getDefault()).format(today_milli).toIntOrNull() ?: 0
    val week = SimpleDateFormat("W", Locale.getDefault()).format(time)
    val thisWeek = SimpleDateFormat("W", Locale.getDefault()).format(today_milli)
    val month = SimpleDateFormat("MMM", Locale.getDefault()).format(time)
    val thisMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(today_milli)
    val year = SimpleDateFormat("yyyy", Locale.getDefault()).format(time)
    val thisYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(today_milli)
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(time)

    if(DateUtils.isToday(time)) {
        return "Today"
    }else if ((today - day).absoluteValue == 1) {
        return "Yesterday"
    }else if (week == thisWeek){
        return dayOfWeek
    } else {
        return "$day/$month/$year"
    }
}

fun lastSeen(time:Long):String{
    val today_milli = System.currentTimeMillis()
    val minute = SimpleDateFormat("m", Locale.getDefault()).format(time).toIntOrNull() ?: 0
    val thisMinute = SimpleDateFormat("m", Locale.getDefault()).format(today_milli).toIntOrNull() ?: 0
    val day = SimpleDateFormat("dd", Locale.getDefault()).format(time).toIntOrNull() ?: 0
    val today = SimpleDateFormat("dd", Locale.getDefault()).format(today_milli).toIntOrNull() ?: 0
    val week = SimpleDateFormat("W", Locale.getDefault()).format(time)
    val thisWeek = SimpleDateFormat("W", Locale.getDefault()).format(today_milli)
    val month = SimpleDateFormat("MMM", Locale.getDefault()).format(time)
    val thisMonth = SimpleDateFormat("MMM", Locale.getDefault()).format(today_milli)
    val year = SimpleDateFormat("yyyy", Locale.getDefault()).format(time)
    val thisYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(today_milli)
    val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(time)
    return if (minute == thisMinute){
        "now"
    } else if (DateUtils.isToday(time)){
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(time)
    }else if ((today - day).absoluteValue == 1) {
        "Yesterday"
    }else if (week == thisWeek){
        dayOfWeek
    } else if (month == thisMonth){
        "$dayOfWeek $day"
    }else if (year == thisYear) {
        "$day, $month"
    }else{
        "$day $month $year"
    }
}