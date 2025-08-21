package su.pank.simplescanner.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun timeFlow(duration: Duration) = flow {
    while (true) {
        emit(Clock.System.now())
        delay(duration)
    }
}