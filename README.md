# KMP-ReactNative-Handshake

This project demonstrates how to integrate **Kotlin Multiplatform (KMP)** with **React Native**, enabling shared business logic across Android and iOS platforms while maintaining native UI components.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [1. Setting Up the Environment](#1-setting-up-the-environment)
  - [2. Connecting KMP with React Native](#2-connecting-kmp-with-react-native)
  - [3. Creating Bridges](#3-creating-bridges)
    - [A. Android Bridge](#a-android-bridge)
    - [B. iOS Bridge](#b-ios-bridge)
  - [4. Testing the Integration](#4-testing-the-integration)
- [Additional Resources](#additional-resources)
- [License](#license)

## Introduction

This repository provides a step-by-step guide to integrating Kotlin Multiplatform with React Native, facilitating code sharing between mobile platforms. By following this guide, developers can streamline their development process, reduce code duplication, and maintain native performance and appearance.

## Prerequisites

- **Node.js**: Ensure that Node.js is installed on your machine.
- **React Native CLI**: Install globally using `npm install -g react-native-cli`.
- **Android Studio**: For Android development and KMP project setup.
- **Xcode**: For iOS development.
- **Kotlin 1.5.30 or above**: Required for Kotlin Multiplatform projects.
- **Android KTS**: Update your React native's gradle from groovy to KTX and update plugins.

## Setup Instructions

### 1. Setting Up the Environment

0. open android folder inside the android studio, you may need to /uninstall and then install pods. to insall pods go to the ios folder.

1. **Initialize a React Native Project**:
   ```bash
   npx react-native init MyReactNativeApp
   cd MyReactNativeApp
   ```

2. **Create a Kotlin Multiplatform Project**:
   - Open Android Studio and create a new Kotlin Multiplatform Mobile project.
   - Define shared code in the `shared` module.

### 2. Connecting KMP with React Native

1. **Configure the Shared Module**:
   - For **Android**:
     - Ensure the `shared` module produces an `.aar` file.
     - Copy the `.aar` file to the React Native project's `android/libs` directory.
     - Modify `android/app/build.gradle` to include:
       ```groovy
       implementation fileTree(dir: 'libs', include: ['*.aar'])
       ```
   - For **iOS**:
     - Ensure the `shared` module produces a `.framework`.
     - Add the `.framework` to the Xcode project under the `Frameworks, Libraries, and Embedded Content` section.

### 3. Creating Bridges

#### A. Android Bridge

1. **Create a Native Module**:
   - Create `KMPBridgeModule.kt` in react native android module:
     ``` kotlin
     
     class KMPBridgeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
        override fun getName() = "KMPBridge"
        @ReactMethod
        fun launchKMPLoginScreen() {
           val intent = Intent(reactApplicationContext, MainActivityKMP::class.java)
           intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
           reactApplicationContext.startActivity(intent)
           println("launchKMPLoginScreen: ${getPlatform().name}")
           }
     }

     ```

3. **Register the Module**:
   - Create `KMPPackage.kt`:
     ```kotlin
     
     class KMPPackage: ReactPackage {
        override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
              val modules = ArrayList<NativeModule>()
              modules.add(KMPBridgeModule(reactContext))
              return modules
        }

         override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return Collections.emptyList()
       }
     }
     ```
   - Modify `MainApplication.java` and include `KMPPackage()`.

4. **Access in React Native**:
   - Create a JavaScript module:
     ```javascript
     <img width="650" alt="Screenshot 2025-01-09 at 10 10 22 AM" src="https://github.com/user-attachments/assets/61e4febf-2a14-49a5-997c-c2db5004eb84" />

     ```

#### B. iOS Bridge

1. **Create a Native Module**:
   - In Xcode, create `KMPBridgeModule.m`:
     ```objc
     #import <React/RCTBridgeModule.h>

     @interface RCT_EXTERN_MODULE(KMPBridge, NSObject)
     RCT_EXTERN_METHOD(launchKMPLoginScreen)
     @end

     ```
   - Implement in `KMPBridgeModule.swift`:
     ```swift

     import Foundation
     import SwiftUI 

      @objc(KMPBridge)
      class KMPBridge: NSObject {

       @objc
       func launchKMPLoginScreen() {
           DispatchQueue.main.async {
               if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                   let viewController = MainViewControllerKt.MainViewController()
                    .........
                    .........        
               } else {
                   print("Failed to find root view controller")
               }
           }
       }
   
       @objc
       static func requiresMainQueueSetup() -> Bool {
           // This determines if the module requires the main queue to initialize
           return true
       }
      }
       
     ```

### Now set the KMP Path inside the pods of React native IOS 
<img width="950" alt="Screenshot 2025-01-09 at 11 27 30 AM" src="https://github.com/user-attachments/assets/46b65006-fd31-4a95-86fb-1d93e6d168f2" />


### Then install the cocoa pods dependency inside the KMP module and set the paths accordingly 
<img width="902" alt="Screenshot 2025-01-09 at 11 28 42 AM" src="https://github.com/user-attachments/assets/73a396a0-8ae7-4fe1-a8f1-18efed9e7e2e" />

---

### Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/malikdawar/KMM-React-Native-Handshake.git
   ```

2. **Open the project in Visual Studio code**:
   - Open Visual Studio.
   - Click on `File` -> `Open` and navigate to the cloned repository.

3. **Build and Run**:
   - Open the terminal by pressing (Control + `)
   - Go to the project directory cd path to the project folder
   - To run android app write the command npm run android
   - To run the IOS app write the command npm run ios

### Usage

- There are Screens in this tiny project. 1st screen(Into) is developed in React Native
- The second screen(Login) is developed in KMP

### next: Compose preview inside react native app

To show a Kotlin Compose-based view inside a React Native app, you need to bridge the two frameworks. This can be achieved by using React Native's native module system to integrate the Compose view into the React Native app. Below is a step-by-step guide:

---

### 1. **Set Up Your React Native Project**
Ensure you have a React Native project set up. If you don't have one, create it using:

```bash
npx react-native init MyReactNativeApp
```

---

### 2. **Set Up Kotlin Compose in Android**
If you don't already have a Kotlin Compose view, create one in your Android project.

#### a. Add Compose Dependencies
In your `android/app/build.gradle` file, add the necessary Compose dependencies:

```gradle
android {
    ...
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion '1.5.3' // Use the latest version
    }
}

dependencies {
    ...
    implementation "androidx.compose.ui:ui:1.5.3"
    implementation "androidx.compose.material:material:1.5.3"
    implementation "androidx.compose.ui:ui-tooling-preview:1.5.3"
    implementation "androidx.activity:activity-compose:1.7.2"
}
```

#### b. Create a Compose View
Create a simple Compose view in your Android project. For example, create a file `MyComposeView.kt`:

```kotlin
package com.myreactnativeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myreactnativeapp.ui.theme.MyReactNativeAppTheme

class MyComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyReactNativeAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Hello from Compose!")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyReactNativeAppTheme {
        Greeting("Android")
    }
}
```

---

### 3. **Create a Native Module for React Native**
To expose the Compose view to React Native, create a native module.

#### a. Create a Native Module
Create a new Kotlin class, e.g., `ComposeViewModule.kt`:

```kotlin
package com.myreactnativeapp

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class ComposeViewModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule() {

    override fun getName(): String {
        return "ComposeViewModule"
    }

    @ReactMethod
    fun showComposeView() {
        val intent = Intent(reactContext, MyComposeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        reactContext.startActivity(intent)
    }
}
```

#### b. Register the Module
Create a package for the module, e.g., `ComposeViewPackage.kt`:

```kotlin
package com.myreactnativeapp

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

class ComposeViewPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(ComposeViewModule(reactContext))
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
```

#### c. Add the Package to `MainApplication.java`
Register the package in your `MainApplication.java`:

```java
import com.myreactnativeapp.ComposeViewPackage; // Import your package

@Override
protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new ComposeViewPackage() // Add your package here
    );
}
```

---

### 4. **Call the Native Module from React Native**
In your React Native JavaScript code, call the native module to show the Compose view.

```javascript
import { NativeModules, Button } from 'react-native';

const { ComposeViewModule } = NativeModules;

const App = () => {
  return (
    <Button
      title="Show Compose View"
      onPress={() => ComposeViewModule.showComposeView()}
    />
  );
};

export default App;
```

---

### 5. **Run the App**
Run your React Native app:

```bash
npx react-native run-android
```

When you press the button, the Kotlin Compose view should open as a new activity.

---

### Notes:
- This example opens the Compose view as a new activity. If you want to embed the Compose view directly within a React Native screen, you'll need to use `AndroidView` and create a custom `ViewManager` to host the Compose view.
- Ensure your React Native and Compose dependencies are compatible.
- For iOS, we would need to use SwiftUI or UIKit and follow a similar bridging process.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---


## Developed By

**Malik Dawar**  
[malikdawar332@gmail.com](mailto:malikdawar332@gmail.com)

