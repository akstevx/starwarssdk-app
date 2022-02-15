# Starwarssdk-app
A demo application to showcase modular SDK builders for better Android SDK (library) design.

## Summarized flow

1. To launch StarwarsSDK, call initialize function in StarwarsSdk object and pass in your activity: StarwarsSdk.initialize(activity = this)

2. After SDK is launched
    - You can view a random list of characters from starwars api https://swapi.dev/api/people/
    - You can view a random list of planets from starwars api https://swapi.dev/api/planets/
    - You can view a random list of movies from starwars api https://swapi.dev/api/films/


## App Requirements
- Android SDKv16 (Android 4.1 "Jelly Bean") - This is the first SDK version that includes
`TLSv1.2` which is required by our servers. Native app support for user devices older than
API 16 will not be available.


## Gradle Requirements
1. set wrapper property to https\://services.gradle.org/distributions/gradle-7.2-bin.zip
2. You should also add Java 8 support in your `build.gradle`:
    ```gradle
    android { 
        // ... Other configuration code 
        compileOptions {   
            sourceCompatibility JavaVersion.VERSION_1_8 
            targetCompatibility JavaVersion.VERSION_1_8 
        } 

        // For kotlin codebases, include
        kotlinOptions {
             jvmTarget = "1.8" 
        }
    }
3. minSdkVersion = 16, targetSdkVersion and compileSdkVersion = 31

## Prepare for use
To prepare for use, you must ensure that your app has internet permissions by making sure the `uses-permission` line below is present in the AndroidManifest.xml.
```xml
<uses-permission android:name="android.permission.INTERNET" />

## Building the example project
1. Clone the repository.
2. Import the project either using Android Studio/
3. Use hilt for dependency injection and annotate your application class with @HiltAndroidApp.
4. Build and run the project on your device or emulator.

## Improvements
- Persist data with database to avoid always making api requests to fetch data.
- Dependency invasion without using hilt or any di library that enforces user to make changes to their Application class.
- Error handling can be improved.
- Add button or swipe to refresh effect on activities displaying data to refresh the data being displayed on activity
- Restrict access to files inside Sdk.

## Blog
- [Companion blog article](https://www.raywenderlich.com/52-building-an-android-library-tutorial#toc-anchor-008)
- [Companion blog article](https://github.com/jacksoncheek/UserProfileSdk)



