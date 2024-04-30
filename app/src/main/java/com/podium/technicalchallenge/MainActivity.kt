package com.podium.technicalchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.podium.technicalchallenge.databinding.ActivityMainBinding
import com.podium.technicalchallenge.ui.navigation.AppNavigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }
}