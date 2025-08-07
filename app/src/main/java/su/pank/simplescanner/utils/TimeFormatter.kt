package su.pank.simplescanner.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.qualifiers.ApplicationContext
import su.pank.simplescanner.R
import javax.inject.Inject
import kotlin.time.Duration

class TimeFormatter(private val context: Context) {

    fun formatSeconds(count: Long): String {
        return context.resources.getQuantityString(R.plurals.seconds, count.toInt(), count)
    }

    fun formatMinutes(count: Long): String {
        return context.resources.getQuantityString(R.plurals.minutes, count.toInt(), count)
    }

    fun formatHours(count: Long): String {
        return context.resources.getQuantityString(R.plurals.hours, count.toInt(), count)
    }

    fun formatDays(count: Long): String {
        return context.resources.getQuantityString(R.plurals.days, count.toInt(), count)
    }

    fun formatMonths(count: Long): String {
        return context.resources.getQuantityString(R.plurals.months, count.toInt(), count)
    }

    fun formatDuration(duration: Duration): String{
        if (duration.inWholeSeconds < 60) {
            return formatSeconds(duration.inWholeSeconds)
        }
        if (duration.inWholeMinutes < 60) {
            return formatMinutes(duration.inWholeMinutes)
        }
        if (duration.inWholeHours < 24){
            return formatHours(duration.inWholeHours)
        }
        if (duration.inWholeDays < 30){
            return formatDays(duration.inWholeDays)
        }
        return formatMonths(duration.inWholeDays / 30L)
    }
}

@Composable
fun rememberTimeFormatter(): TimeFormatter{
    val context = LocalContext.current
    return remember {
        TimeFormatter(context)
    }
}