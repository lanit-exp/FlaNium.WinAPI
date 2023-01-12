## Changelog

### 2.0.0 (2023-01-12)

#### Breaking changes
* Change selenium library version to 4.7.2

#### Enhancements
* Added smoothDragAndDrop method.

#### Bug fixes
* None

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
