package com.example.iteventscheckin

import android.content.res.Resources
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        private val pattern : String = App.appInstance.getString(R.string.date_utils_pattern)

        private const val delimT : Char = 'T'

        private const val delimBlank : Char = ' '

        private val defString : String = App.appInstance.getString(R.string.events_no_date)

        fun getCurrentDate() : String {
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date = simpleDateFormat.format(Date())
            val result = dateToFormat(date)
            Log.d(
                "DEBUG",
                result
            )
            return result
        }

        private fun dateToFormat(date: String) : String {
            return date.replace(delimBlank, delimT, true)
        }

        fun dateFromFormat(date: String?) : String {
            if (date != null) {
                return date.substringBefore(delimT)
            } else {
                return defString
            }
        }

    }

}