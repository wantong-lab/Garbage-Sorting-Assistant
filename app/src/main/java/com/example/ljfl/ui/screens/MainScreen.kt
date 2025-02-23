package com.example.ljfl.ui.screens

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ljfl.R
import com.example.ljfl.ui.theme.*

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            // 标题
            Text(
                text = "智能垃圾分类",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = "让环保更简单",
                fontSize = 14.sp,
                color = TextSecondary,
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
                    color = RecyclableCard,
                    modifier = Modifier.weight(1f)
                )
                CategoryCard(
                    title = "有害垃圾",
                    icon = Icons.Default.Warning,
                    color = HazardousCard,
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
                    color = KitchenCard,
                    modifier = Modifier.weight(1f)
                )
                CategoryCard(
                    title = "其他垃圾",
                    icon = Icons.Default.Delete,
                    color = OtherCard,
                    modifier = Modifier.weight(1f)
                )
            }

            // 快捷功能标题
            Text(
                text = "快捷功能",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
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

        // 底部导航栏
        NavigationBar(
            containerColor = BottomNavBackground,
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = "首页") },
                label = { Text("首页") },
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BottomNavSelected,
                    unselectedIconColor = BottomNavUnselected,
                    selectedTextColor = BottomNavSelected,
                    unselectedTextColor = BottomNavUnselected
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.PhotoCamera, contentDescription = "识别") },
                label = { Text("识别") },
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BottomNavSelected,
                    unselectedIconColor = BottomNavUnselected,
                    selectedTextColor = BottomNavSelected,
                    unselectedTextColor = BottomNavUnselected
                )
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Person, contentDescription = "我的") },
                label = { Text("我的") },
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BottomNavSelected,
                    unselectedIconColor = BottomNavUnselected,
                    selectedTextColor = BottomNavSelected,
                    unselectedTextColor = BottomNavUnselected
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        value = "",
        onValueChange = { },
        placeholder = { Text("搜索垃圾分类...", color = TextSecondary) },
        modifier = modifier
            .background(
                SearchBarBackground,
                RoundedCornerShape(8.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = SearchBarBackground,
            textColor = TextPrimary,
            cursorColor = TextPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "搜索",
                tint = TextSecondary
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
        colors = CardDefaults.cardColors(containerColor = color)
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
        colors = CardDefaults.cardColors(containerColor = SearchBarBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = text,
                tint = TextPrimary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                color = TextPrimary,
                fontSize = 12.sp
            )
        }
    }
} 