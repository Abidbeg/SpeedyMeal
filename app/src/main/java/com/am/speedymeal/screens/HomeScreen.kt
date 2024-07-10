package com.am.speedymeal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.am.speedymeal.R

@Preview(widthDp = 300, heightDp = 500)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        CustomToolBar()
        CustomSearchBar()
        ListCategory()
    }
}

@Composable
fun CustomToolBar() {
    Row {
        Row(modifier = Modifier.weight(1f)) {
            Text(text = "110 Symington Ave", textAlign = TextAlign.Center)
            Image(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "arrow_down"
            )
        }
        Row() {
            Image(
                painter = painterResource(id = R.drawable.notifications),
                contentDescription = "notification",
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "cart"
            )
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
            .padding(8.dp)
            .clip(CircleShape)
            .background(Color(0XFF101921))
    ) {
        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFFFFFFF),
                focusedIndicatorColor = Color.Transparent,
            )
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
            painter = painterResource(id = R.drawable.shopping_cart),
            contentDescription = "strText",
        )
        Text(text = strText)
    }
}