package com.am.speedymeal.screens

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.am.speedymeal.R
import com.am.speedymeal.model.Result
import com.am.speedymeal.viewmodel.BooksModel

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
            }, Modifier.padding(end = 20.dp)) {
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
            }, Modifier.padding(end = 20.dp)) {
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

    val listImages = mutableListOf(
        R.drawable.ic_fiction,
        R.drawable.ic_drama,
        R.drawable.ic_humour,
        R.drawable.ic_politics,
        R.drawable.ic_philosophy,
        R.drawable.ic_history,
        R.drawable.ic_adventure
    )

    var strList = mutableListOf(
        "Fiction",
        "Drama",
        "Humour",
        "Politics",
        "Philosophy",
        "History",
        "Adventure"
    )

    LazyRow {
        items(strList.size) { item ->
            ListCategoryItem(strList[item], listImages[item])
        }
    }
}

@Composable
fun ListCategoryItem(strText: String, strImage: Int) {
    val bookViewModel: BooksModel = hiltViewModel()

    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                bookViewModel._strTopic.value = strText
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = strImage),
            contentDescription = "strText",
            Modifier.size(50.dp)
        )
        Text(text = strText)
    }
}

@Composable
fun ListRestaurant() {
    val bookViewModel: BooksModel = hiltViewModel()
    val book = bookViewModel.book.collectAsState()

    if (book.value.count == 0) {
        Text(text = "Loading...")
    } else {
        LazyVerticalGrid(

            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(book.value.results) {
                ListRestaurantItem(it)
            }
        }
    }
}

@Composable
fun ListRestaurantItem(resultList: Result) {
    Column(modifier = Modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .height(140.dp)
                .clip(RoundedCornerShape(8.dp))
                .paint(
                    painter = rememberAsyncImagePainter(resultList.formats.imagePath),
                    contentScale = ContentScale.Fit
                )
        ) {
            Row(modifier = Modifier.padding(6.dp)) {
                Text(
                    text = "", color = Color.White, modifier = Modifier
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
                Text(text = "${resultList.title}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
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