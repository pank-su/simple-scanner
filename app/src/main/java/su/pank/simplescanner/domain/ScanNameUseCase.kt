package su.pank.simplescanner.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import su.pank.simplescanner.R
import javax.inject.Inject

class ScanNameUseCase @Inject constructor(@ApplicationContext private val context: Context) {
    operator fun invoke(): String {
        val nouns = context.resources.getStringArray(R.array.scan_nouns)
        val adjectives = context.resources.getStringArray(R.array.scan_adjectives)

        val randomAdjective = adjectives.random()
        val randomNoun = nouns.random()

        return "$randomAdjective $randomNoun".replaceFirstChar { it.uppercase() }
    }

}