# android-extended-path

Android graphics path with extended functionalities.

## Using

1. Add the library to your project.

```groovy
dependencies {
    compile 'com.aureusapps.android:extended-path:1.0.0'
}
```

2. Use the `ExtendedPath` wrapper class.

   This wrapper class can be replaced by your `Path` class. It records all the operations performed
   on the `Path` object and the operations can be converted to a JSON string.

```kotlin
val path = ExtendedPath()
path.moveTo(0f, 0f)
path.lineTo(100f, 100f)
path.close()
val json = path.toJson()
```

## Appreciate my work!

If you find this library useful, please consider buying me a coffee.

<a href="https://www.buymeacoffee.com/udarawanasinghe" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>