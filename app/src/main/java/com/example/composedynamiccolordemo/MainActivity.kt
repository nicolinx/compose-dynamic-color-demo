package com.example.composedynamiccolordemo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedynamiccolordemo.ui.theme.ComposeDynamicColorDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDynamicColorDemoTheme {
                ProfileDashboardScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileDashboardScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Dynamic Color Demo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* no-op: demo only */ },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            ) {
                Text(text = "+", style = MaterialTheme.typography.headlineSmall)
            }
        }
    ) { innerPadding ->
        DashboardContent(innerPadding)
    }
}

@Composable
private fun DashboardContent(innerPadding: PaddingValues) {
    val scrollState = rememberScrollState()

    var notificationsEnabled by rememberSaveable { mutableStateOf(true) }
    var rememberMe by rememberSaveable { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(0.6f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "This screen's colors are generated from your wallpaper on Android 12+.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(Modifier.height(20.dp))
        ProfileCard()

        Spacer(Modifier.height(24.dp))
        Text(
            text = "Preferences",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.height(8.dp))

        PreferenceRow(
            title = "Push notifications",
            subtitle = "Get notified about new activity",
        ) {
            Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
        }

        PreferenceRow(
            title = "Remember me",
            subtitle = "Stay signed in on this device",
        ) {
            Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
        }

        Spacer(Modifier.height(8.dp))
        Text(
            text = "Notification volume",
            style = MaterialTheme.typography.bodyLarge,
        )
        Slider(
            value = volume,
            onValueChange = { volume = it },
        )

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FilledTonalButton(
                onClick = { /* no-op: demo only */ },
                modifier = Modifier.weight(1f),
            ) {
                Text("Save changes")
            }
            OutlinedButton(
                onClick = { /* no-op: demo only */ },
                modifier = Modifier.weight(1f),
            ) {
                Text("Cancel")
            }
        }

        // Extra bottom space so content isn't hidden behind the FAB.
        Spacer(Modifier.height(80.dp))
    }
}

@Composable
private fun ProfileCard() {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarInitials(initials = "JD")
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Jamie Doe",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Android Developer",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                StatColumn(value = "128", label = "Projects", modifier = Modifier.weight(1f))
                StatColumn(value = "4.9", label = "Rating", modifier = Modifier.weight(1f))
                StatColumn(value = "2.1k", label = "Followers", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun AvatarInitials(initials: String) {
    Card(
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = initials,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun StatColumn(value: String, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun PreferenceRow(
    title: String,
    subtitle: String,
    trailing: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        trailing()
    }
}

@Preview(showBackground = true, name = "Light")
@Composable
fun ProfileDashboardPreviewLight() {
    ComposeDynamicColorDemoTheme(darkTheme = false, dynamicColor = false) {
        ProfileDashboardScreen()
    }
}

@Preview(
    showBackground = true,
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun ProfileDashboardPreviewDark() {
    ComposeDynamicColorDemoTheme(darkTheme = true, dynamicColor = false) {
        ProfileDashboardScreen()
    }
}
