package com.example.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class BaselineProfileGenerator {

    @OptIn(ExperimentalBaselineProfilesApi::class)
    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @OptIn(ExperimentalBaselineProfilesApi::class)
    @Test
    fun generate() = baselineProfileRule.collectBaselineProfile(
        packageName = "com.example.spotifymusicapp",
        profileBlock = {
            startActivityAndWait()
            val list = device.findObject(By.text("Log in"))

            list.click()
        }
    )
}