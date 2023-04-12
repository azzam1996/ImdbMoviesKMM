# ImdbMoviesKMM
Android and iOS app built using Kotlin Multiplatform, displaying movies🎬 from Imdb website, built according to Clean Architecture
<p>
<image src="/assets/clean_arch.png">
</p>

<p align="center">
<b>KMM  ,  MVVM  ,  Clean Architecture  ,  Jetpack Components  ,  SOLID  ,  Live Data  ,  SwiftUI</b>
</p>

[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html) is a framework of Kotlin that allows for sharing of a single codebase for business logic across different platforms.

# App Structure
The project has 3 main modules:

### 1. androidApp
The Android project, UI is built using XML.

### 2. iOSApp
The iOS project, UI is built using SwiftUI.

### 3. shared
This module contains shared code that holds the domain and data layers.

<p>
<image src="/assets/basic-structure.png">
</p>

# Screenshoot

### Android
<p align="center">
<image src="/assets/android1.png" width="40%">
<image src="/assets/android2.png" width="40%">
</p>

### iOS
<p align="center">
<image src="/assets/ios1.png" width="40%">
<image src="/assets/ios2.png" width="40%">
</p>

# Libraries

## shared
* [**Coroutines**](https://github.com/Kotlin/kotlinx.coroutines): Kotlin corountines for background tasks
* [**Ktor**](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html): For Network Calls
* [**Kotlinx.serialization**](https://github.com/Kotlin/kotlinx.serialization) is used to
serialize feed data and store it locally
* [**SqlDelight**](https://github.com/cashapp/sqldelight): For Database

## Android
* [**Coroutines**](https://github.com/Kotlin/kotlinx.coroutines): Kotlin corountines for background tasks
* [**Timber**](https://github.com/JakeWharton/timber):  Logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [**Koin**](https://github.com/InsertKoinIO/koin): Lightweight dependency injection framework
* [**Coil**](https://github.com/coil-kt/coil): Image loading library


## iOS
* [**Kingfisher**](https://github.com/onevcat/Kingfisher): Image loading library

## Demo 


https://user-images.githubusercontent.com/59395628/231593909-8c2f0ba4-f643-4c90-9693-812487661408.mp4

