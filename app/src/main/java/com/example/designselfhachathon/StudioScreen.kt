package com.example.designselfhachathon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.designselfhachathon.ui.theme.BGButton
import com.example.designselfhachathon.ui.theme.ButtonGradient
import com.example.designselfhachathon.ui.theme.TextColor
import com.example.designselfhachathon.ui.theme.TextWhite
import kotlin.random.Random

@Composable
fun StudioScreen(navController: NavController){
    val modelList = listOf<Model>(Model("Model",1),Model("Model",2),Model("Model",3))
    val advList = listOf<AdvanceSettings>(AdvanceSettings("set",1),AdvanceSettings("set",2),AdvanceSettings("set",3))

    val list = listOf<Resolution>(Resolution("1:1",index = 1),Resolution("9:16",index = 2),Resolution("16:9",index = 3),Resolution("3:4",index = 4),)
    LazyColumn(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        item{StudioTopBar({navController.navigate("MainScreen")})}
        item{PromptBar()}
        item{ButtonsBar()}
        item{TextField()}
        item{ResolutionPickBar(list)}
        item{    Text("Model", fontSize = 20.sp,color = TextWhite) }
        item{ModelStyleBar(modelList)}
        item{    Text("Advance Setting", fontSize = 20.sp,color = TextWhite) }
        item{AdvanceSettingBar(advList)}
        item{GenerateButton()}
    }
}

