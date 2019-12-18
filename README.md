![](https://i.imgur.com/oatemPt.png)

# WOLMO Cookbook - Android

This Cookbook contains recipes that demonstrate how to solve common problems while using **WOLMO** in you daily development. Each recipe **must** be self-contained in a package and will be used as a reference to help you build up an application with **WOLMO**.

The Cookbook will also be used as a playground to test new stuff out. That does not mean it will be messy, the kitchen must be kept clean.

## <a name="topic-contributing"></a> Current recipes

1.  <b>Google login:</b> Using Google's API to verify user authentication
2.  <b>Facebook login:</b> Using Facebook's API to verify user authentication
3.  <b>Instagram login:</b> Using Instagram's API to verify user authentication
4.  <b>Twitter login:</b> Using Twitter's API to verify user authentication
5.  <b>Room DB:</b> Saving, editing and removing data from a database without a back-end connection.
6.  <b>MP Chart:</b> Creating different charts using this library. Bar, line and pie charts are some of the examples included
7.  <b>Navigation Architecture Component:</b> A different way of navigating through Activities and Fragments.
8.  <b>Data Sync Recipe:</b> Implementation of observer that react to network request to fill information on screen.
9.  <b>Tests:</b>Testing different situation that could happened when dealing with a login.
10. <b>Koin: </b> An alternative of Dagger to inject dependencies.
11. <b>Notifications:</b> Implementation of local notifications with different designs (expandable text, picture, actions, etc).
12. <b>Graph QL: </b>A GET example using this network component.


## Initial Setup
To build project, `keystone.gradle` file will be needed (it won't be included in the repository due to security reasons). Default file could be created to run project (it is attached below), however, original file will be needed to deploy in Google Play Store. 


```
ext.release_keystore=file('keystore/debug.keystore')
ext.key_alias='androiddebugkey'
ext.key_password='android'
ext.store_password='android'
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
