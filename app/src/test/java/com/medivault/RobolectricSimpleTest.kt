package com.medivault

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.junit.Assert.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30]) // Specifying an SDK version that Robolectric supports
class RobolectricSimpleTest {
    @Test
    fun `test application context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertNotNull(context)
    }
    
    @Test
    fun `test application package name`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertEquals("com.medivault", context.packageName)
    }
} 