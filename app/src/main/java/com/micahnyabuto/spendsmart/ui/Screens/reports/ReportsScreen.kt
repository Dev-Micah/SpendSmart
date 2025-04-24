package com.micahnyabuto.spendsmart.ui.Screens.reports

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.micahnyabuto.spendsmart.R

@Composable
fun ReportsScreen(
    modifier: Modifier =Modifier,
    navController: NavController
){

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Image(
            painter = painterResource(id =R.drawable.report),
            contentDescription = "Empty reports screen logo",
            modifier= modifier
                .size(300.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(Modifier.size(8.dp))
        Text("Your reports will be here for downloading",
            style = )
        Spacer(Modifier.size(8.dp))
        Text("Currently you have no reports ")
    }

}

@Preview
@Composable
fun ReportsScreenPreview(){
    ReportsScreen(
        modifier = Modifier,
        navController = rememberNavController()
    )
}
