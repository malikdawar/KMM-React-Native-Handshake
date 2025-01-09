package com.kmm.kmpsharedmodule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


// Android: reactnative -> android native -> KMP

// IOS: reactnative -> SWIFT -> KMP