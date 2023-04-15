<div align="center"> 
    <img 
        src = ".media/icon.png" 
        alt = "App Icon" 
        width = 100px 
        height = 100px
    >
</div>

<h1 align="center"> Material You Color Previewer </h1>

Use this simple app to view all available Material You colors. App will also show you the actual color ID, so you will be able to easily use it in your project.

Recently, more features had been added, so now you can copy full list of Monet Colors, as they ar set on your device. Available formats for copying the full palette of colors are the following: XML list for Android app development, JSON object, set of CSS variables and a C# class for Unity.

Moreover, the app now works on devices which run under API lower than 31 (Android 12). In such cases app will use a fallback set of colors (blue-ish Monet theme, as the one used in apps like Google Dialer on devices running API 30 and lower).

## Screenshots

<table align="center">
    <tr>
        <td>
            <img src=".media/01.png" />
        </td>
        <td>
            <img src=".media/02.png" />
        </td>
        <td>
           <img src=".media/03.png" />
        </td>
    </tr>
        <tr>
        <td>
            <img src=".media/11.png" />
        </td>
        <td>
            <img src=".media/12.png" />
        </td>
        <td>
           <img src=".media/13.png" />
        </td>
    </tr>
</table>

<h2 align = "center"> Screenshots (Android 11 and lower) </h2>

<table align="center">
    <tr>
        <td>
            <img src=".media/21.png" />
        </td>
        <td>
            <img src=".media/22.png" />
        </td>
        <td>
            <img src=".media/23.png" />
        </td>
    </tr>
        <tr>
        <td>
            <img src=".media/31.png" />
        </td>
        <td>
            <img src=".media/32.png" />
        </td>
        <td>
           <img src=".media/33.png" />
        </td>
    </tr>
</table>

## Known issues

When changing the UI mode on your device (e.g. switching from light to dark theme) the app will most likely crash. It may crash in some cases when you are changing its activity state (e.g. returning to the app from a home screen or "Recent applications" screen). The cause of these crashes is the [android-maskable-layout library](https://github.com/Smooth-E/android-maskable-layout) which I am using to cut inner UI elements of rounded-cornered dialogs and scrollable views. Since this library is very outdated (last update in 2020, migrated to AndroidX by me in early spring 2023) I am planning to either replace it with Android's new `ShapeableImageView` where possible or patch the existing library further to resolve mentioned crashes. Stay tuned for the next release!

## Licensing and resources

Monet Color Previewer is licensed under the [BSD 2-Clause License](./LICENSE). You can use it freely, but I do not give any warranties. Also, if you find this app useful, it will be nice of you to share it with others and not forget to give me a credit.

This project also uses external resources and libraries: 
- [JetBrains Mono Font](https://www.jetbrains.com/lp/mono/) 
- [Google Material Icons](https://fonts.google.com/icons?icon.style=Two+tone&icon.set=Material+Icons)
- [android-maskable-layout](https://github.com/Smooth-E/android-maskable-layout)
- [Google Material](https://github.com/material-components/material-components-android)
- [AndroidX Libraries](https://github.com/androidx/androidx)
