package com.example.garbage_sorting_assistant

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.garbage_sorting_assistant.ui.screens.CameraScreen
import com.example.garbage_sorting_assistant.ui.screens.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(
                                onNavigateToCamera = {
                                    navController.navigate("camera")
                                }
                            )
                        }
                        
                        composable("camera") {
                            CameraScreen(
                                onNavigateBack = {
                                    navController.navigateUp()
                                },
                                onImageCaptured = { uri ->
                                    // 处理拍摄的图片
                                    Toast.makeText(
                                        this@MainActivity,
                                        "图片已保存: $uri",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onImagePickerSelected = { uri ->
                                    // 处理从相册选择的图片
                                    Toast.makeText(
                                        this@MainActivity,
                                        "已选择图片: $uri",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
} 