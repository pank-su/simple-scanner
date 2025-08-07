package su.pank.simplescanner.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }

}