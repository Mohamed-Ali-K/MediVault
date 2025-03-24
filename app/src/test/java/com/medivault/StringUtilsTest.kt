package com.medivault

import org.junit.Test
import org.junit.Assert.*

/**
 * Tests for string utility functions
 */
class StringUtilsTest {
    
    @Test
    fun `test capitalize first letter`() {
        assertEquals("Hello", capitalizeFirstLetter("hello"))
        assertEquals("World", capitalizeFirstLetter("world"))
        assertEquals("A", capitalizeFirstLetter("a"))
        assertEquals("", capitalizeFirstLetter(""))
    }
    
    @Test
    fun `test mask sensitive data`() {
        assertEquals("****1234", maskSensitiveData("12341234", 4))
        assertEquals("************9876", maskSensitiveData("1234567890129876", 4))
        assertEquals("1234", maskSensitiveData("1234", 4))
        assertEquals("", maskSensitiveData("", 4))
    }
    
    // Utility functions to test
    private fun capitalizeFirstLetter(text: String): String {
        if (text.isEmpty()) return ""
        return text.substring(0, 1).uppercase() + text.substring(1)
    }
    
    private fun maskSensitiveData(data: String, visibleChars: Int): String {
        if (data.isEmpty()) return ""
        val visibleCount = minOf(visibleChars, data.length)
        val visiblePortion = data.takeLast(visibleCount)
        val maskedLength = data.length - visibleCount
        val maskedPortion = if (maskedLength > 0) "*".repeat(maskedLength) else ""
        return maskedPortion + visiblePortion
    }
} 