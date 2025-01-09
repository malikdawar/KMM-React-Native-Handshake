import { StyleSheet, Text, TouchableOpacity, View, NativeModules, Platform } from 'react-native';
import React from 'react';

// Import NativeModules and extract KMPBridge
const { KMPBridge } = NativeModules;

// Function to launch the KMP login screen
const launchKMPLoginScreen = () => {
  if (Platform.OS === 'android') {
    console.log("Calling Android function");
    KMPBridge.launchKMPLoginScreen(); // Calls Android bridge
  } else if (Platform.OS === 'ios') {
    console.log("Calling iOS function");
    KMPBridge.launchKMPLoginScreen(); // Calls iOS bridge
  } else {
    console.log("Unsupported platform");
  }
};

const App = () => {
  return (
    <View style={styles.container}>
      {/* Sign In Button */}
      <TouchableOpacity style={styles.button} onPress={launchKMPLoginScreen}>
        <Text style={styles.buttonText}>Sign In</Text>
      </TouchableOpacity>

      {/* Sign Up Button */}
      <TouchableOpacity style={styles.button}>
        <Text style={styles.buttonText}>Sign Up</Text>
      </TouchableOpacity>
    </View>
  );
};

export default App;

// Styles
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5', // Added a light background color
  },
  button: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'gray',
    marginBottom: 20,
    width: 200,
    borderRadius: 10, // Added rounded corners for a modern look
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
    padding: 15,
  },
});
