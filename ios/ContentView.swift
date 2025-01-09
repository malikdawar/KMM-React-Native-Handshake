//
//  ContentView.swift
//  AwesomeProject
//
//  Created by Malik Dawar on 02/01/2025.
//

import SwiftUI
import UIKit

struct ContentView: View {
    var body: some View {
        // Use ComposeView to display the Kotlin Multiplatform screen
        ComposeView()
            .ignoresSafeArea(.keyboard) // Ensures the Compose screen handles the keyboard
    }
}
