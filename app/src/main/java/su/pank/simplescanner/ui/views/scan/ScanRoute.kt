package su.pank.simplescanner.ui.views.scan

import android.net.Uri
import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.savedstate.SavedState
import kotlinx.serialization.json.Json
import su.pank.simplescanner.data.models.ScannedItem
import su.pank.simplescanner.utils.AnimatedScopeProvider
import kotlin.reflect.typeOf

internal val ScannedItemType = object : NavType<ScannedItem>(isNullableAllowed = true) {
    override fun put(
        bundle: SavedState,
        key: String,
        value: ScannedItem
    ) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun get(
        bundle: SavedState,
        key: String
    ): ScannedItem? =
        bundle.getString(key)?.let { parseValue(it) }


    override fun parseValue(value: String): ScannedItem {
        Log.d("ScannedItemType", "parseValue: $value")
        return Json.decodeFromString(Uri.decode(value))}

    override fun serializeAsValue(value: ScannedItem): String = Uri.encode(Json.encodeToString(value))
}

internal fun NavGraphBuilder.scanScreen() {


    composable<Scan>(typeMap = Scan.typeMap) { entry ->
        AnimatedScopeProvider {
            ScanRoute()
        }
    }
}