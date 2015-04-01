# Android CustomButton v1.0.0

This is an Android library. When we use the Buttons in Andrlid, we may write XML files to make the button become beautiful, it's very tired. You can use this library to easily make beautiful buttons. There is also provided a ripple effect button, which is so cool.

## Screenshots

<img src="/screenshots/screenshot_0.gif" alt="screenshot_0" title="screenshot_0" />

## Usage
### Step 1
####Gradle

```groovy
dependencies {
    compile 'com.github.whilu.CustomButton:library:1.0.0'
}
```

If it doesn't work, please send me an [email](mailto:lujun.byte@gmail.com).

### Step 2

First, add the namespace in the XML file:

```xml
xmlns:whilu="http://schemas.android.com/apk/res-auto"

```

Then, you can easily use it:

1.CustomImageButton, no need to write a `xx_selector.xml`, just add the value.

```xml
<com.github.whilu.library.CustomImageButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:scaleType="fitCenter"
    whilu:unpressed_src="@drawable/ic_btn_go_menu"
    whilu:pressed_src="@drawable/ic_btn_go_menu_pressed" />

```

* `whilu:unpressed_src` (the drawable before the ImageButton pressed)
* `whilu:pressed_src` (the drawable when the ImageButton pressed)

It looks like this:

<img src="/screenshots/screenshot_1.gif" alt="screenshot_1" title="screenshot_1" />  

2.CustomButton

```xml
<com.github.whilu.library.CustomButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Custom Button"
    whilu:shape_type="rectangle"
    whilu:round_radius="10dp"
    whilu:pressed_color="@color/pressed_color_s2"
    whilu:unpressed_color="@color/unpressed_color_s2" />

```

* `whilu:shape_type` (the shape of the Button, provided `circle` and `rectangle`)
* `whilu:round_radius` (the round corner radius of the Button)
* `whilu:pressed_color` (the color when the Button pressed)
* `whilu:unpressed_color` (the color before the Button pressed)

It looks like this:

No round corner  

<img src="/screenshots/screenshot_2.gif" alt="screenshot_2" title="screenshot_2" />

With round corner  

<img src="/screenshots/screenshot_3.gif" alt="screenshot_3" title="screenshot_3" />  

If you use CustomButton with `circle` shape,  you need to define an accurate value for `android:layout_width` and `android:layout_height`. Generally, they are equal.

It looks like this:  

<img src="/screenshots/screenshot_4.gif" alt="screenshot_4" title="screenshot_4" />  

3.CustomRippleButton

```xml
<com.github.whilu.library.CustomRippleButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Custom RippleButton"
    whilu:ripple_color="@color/unpressed_color_s2"
    whilu:ripple_duration="500"
    whilu:shape_type="rectangle"
    whilu:round_radius="10dp"
    whilu:unpressed_color="@color/unpressed_color_s" />

```

* `whilu:ripple_color` (the ripple effect color)
* `whilu:ripple_duration` (the ripple eccect duration time[ms])
* `whilu:shape_type` (the shape of the Button, provided `circle` and `rectangle`)
* `whilu:round_radius` (the round corner radius of the Button)
* `whilu:unpressed_color` (the color before the Button pressed)

It looks like this:  

No round corner  

<img src="/screenshots/screenshot_5.gif" alt="screenshot_5" title="screenshot_5" />  

With round corner  

<img src="/screenshots/screenshot_6.gif" alt="screenshot_6" title="screenshot_6" />  

If you use CustomRippleButton with `circle` shape,  you need to define an accurate value for `android:layout_width` and `android:layout_height`. Generally, they are equal.

It looks like this:  

<img src="/screenshots/screenshot_7.gif" alt="screenshot_7" title="screenshot_7" />


## SAMPLE

[sample apk](/sample/sample-release.apk)

## About

If you have any problems, please [email me](mailto:lujun.byte@gmail.com).


License
============

    Copyright 2015 whilu

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

