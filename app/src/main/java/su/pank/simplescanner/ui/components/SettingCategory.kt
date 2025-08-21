package su.pank.simplescanner.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonShapes
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import su.pank.simplescanner.R
import su.pank.simplescanner.ui.theme.SimpleScannerTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun SettingsCategory(
    expanded: Boolean,
    onExpand: (Boolean) -> Unit,
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shapes: ButtonShapes = SettingsCategoryShapes.middle(),

    content: @Composable ColumnScope.() -> Unit,
) {
    // val rounding by animateDpAsState(if (expanded) 12.dp else 42.dp, animationSpec = MaterialTheme.motionScheme.defaultSpatialSpec())
    val animSpecFloat = MaterialTheme.motionScheme.slowSpatialSpec<Float>()
    val animSpecIntSize = MaterialTheme.motionScheme.slowSpatialSpec<IntSize>()
    val shapeAnimSpec = MaterialTheme.motionScheme.defaultEffectsSpec<Float>()
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val shape = shapeByInteraction(shapes, pressed, shapeAnimSpec)
    Card(
        modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .clickable(interactionSource = interactionSource) {
                        onExpand(!expanded)
                    }
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon()
                Spacer(Modifier.width(10.dp))
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleLarge) {
                    title()
                }
                Spacer(Modifier.weight(1f))
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)


            }
            AnimatedVisibility(
                expanded, enter = fadeIn(animSpecFloat) + expandVertically(animSpecIntSize),
                exit = fadeOut(animSpecFloat) + shrinkVertically(animSpecIntSize),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Column(Modifier) {
                    HorizontalDivider(Modifier.fillMaxWidth())
                    Box(modifier = Modifier.padding(top = 6.dp, bottom = 12.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            content()
                        }
                    }
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
object SettingsCategoryShapes {
    @Composable
    fun top() = ButtonShapes(
        MaterialTheme.shapes.large.copy(
            bottomEnd = CornerSize(
                8.dp
            ), bottomStart = CornerSize(8.dp)
        ), MaterialTheme.shapes.extraSmall
    )

    @Composable
    fun middle() = ButtonShapes(MaterialTheme.shapes.small, MaterialTheme.shapes.extraSmall)

    @Composable
    fun bottom() = ButtonShapes(
        MaterialTheme.shapes.large.copy(
            topEnd = CornerSize(
                8.dp
            ), topStart = CornerSize(8.dp)
        ), MaterialTheme.shapes.extraSmall
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
internal val ButtonShapes.hasRoundedCornerShapes: Boolean
    get() = shape is RoundedCornerShape && pressedShape is RoundedCornerShape

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun shapeByInteraction(
    shapes: ButtonShapes,
    pressed: Boolean,
    animationSpec: FiniteAnimationSpec<Float>,
): Shape {
    val shape =
        if (pressed) {
            shapes.pressedShape
        } else {
            shapes.shape
        }

    if (shapes.hasRoundedCornerShapes)
        return key(shapes) { rememberAnimatedShape(shape as RoundedCornerShape, animationSpec) }

    return shape
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun SettingsCategoryPreview() {
    SimpleScannerTheme {
        var expanded by remember { mutableStateOf(false) }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            SettingsCategory(
                expanded,
                { expanded = it },
                icon = { Icon(painterResource(R.drawable.scan), contentDescription = "Settings") },
                title = { Text("Settings") },
                content = {
                    Text(
                        LoremIpsum(words = 50).values.joinToString()
                    )
                },
                shapes = SettingsCategoryShapes.top()
            )
            SettingsCategory(
                expanded,
                { expanded = it },
                icon = { Icon(painterResource(R.drawable.scan), contentDescription = "Settings") },
                title = { Text("Settings") },
                content = {
                    Text(
                        LoremIpsum(words = 50).values.joinToString()
                    )
                },

                )
            SettingsCategory(
                expanded,
                { expanded = it },
                icon = { Icon(painterResource(R.drawable.scan), contentDescription = "Settings") },
                title = { Text("Settings") },
                content = {
                    Text(
                        LoremIpsum(words = 50).values.joinToString()
                    )
                },
                shapes = SettingsCategoryShapes.bottom()
            )
        }
    }
}
