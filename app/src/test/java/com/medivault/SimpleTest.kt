package com.medivault

import org.junit.Test
import org.junit.Assert.*

class SimpleTest {
    @Test
    fun `test simple addition`() {
        assertEquals(4, 2 + 2)
    }
    
    @Test
    fun `test string concatenation`() {
        val result = "Hello" + " " + "World"
        assertEquals("Hello World", result)
    }
} 