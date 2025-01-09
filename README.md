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
     
     class KMPPackage : ReactPackage {
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

Now set the KMP Path inside the pods of React native IOS 

<img width="835" alt="Screenshot 2025-01-09 at 10 21 44 AM" src="https://github.com/user-attachments/assets/45449b90-54ab-4094-a6fb-7e6322b43b8e" />

Then install the cocoapods dependency inside the KMP module and set the paths accordinly 

<img width="713" alt="Screenshot 2025-01-09 at 10 22 14 AM" src="https://github.com/user-attachments/assets/37c853e7-ddce-47b4-8fac-8bb7876f148d" />

---

### Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/malikdawar/KMP-ReactNative-Handshake.git
   ```

2. **Open the project in Visual Studio code**:
   - Open Visual Studio.
   - Click on `File` -> `Open` and navigate to the cloned repository.

3. **Build and Run**:
   - Open terminal by pressing (Control + `)
   - Go to the project directory cd path to the project folder
   - To run andorid app write the command npm run android
   - To run IOS app write the command npm run ios

### Usage

- There are Screens in this tiny project. 1st screen(Into) is develop in React native
- The second screen(Login) is developed in KMP

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---


## Developed By

**Malik Dawar**  
[malikdawar332@gmail.com](mailto:malikdawar332@gmail.com)

