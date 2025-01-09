//
//  ComposeView.swift
//  AwesomeProject
//
//  Created by Malik Dawar on 02/01/2025.
//

import SwiftUI
import UIKit
import SharedModuleIos

//struct ComposeView: UIViewControllerRepresentable {
//    func makeUIViewController(context: Context) -> UIViewController {
//        MainViewControllerKt.MainViewController()
//    }
//
//    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
//}


struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // Create the Kotlin Compose MainViewController
        let viewController = MainViewControllerKt.MainViewController()
        
        // Add tap gesture recognizer to dismiss the keyboard
        let tapGesture = UITapGestureRecognizer(target: context.coordinator, action: #selector(Coordinator.dismissKeyboard))
        tapGesture.cancelsTouchesInView = false // Allow other interactions
        viewController.view.addGestureRecognizer(tapGesture)
        
        return viewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}

    // Coordinator for handling gestures
    func makeCoordinator() -> Coordinator {
        return Coordinator()
    }

    class Coordinator: NSObject {
        @objc func dismissKeyboard() {

            UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
        }
    }
}

