Android Gesture Detectors Framework
===================================

Introduction
------------

Since I was amazed Android has a ScaleGestureDetector since API level 8 but 
(still) no such thing as a RotateGestureDetector I decided to create this class 
myself. In the process I decided to create a small extendable framework for
GestureDetectors in general.


Tutorials
---------

#### Blog on using gesture detectors 

If you want to use the ScaleGestureDetector and/or the gesture detectors 
from this project, please read my tutorial: [code.almeros.com/android-multitouch-gesture-detectors](http://code.almeros.com/android-multitouch-gesture-detectors)

#### Example application

If you like to just dive into a working example, please check out 
[the example app (GitHub)](https://github.com/Almeros/android-gesture-detectors-example). 


Dependencies / Downloads
------------------------
You can always just download a zip from GitHub and add its contents to your project. But dependency  
management might be easier for you using Gradle and jitpack.io. Include the following in your project:

#### build.gradle root

    buildscript {       
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
        ...
    }
    
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    
#### build.gradle app

    dependencies {
        // Note: Change 'v1.0' to whatever release you need or 'master-SNAPSHOT' for a snapshot of 
        // the latest version on the master-branch.
        compile 'com.github.Almeros:android-gesture-detectors:v1.0'
    }


Extending / Contributing
------------------------

If you want to extend this framework with new gesture detectors please check out the existing classes
and read the comments/javadocs.

I hope you'll send a pull request with your FingerTwistGestureDetector or something ;)

If you help maintaining this framework, first of all thank you, and second, please consider also updating 
[the example app (GitHub)](https://github.com/Almeros/android-gesture-detectors-example) to make use
of your awesome new functionality/bugfixes!


Containments
------------

### RotateGestureDetector

Helps finding out the rotation angle between the line of two fingers (with the 
normal or previous finger positions)

### MoveGestureDetector

Convenience gesture detector to keep it easy moving an object around with one 
ore more fingers without losing the clean usage of the gesture detector pattern.

### ShoveGestureDetector

Detects a vertical two-finger shove. (If you place two fingers on screen with less than a 20 degree angle between them,
this will detact movement on the Y-axis.)

### ScaleGestureDetector (default Android)

This one is NOT in this framework, but is gesture detector that resides in the 
Android API since API level 8 (2.2).

### BaseGestureDetector (abstract)

Abstract class that every new gesture detector class should extend.

### TwoFingerGestureDetector (abstract)

Abstract class that extends the BaseGestureDetector and that every new gesture 
detector class for two finger operations should extend.

License
-------
This project is licensed with the 2-clause BSD license.
