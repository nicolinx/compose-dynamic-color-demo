package com.example.composedynamiccolordemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedynamiccolordemo.ui.theme.ComposeDynamicColorDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDynamicColorDemoTheme {
                NowPlayingScreen()
            }
        }
    }
}

@Composable
fun NowPlayingScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
        ) {
            PlayerTopBar()

            Spacer(Modifier.height(4.dp))
            Text(
                text = "PLAYING FROM PLAYLIST",
                style = MaterialTheme.typography.labelSmall,
                letterSpacing = 1.5.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Late Night Focus",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.height(28.dp))
            AlbumArtwork()

            Spacer(Modifier.height(32.dp))
            TrackHeader()

            Spacer(Modifier.height(20.dp))
            SeekSection()

            Spacer(Modifier.height(12.dp))
            TransportControls()

            Spacer(Modifier.height(28.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

            Spacer(Modifier.height(16.dp))
            VolumeRow()

            Spacer(Modifier.height(16.dp))
            AssistChip(
                onClick = { /* no-op: demo only */ },
                label = { Text("Casting to Living Room TV") },
                leadingIcon = { Text("▶︎", color = MaterialTheme.colorScheme.primary) },
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun PlayerTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /* no-op: demo only */ }) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Text(
            text = "Now Playing",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        IconButton(onClick = { /* no-op: demo only */ }) {
            Text(
                text = "⋯",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun AlbumArtwork() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        shape = RoundedCornerShape(28.dp),
        tonalElevation = 6.dp,
        shadowElevation = 16.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.tertiaryContainer,
                        )
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "♪",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Composable
private fun TrackHeader() {
    var isFavorite by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Midnight Drive",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "Nova Wells",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.width(12.dp))
        FilledIconToggleButton(
            checked = isFavorite,
            onCheckedChange = { isFavorite = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                checkedContainerColor = MaterialTheme.colorScheme.primary,
                checkedContentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            // ︎ forces the text (monochrome) glyph instead of a color emoji,
            // so the icon actually follows contentColor.
            Text(if (isFavorite) "♥︎" else "♡︎")
        }
    }
}

@Composable
private fun SeekSection() {
    var position by remember { mutableFloatStateOf(0.45f) }

    Slider(
        value = position,
        onValueChange = { position = it },
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = MaterialTheme.colorScheme.primary,
            inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "1:42",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "3:58",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun TransportControls() {
    var isShuffle by rememberSaveable { mutableStateOf(false) }
    var isRepeat by rememberSaveable { mutableStateOf(true) }
    var isPlaying by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlyphToggleButton(glyph = "⇄", checked = isShuffle, onCheckedChange = { isShuffle = it })

        IconButton(onClick = { /* no-op: demo only */ }) {
            Text("⏮", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)
        }

        FilledIconButton(
            onClick = { isPlaying = !isPlaying },
            modifier = Modifier.size(72.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            if (isPlaying) {
                // Drawn instead of using a "⏸" glyph: that codepoint only has a
                // fixed-color emoji form on most fonts and ignores contentColor.
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .size(width = 7.dp, height = 22.dp)
                                .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(2.dp))
                        )
                    }
                }
            } else {
                Text(text = "▶︎", style = MaterialTheme.typography.headlineMedium)
            }
        }

        IconButton(onClick = { /* no-op: demo only */ }) {
            Text("⏭", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onSurface)
        }

        GlyphToggleButton(glyph = "↻", checked = isRepeat, onCheckedChange = { isRepeat = it })
    }
}

@Composable
private fun GlyphToggleButton(
    glyph: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = IconButtonDefaults.iconToggleButtonColors(
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            checkedContentColor = MaterialTheme.colorScheme.secondary,
        ),
    ) {
        Text(glyph, style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
private fun VolumeRow() {
    var volume by remember { mutableFloatStateOf(0.7f) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "🔉",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Slider(
            value = volume,
            onValueChange = { volume = it },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.tertiary,
                activeTrackColor = MaterialTheme.colorScheme.tertiary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
        )
        Text(
            text = "🔊",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
fun NowPlayingPreviewLight() {
    ComposeDynamicColorDemoTheme(darkTheme = false, dynamicColor = false) {
        NowPlayingScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun NowPlayingPreviewDark() {
    ComposeDynamicColorDemoTheme(darkTheme = true, dynamicColor = false) {
        NowPlayingScreen()
    }
}
