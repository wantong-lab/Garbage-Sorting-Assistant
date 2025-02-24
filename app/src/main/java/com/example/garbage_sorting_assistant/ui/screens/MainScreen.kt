package com.example.garbage_sorting_assistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onNavigateToCamera: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF1A1A2E),
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    Triple("首页", Icons.Default.Home, 0),
                    Triple("识别", Icons.Default.PhotoCamera, 1),
                    Triple("我的", Icons.Default.Person, 2)
                )
                
                items.forEach { (label, icon, index) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { 
                            selectedTab = index
                            if (index == 1) {
                                onNavigateToCamera()
                            }
                        },
                        icon = { Icon(imageVector = icon, contentDescription = label) },
                        label = { Text(text = label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // 标题
            Text(
                text = "智能垃圾分类",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "让环保更简单",
                fontSize = 14.sp,
                color = Color(0xB3FFFFFF),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // 搜索栏
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            // 分类卡片网格
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    title = "可回收物",
                    icon = Icons.Default.Refresh,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.weight(1f)
                )
                CategoryCard(
                    title = "有害垃圾",
                    icon = Icons.Default.Warning,
                    color = Color(0xFFF44336),
                    modifier = Modifier.weight(1f)
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    title = "厨余垃圾",
                    icon = Icons.Default.Restaurant,
                    color = Color(0xFFE65100),
                    modifier = Modifier.weight(1f)
                )
                CategoryCard(
                    title = "其他垃圾",
                    icon = Icons.Default.Delete,
                    color = Color(0xFF424242),
                    modifier = Modifier.weight(1f)
                )
            }

            // 快捷功能标题
            Text(
                text = "快捷功能",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // 快捷功能按钮
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                QuickActionButton(
                    text = "扫描识别",
                    icon = Icons.Default.PhotoCamera,
                    modifier = Modifier.weight(1f)
                )
                QuickActionButton(
                    text = "分类指南",
                    icon = Icons.Default.Book,
                    modifier = Modifier.weight(1f)
                )
                QuickActionButton(
                    text = "积分兑换",
                    icon = Icons.Default.CardGiftcard,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text("搜索垃圾分类...", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0x1AFFFFFF),
            unfocusedContainerColor = Color(0x1AFFFFFF),
            disabledContainerColor = Color(0x1AFFFFFF),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "搜索",
                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        }
    )
}

@Composable
fun CategoryCard(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun QuickActionButton(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0x1AFFFFFF)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
} 