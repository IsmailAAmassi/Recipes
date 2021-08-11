package com.ismailamassi.data.shared

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.ismailamassi.domain.Constants
import java.util.concurrent.Semaphore
import javax.inject.Inject

class SharedVariables @Inject constructor(mContext: Context) {

    private var mSharedPreference: SharedPreferences? = null


    private val mSharedPreferenceEditor: SharedPreferences.Editor
    private val mSemaphore: Semaphore

    init {
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(mContext)
        mSharedPreferenceEditor = mSharedPreference!!.edit()
        mSemaphore = Semaphore(
            Constants.MAX_PROCESS_AVAILABLE,
            true
        )
    }

    fun setString(flag: SharedFlag, value: String) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putString(flag.key, value)
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getString(flag: SharedFlag): String {
        var returnValue: String = SPF_STRING_NO_VALUE_FOUND
        try {
            mSemaphore.acquire()
            returnValue = mSharedPreference!!.getString(
                flag.key,
                SPF_STRING_NO_VALUE_FOUND
            ) ?: SPF_STRING_NO_VALUE_FOUND
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return returnValue
    }

    fun setStringList(flag: SharedFlag, value: List<String>) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putStringSet(flag.key, value.toSet())
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getStringList(flag: SharedFlag): List<String> {
        val defaultValue = SPF_STRING_LIST_NO_VALUE_FOUND
        return try {
            mSharedPreference?.let {
                mSemaphore.acquire()
                it.getStringSet(flag.key, defaultValue.toSet())?.toList()
            }.also {
                mSemaphore.release()
            } ?: defaultValue
        } catch (e: InterruptedException) {
            e.printStackTrace()
            defaultValue
        }
    }

    fun setInt(flag: SharedFlag, value: Int) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putInt(flag.key, value)
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getInt(flag: SharedFlag): Int {
        var returnValue = SPF_INT_NO_VALUE_FOUND
        try {
            mSemaphore.acquire()
            returnValue = mSharedPreference!!.getInt(
                flag.key,
                SPF_INT_NO_VALUE_FOUND
            )
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return returnValue
    }

    fun getLong(flag: SharedFlag): Long {
        var returnValue = SPF_LONG_NO_VALUE_FOUND
        try {
            mSemaphore.acquire()
            returnValue = mSharedPreference!!.getLong(
                flag.key,
                SPF_LONG_NO_VALUE_FOUND
            )
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return returnValue
    }

    fun setLong(flag: SharedFlag, value: Long) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putLong(flag.key, value)
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getDouble(flag: SharedFlag): Double {
        var returnValue: Float = SPF_FLOAT_NO_VALUE_FOUND.toFloat()
        try {
            mSemaphore.acquire()
            returnValue = mSharedPreference!!.getFloat(
                flag.key,
                SPF_FLOAT_NO_VALUE_FOUND.toFloat()
            )
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return returnValue.toDouble()
    }

    fun setDouble(flag: SharedFlag, value: Double) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putFloat(flag.key, value.toFloat())
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun setBoolean(flag: SharedFlag, value: Boolean) {
        try {
            mSemaphore.acquire()
            mSharedPreferenceEditor.putBoolean(flag.key, value)
            mSharedPreferenceEditor.commit()
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun getBoolean(flag: SharedFlag): Boolean {
        var returnValue = SPF_BOOLEAN_NO_VALUE_FOUND
        try {
            mSemaphore.acquire()
            returnValue = mSharedPreference!!.getBoolean(
                flag.key,
                SPF_BOOLEAN_NO_VALUE_FOUND
            )
            mSemaphore.release()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return returnValue
    }

    companion object AA {
        const val SPF_STRING_NO_VALUE_FOUND = "NULL"
        val SPF_STRING_LIST_NO_VALUE_FOUND = mutableListOf<String>()

        const val SPF_INT_NO_VALUE_FOUND = -1
        const val SPF_LONG_NO_VALUE_FOUND = -1L
        const val SPF_FLOAT_NO_VALUE_FOUND = -1.0
        const val SPF_BOOLEAN_NO_VALUE_FOUND = false
    }

}
