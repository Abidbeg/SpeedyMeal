package com.am.speedymeal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.speedymeal.R

@Preview(widthDp = 300, heightDp = 500)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        CustomToolBar()
        CustomSearchBar()
        ListCategory()
        ListRestaurant()
    }
}

@Composable
fun CustomToolBar() {
    var itemCount = 10
    Row {
        Row(modifier = Modifier.weight(1f)) {
            Text(text = "110 Symington Ave", textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "arrow_down"
            )
        }
        Row() {
            BadgedBox(badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(text = "$itemCount")
                    }
                }
            },Modifier.padding(end = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = "notification",
                )
            }
            BadgedBox(badge = {
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(text = "$itemCount")
                    }
                }
            },Modifier.padding(end = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.shopping_cart),
                    contentDescription = "cart"
                )
            }


        }
    }
}

@Composable
fun CustomSearchBar(modifier: Modifier = Modifier) {
    var searchValue by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(CircleShape)
            .background(Color(0XFF101921))
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchValue,
            onValueChange = { searchValue = it },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            label = { Text(text = "Search SpeedyMeal") }
        )

    }
}

@Composable
fun ListCategory(modifier: Modifier = Modifier) {

    var strList: MutableList<String> = ArrayList<String>()
    strList.add("Indian")
    strList.add("Pizza")
    strList.add("Sandwich")
    strList.add("Fast Food")
    strList.add("Vegetarian")
    strList.add("Vadapav")


    LazyRow {
        items(strList.size) { item ->
            ListCategoryItem(strList[item])
        }
    }
}

@Composable
fun ListCategoryItem(strText: String) {
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.pizza),
            contentDescription = "strText",
        )
        Text(text = strText)
    }
}

@Composable
fun ListRestaurant() {
    LazyColumn {
        items(10) { item ->
            ListRestaurantItem()

        }
    }
}

@Composable
fun ListRestaurantItem(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .paint(
                    painterResource(id = R.drawable.osmos),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Row(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = "Buy 1 Get 1 Free", color = Color.White, modifier = Modifier
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.brightness),
                    contentDescription = "Like"
                )
            }
        }
        Row(Modifier.padding(top = 4.dp, bottom = 6.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Royal Darbar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "$1.99 Delivery Fee - 35 Min", fontSize = 11.sp, color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(0XFFd3d3d3))
            ) {
                Text(text = "3.8", modifier = Modifier.padding(6.dp), fontSize = 12.sp)
            }
        }
    }
}