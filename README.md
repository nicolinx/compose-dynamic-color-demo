# Dynamic Color Demo

A Jetpack Compose demo of Material 3's **dynamic color**: every color on screen is generated
from the user's device wallpaper on Android 12+, with a static fallback theme below that.

### Same screen, different wallpapers

The screenshots below are the exact same screen and the exact same code, only the device
wallpaper changed between them:

| Wallpaper | App |
| --- | --- |
| <img src="docs/wallpaper-blue.png" width="160" alt="Blue wallpaper" /> | <img src="docs/screenshot-blue.png" width="160" alt="App themed from the blue wallpaper" /> |
| <img src="docs/wallpaper-sunset.png" width="160" alt="Sunset wallpaper" /> | <img src="docs/screenshot-sunset.png" width="160" alt="App themed from the sunset wallpaper" /> |
| <img src="docs/wallpaper-pink.png" width="160" alt="Pink wallpaper" /> | <img src="docs/screenshot-pink.png" width="160" alt="App themed from the pink wallpaper" /> |
| <img src="docs/wallpaper-green-approx.png" width="160" alt="Green wallpaper" /> | <img src="docs/screenshot-green.png" width="160" alt="App themed from the green wallpaper" /> |

## What is this?

Material 3 can derive an app's entire color scheme from the wallpaper the user has set on their
device (aka "Material You" / dynamic color). This project is a single-screen showcase built to
make that mapping obvious: a "Now Playing" media player mockup where the artwork gradient, seek
and volume sliders, play/pause button, favorite toggle, shuffle/repeat controls, and chip are all
pulled from different roles of the same generated `ColorScheme` (`primary`, `secondary`,
`tertiary`, their container variants, `surfaceVariant`, `outline`, etc.); nothing is hardcoded.
Change the device wallpaper, relaunch, and the whole UI re-themes itself.

On devices below Android 12 (API 31), where wallpaper-based color extraction isn't available, the
app falls back to a static, hand-picked Deep Purple Material 3 seed scheme.

## Features

- Automatic detection of Android 12+ via `dynamicLightColorScheme` / `dynamicDarkColorScheme`
- Deep Purple seed-based fallback `ColorScheme` for pre-Android 12 devices
- A media-player-style layout exercising a wide range of Material 3 components across different
  color roles: `Slider` (×2), `FilledIconToggleButton`, `IconToggleButton`, `FilledIconButton`,
  `AssistChip`, `Surface`, `HorizontalDivider`
- Light and dark `@Preview`s
- **Zero third-party dependencies**, built entirely on `androidx.compose.material3`

## Tech stack

- Kotlin
- Jetpack Compose
- Material 3 (`androidx.compose.material3`)
- minSdk 24 · target/compileSdk 37

## Project structure

```
app/src/main/java/com/example/composedynamiccolordemo/
├── MainActivity.kt          # Now Playing screen and its composables
└── ui/theme/
    ├── Color.kt              # Deep Purple fallback seed palette
    ├── Theme.kt               # Dynamic color detection + MaterialTheme setup
    └── Type.kt                # Typography
```

## Running it

```bash
git clone git@github.com:nicolinx/compose-dynamic-color-demo.git
cd compose-dynamic-color-demo
./gradlew installDebug
```

Or just open the project in Android Studio and run the `app` configuration.

To see dynamic color in action, run it on an **Android 12+ device or emulator**, change the
wallpaper (Settings → Wallpaper & style), and relaunch the app.

## Author

**Nicodemus Lin** — Software Engineer

[![Website](https://img.shields.io/badge/Website-nicolin.dev-4F46E5?style=for-the-badge&logo=globe&logoColor=white)](https://www.nicolin.dev/)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Nicodemus_Lin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nicodemus-lin/)
