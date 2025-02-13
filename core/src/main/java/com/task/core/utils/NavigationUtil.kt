package com.task.core.utils


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.core.R
import timber.log.Timber

object NavigationUtil {
    private const val tag = "NavigationUtil"
    fun Fragment.navigateTo(
        id: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = defaultNavOptions,
        extras: Navigator.Extras? = null
    ) {
        safeTaskHandler { findNavController().navigate(id, args, navOptions, extras) }
    }


    fun BottomSheetDialogFragment.navigateTo(
        id: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = defaultNavOptions,
        extras: Navigator.Extras? = null
    ) {
        safeTaskHandler { findNavController().navigate(id, args, navOptions, extras) }
    }

    private fun safeTaskHandler(task: () -> Unit) = try {
        task.invoke()
    } catch (t: Throwable) {
        Timber.tag(tag).e("$t")
    }

    private val defaultNavOptions by lazy {
        NavOptions.Builder()
            .setEnterAnim(R.anim.enter_from_right)
            .setExitAnim(R.anim.exit_to_left)
            .setPopEnterAnim(R.anim.enter_from_left)
            .setPopExitAnim(R.anim.exit_to_right)
            .build()
    }


}
