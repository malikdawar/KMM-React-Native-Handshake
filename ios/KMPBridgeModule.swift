//
//  KMPBridgeModule.swift
//  AwesomeProject
//
//  Created by Malik Dawar on 01/01/2025.
//

import Foundation
import SwiftUI // Ensure SwiftUI is imported

@objc(KMPBridge)
class KMPBridge: NSObject {

    @objc
    func launchKMPLoginScreen() {
        DispatchQueue.main.async {
            if let rootViewController = UIApplication.shared.keyWindow?.rootViewController {
                // Wrap `ContentView` in a UIHostingController
                let hostingController = UIHostingController(rootView: ContentView())
                
                // Present it modally
                rootViewController.present(hostingController, animated: true, completion: nil)
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
