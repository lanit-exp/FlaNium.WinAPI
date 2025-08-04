## Changelog


### 2.3.1 (2025-08-04)

Updating library versions:
  - java up to 11

  - com.google.code.gson:gson up to 2.13.1

  - org.seleniumhq.selenium:selenium-remote-driver up to 4.34.0

  - org.seleniumhq.selenium:selenium-api up to 4.34.0

  - org.apache.maven.plugins:maven-javadoc-plugin up to 3.11.2

  - org.apache.maven.plugins:maven-release-plugin up to 3.1.1

  - org.apache.maven.plugins:maven-compiler-plugin up to 3.14.0

  - org.apache.maven.plugins:maven-source-plugin up to 3.3.1

  - org.apache.maven.plugins:maven-gpg-plugin up to 3.2.8

  - and updating versions of other libraries...


### 2.3.0 (2025-01-09)

#### Enhancements
* Added setElementFocus method in DesktopElement class.


### 2.2.0 (2023-08-31)

#### Enhancements
* Added support for system variables in the application path
* Added fileOrDirectoryExists and deleteFileOrDirectory methods in FlaNiumDriver class.
* Added startApp method in FlaNiumDriver class and initDriverWithoutStartApp method in FlaNium class.

#### Bug fixes
* getAttribute method fixed


### 2.1.0 (2023-07-05)

#### Enhancements
* Added w3c standard support (selenium 4.10).


### 2.0.1 (2023-04-18)

#### Bug fixes
* Fix application path initialization error when running on UNIX OS (using FlaNium.initDriver() method). 


### 2.0.0-beta (2023-03-14)


* The library code has been refactored.
* Change selenium library version to 4.7.2
* Some of the methods of the FlaNiumDriver and DesktopElement classes have been moved.
* Added many new methods.
* Added simplified initialization.

Details in the Readme.md

### 1.6.0 (2022-02-12)

#### Breaking changes
* None

#### Enhancements
* Added responseTimeout parameter to DesktopOptions.
* Added TouchActions : Drag, Hold, Pinch, Rotate, Tap, Transition.

#### Bug fixes
* None


### 1.5.0 (2021-08-30)

#### Breaking changes
* None

#### Enhancements
* Added methods for saving a screenshot of elements that are not in the foreground (overlapped by other windows).

#### Bug fixes
* None


### 1.4.0 (2021-08-19)

#### Breaking changes
* None

#### Enhancements
* Added setClipboardText method to the FlaNiumDriver class.
* Added processName option to the DesktopOptions class.

#### Bug fixes
* None


### 1.3.0 (2021-08-11)

#### Breaking changes
* None

#### Enhancements
* Added methods to the FlaNiumDriver class:
  
        setKeyboardLayoutCode
        getKeyboardLayoutCode
        setKeyboardLayout
        getKeyboardLayout
        getClipboardText
        performKeyCombination

* Added methods to the DesktopElement class:

        mouseMove
        mouseClick
        mouseRightClick
        mouseDoubleClick

* Added CoordinateElement class.

#### Bug fixes
* None


### 1.2.0 (2021-06-21)

#### Breaking changes
  * None

#### Enhancements
  * Added sendChars method to the FlaNiumDriver class.
 
#### Bug fixes
  * Solving the problem with the impossibility of setting the FlaNiumDriverService port other than 9999


### 1.1.0 (2021-04-13)

#### Breaking changes
  * None

#### Enhancements
  * Added screenshot methods to the DesktopElement and FlaNiumDriver classes.
  * Added dragAndDrop methods to the DesktopElement and FlaNiumDriver classes.
  * Added getActiveWindow method to the FlaNiumDriver class.

#### Bug fixes
  * None
