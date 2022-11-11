# android-serializable-path

Convert android graphics path to a JSON object and back.

## Using

1. Add the library to your project.

```groovy
dependencies {
    compile 'com.aureusapps.android:serializable-path:1.0.0'
}
```

2. Use the `SerialiablePath` wrapper class. This wrapper class can be replaced by your `Path` class.
   It records all the operations performed on the `Path` object and the operations are converted to
   a JSON object. Be aware that any operations that can concatenate such as transform are not
   simplified and are recorded as it is. But this method will save lot of space required for
   serialization.

```kotlin
val path = SerializablePath()
path.moveTo(0f, 0f)
path.lineTo(100f, 100f)
path.close()
val json = Json.encodeToString(path)
```

3. Use `PathSerializer` to convert path points to a JSON object. It uses `PathMeasure` to go through
   the path contours and record it's points. This method is error prone and may take more space than
   the above method.

```kotlin
val path = Path()
path.moveTo(0f, 0f)
path.lineTo(0f, 1f)
path.lineTo(1f, 1f)
path.lineTo(1f, 0f)
path.close()
val serializedPath = PathSerializer.serialize(path, error = 1f)
```

## Appreciate my work!

If you find this library useful, please consider buying me a coffee.

<a href="https://www.buymeacoffee.com/udarawanasinghe" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>