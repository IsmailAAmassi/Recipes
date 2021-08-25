package com.ismailamassi.presentation.ui.more

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.utils.AppTheme
import timber.log.Timber

class MoreFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var reviewManger: ReviewManager
    private lateinit var reviewInfo: ReviewInfo


    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        activateReviewFlow()
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val packageName = requireActivity().packageName
        val keyAboutAppPreference = preferenceScreen.findPreference("key_about_app") as Preference?
        keyAboutAppPreference?.setOnPreferenceClickListener {
            Timber.tag(TAG).d("onCreatePreferences : ")
            findNavController().navigate(MoreFragmentDirections.actionGlobalNavAboutFragment())
            false
        }

        val keyRateAppPreference = preferenceScreen.findPreference("key_rate_app") as Preference?
        keyRateAppPreference?.setOnPreferenceClickListener {
            val task = reviewManger.launchReviewFlow(requireActivity(), reviewInfo)
            task.addOnCompleteListener {
                Timber.tag(TAG).d("onCreatePreferences : ${it.result}")
            }
            true
        }
    }

    private fun activateReviewFlow() {
        reviewManger = ReviewManagerFactory.create(requireContext())
        val reviewInfoTask = reviewManger.requestReviewFlow()
        reviewInfoTask.addOnCompleteListener {
            if (it.isSuccessful) {
                this.reviewInfo = it.result
            } else {
                Toast.makeText(requireContext(), "Not Completed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Timber.tag(TAG).d("onSharedPreferenceChanged : key $key")
        when (key) {
            "key_app_theme" -> {
                val themeValue = sharedPreferences?.getString(key, "") ?: ""
                Timber.tag(TAG).d("onSharedPreferenceChanged : themeValue $themeValue")
                val theme = AppTheme.getThemeByLabel(themeValue)
                (requireActivity() as MainActivity).changeAppTheme(theme,true)

            }
        }
    }

    companion object {
        private const val TAG = "MoreFragment"
    }
}