@Composable
fun StudioTopBar(GoTo:()->Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){

        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { GoTo() }
            .background(color = BGButton)){
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite)
        }
        Box(modifier = Modifier.align(Alignment.CenterVertically)){
            Text("Studio", fontSize = 24.sp, color = TextWhite, modifier = Modifier.align(Alignment.Center))

        }

        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { OnClick() }
            .background(color = BGButton)){
            Icon(painter = painterResource(id = R.drawable.baseline_settings_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite
            )
        }
    }
}
@Composable
fun PromptBar(){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){

        Text("Enter your Prompt", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = TextWhite)

       Box(modifier = Modifier
           .clickable { }
           .align(Alignment.CenterVertically)){Text("explore prompt ->",  fontSize = 14.sp, color = TextWhite)}

    }

}@Composable
fun ButtonsBar(){


    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){

        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { OnClick() }
            .background(color = Color.Red)){
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite)
        }
        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { OnClick() }
            .background(color = Color.Blue)){
            Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite)
        }

        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { OnClick() }
            .background(color = Color.Yellow)){
            Icon(painter = painterResource(id = R.drawable.baseline_settings_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite
            )
        }
    }

}@Composable
fun TextField(){
    val limit = 800
    var value by remember {
        mutableStateOf("")
    }
    var isOverCount by remember{
        mutableStateOf(value.count() <= limit)
    }

    BasicTextField(
        value = value,
        onValueChange = { newText ->
            if(isOverCount)// could lead to lags with field
            value = newText
        },
        modifier = Modifier.fillMaxWidth().background(BGButton,
            RoundedCornerShape(16.dp)
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp) // margin left and right
                    .fillMaxWidth()
                    .size(10.dp,160.dp)
                    .border(
                        width = 2.dp,
                        color = BGButton,
                        shape = RoundedCornerShape(size = 16.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding


            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "type anything here...",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextColor
                    )
                }
                innerTextField()
                Box(modifier = Modifier.align(Alignment.BottomEnd).padding(top = 20.dp, bottom = 4.dp)){
                    Text(
                        "${value.count()}/$limit",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextColor)
                }
            }
        }
    )

}@Composable
fun ResolutionPickBar(list: List<Resolution>){

    var pickedRes by remember{mutableStateOf(-1)}

    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
        for(i in list){
            Box(modifier = Modifier){
                Box(
                    Modifier
                        .background(
                            if (pickedRes == i.index) ButtonGradient else BGButton,
                            CircleShape
                        )
                        .clickable {
                            if (pickedRes == i.index)
                                pickedRes = -1
                            else {
                                pickedRes = i.index
                                i.onClick()
                            }

                        }
                ){
                    Text(text = i.name, color = if (pickedRes == i.index) TextWhite else TextColor,
                        fontSize = 18.sp,modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
                }
            }

    }

}
}@Composable
fun ModelStyleBar(list: List<Model>){

    var isVisible  by remember {mutableStateOf(false)}
    var isRotated  by remember {mutableStateOf(false)}
    val indexlist by remember {
        mutableStateOf(MutableList(list.size) {
            false
        })
    }


    val angle: Float by animateFloatAsState(
        targetValue = if (isRotated) 180F else 0F,
        animationSpec = tween(
            durationMillis = 500, // duration
            easing = FastOutSlowInEasing
        )
    )
    Column(){
        Box(
            Modifier
                .fillMaxWidth()
                .background(BGButton, CircleShape)
                .clickable { isVisible = !isVisible
                    isRotated = !isRotated}){
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 12.dp)){

                Text("Choose model", color = TextColor, fontSize = 20.sp, modifier = Modifier)
                Box(modifier = Modifier.rotate(angle)){
                    Icon(painterResource(id = R.drawable.baseline_arrow_drop_down_24),"",tint = TextColor, modifier = Modifier)
                }
            }
        }
        AnimatedVisibility(visible = isVisible) {
            Column(){

                for(i in list){
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(if (indexlist[i.index-1]) ButtonGradient else BGButton, CircleShape).clickable { indexlist[i.index-1] = !indexlist[i.index-1]}){
                        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                            .fillMaxWidth()
                            .padding( vertical = 12.dp)){

                            Text(i.name, color = if (indexlist[i.index-1]) TextWhite else  TextColor, fontSize = 20.sp, modifier = Modifier)
                        }
                    }

                }
            }


        }

    }


}@Composable
fun AdvanceSettingBar(list: List<AdvanceSettings>){

    var isVisible  by remember {mutableStateOf(false)}
    var isRotated  by remember {mutableStateOf(false)}
    val indexlist by remember {
        mutableStateOf(MutableList(list.size) {
            false
        })
    }


    val angle: Float by animateFloatAsState(
        targetValue = if (isRotated) 180F else 0F,
        animationSpec = tween(
            durationMillis = 500, // duration
            easing = FastOutSlowInEasing
        )
    )
    Column(){
        Box(
            Modifier
                .fillMaxWidth()
                .background(BGButton, CircleShape)
                .clickable { isVisible = !isVisible
                            isRotated = !isRotated}){
            Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .fillMaxWidth()
                .padding( vertical = 12.dp)){

                Text("Choose Settings", color = TextColor, fontSize = 20.sp, modifier = Modifier)
                Box(modifier = Modifier.rotate(angle)){
                    Icon(painterResource(id = R.drawable.baseline_arrow_drop_down_24),"",tint = TextColor, modifier = Modifier)
                }
            }
        }
        AnimatedVisibility(visible = isVisible) {
            Column(){

                for(i in list){
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(if (indexlist[i.index-1]) ButtonGradient else BGButton, CircleShape).clickable { indexlist[i.index-1] = !indexlist[i.index-1]}){
                        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                            .fillMaxWidth()
                            .padding( vertical = 12.dp)){

                            Text(i.name, color = if (indexlist[i.index-1]) TextWhite else  TextColor, fontSize = 20.sp, modifier = Modifier)
                        }
                    }

                }
            }


        }

    }
}
@Composable
fun GenerateButton(){
    Box(
        Modifier
            .fillMaxWidth()
            .background(ButtonGradient, CircleShape)
            .clickable { }){
        Text("Generate", color = TextWhite, fontSize = 20.sp, modifier = Modifier
            .align(Alignment.Center)
            .padding(vertical = 12.dp))

    }
}