![](https://i.imgur.com/oatemPt.png)

# WOLMO Cookbook - Android

This Cookbook contains recipes that demonstrate how to solve common problems while using **WOLMO** in you daily development. Each recipe **must** be self-contained in a package and will be used as a reference to help you build up an application with **WOLMO**.

The Cookbook will also be used as a playground to test new stuff out. That does not mean it will be messy, the kitchen must be kept clean.

## <a name="topic-contributing"></a> Current recipes

1.  <b>[Coroutines](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/coroutines):</b> Basic coroutines features examples and retrofit Coroutine vs. Callback example.
2.  <b>[Google login](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/googlelogin):</b> Using Google's API to verify user authentication.
3.  <b>[Facebook login](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/facebooklogin):</b> Using Facebook's API to verify user authentication.
4.  <b>[Instagram login](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/instagramlogin):</b> Using Instagram's API to verify user authentication.
5.  <b>[Twitter login](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/twitterlogin):</b> Using Twitter's API to verify user authentication.
6.  <b>[Room DB](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/room):</b> Saving, editing and removing data from a database without a back-end connection.
7.  <b>[MP Chart](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/mpchart):</b> Creating different charts using this library. Bar, line and pie charts are some of the examples included.
8.  <b>[Navigation Architecture Component](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/navigation):</b> A different way of navigating through Activities and Fragments.
9.  <b>[Data Sync Recipe](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/datasync):</b> Implementation of observer that react to network request to fill information on screen.
10. <b>[Tests](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/tests):</b>Testing different situation that could happened when dealing with a login.
11. <b>[Koin](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/koin): </b> An alternative of Dagger to inject dependencies.
12. <b>[Notifications](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/notifications):</b> Implementation of local notifications with different designs (expandable text, picture, actions, etc).
13. <b>[Graph QL](https://github.com/Wolox/wolmo-cookbook-android/tree/master/app/src/main/java/ar/com/wolox/android/cookbook/graphQl): </b>A GET example using this network component.


## Initial Setup
Setup the production keystore credentials: run scripts/keystore.sh from the project root directory or create app/keystore.gradle file manually:
```
ext.release_keystore=file('...')
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```

On the other hand, assets for project will be located in raw folder and warp.py script will create all drawable needed for all resolutions. For more information, you can check it's repository: https://github.com/Wolox/warp

## <a name="topic-contributing"></a> Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push your branch (`git push origin my-new-feature`)
5. Create a new Pull Request

## <a name="topic-about"></a> About

This project was created by [Emanuel Lamela](https://github.com/emalamela) and it is written and maintained by [Wolox](http://www.wolox.com.ar).

![Wolox](https://i.imgur.com/VuLMt3g.png)

## <a name="topic-license"></a> License

**WOLMO Cookbook** is available under the MIT [license](https://raw.githubusercontent.com/Wolox/wolmo-core-android/master/LICENSE.md).

    Copyright (c) Wolox S.A

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
