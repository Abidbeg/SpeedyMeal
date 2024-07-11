package com.am.speedymeal.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.am.speedymeal.R

@Composable
fun MyAccountScreen(modifier: Modifier) {
    AccountDetails(Modifier)
}

@Preview(widthDp = 300, heightDp = 500)
@Composable
fun AccountDetails(modifier: Modifier = Modifier) {

    var listCategory: MutableList<String> = ArrayList<String>()
    listCategory.add("Favourite")
    listCategory.add("Wallet")
    listCategory.add("Order")

    Column(modifier.padding(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier.weight(1f)) {
                Text(text = "Abidbeg Mirza", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = "account",
                modifier.size(90.dp)
            )
        }
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            for (i in 0 until listCategory.size) {
                Box(
                    modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                        .padding(8.dp)
                ) {
                    Column(
                        modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pizza),
                            contentDescription = "fav",
                            modifier.size(60.dp)
                        )
                        Text(
                            text = listCategory[i],
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Spacer(modifier.width(5.dp))
            }


        }
        Spacer(modifier.height(5.dp))
        Box(
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.LightGray)
                .padding(8.dp)
        ) {
            Row {
                Column(modifier.weight(3f)) {
                    Text(
                        text = "Try SpeedyMeal Base Plan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "6 month free delivery and many more in just $39",
                        fontWeight = FontWeight.Black,
                        fontSize = 11.sp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.pizza),
                    contentDescription = "Gift",
                    modifier.weight(1f)
                )
            }
        }
    }
}
