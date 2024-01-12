package com.example.designselfhachathon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.designselfhachathon.ui.theme.BGButton
import com.example.designselfhachathon.ui.theme.Background
import com.example.designselfhachathon.ui.theme.ButtonGradient
import com.example.designselfhachathon.ui.theme.GradientOne
import com.example.designselfhachathon.ui.theme.GradientTwo
import com.example.designselfhachathon.ui.theme.TextColor
import com.example.designselfhachathon.ui.theme.TextWhite
import com.example.designselfhachathon.ui.theme.VioletGradient

@Composable
fun MainScreen(navController: NavController){
    val creators = listOf<Creator>(Creator(),Creator(),Creator())
    val elements = listOf<ExplorationElement>(ExplorationElement(creators = creators),ExplorationElement(creators = creators),ExplorationElement(creators = creators))
    LazyColumn(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        item{WelcomeBar("Sereja", { OnClick() }, { OnClick() })}
        item{UnleashCreativityBlock({navController.navigate("StudioScreen")})}
        item{SeeAllBar("Exploration", { OnClick() })}
        item{ExplorationBar(elements)}
        item{SeeAllBar("Top Creator", { OnClick() })}
        item{TopCreatorsList(creators)}
    }
}
@Composable
fun WelcomeBar(name: String, profileMethod:()->Unit, settingsMethod:()->Unit){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { profileMethod() }){
            Image(painter = painterResource(id = R.drawable.creator1),"s", modifier = Modifier.align(Alignment.Center))
        }
        Box(){
            Column(){
                Text("Welcome,", fontSize = 20.sp, color = TextWhite)
                Text(name + 0x1f608, fontSize = 28.sp, color = TextWhite)
            }
        }
        Box(modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { settingsMethod() }
            .background(color = BGButton)){
            Icon(painter = painterResource(id = R.drawable.baseline_settings_32), contentDescription ="s", modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),tint = TextWhite)
        }


    }
}
@Composable
fun UnleashCreativityBlock(GoTo: ()->Unit){
    Box(modifier = Modifier.clip(RoundedCornerShape(32.dp))){
        Box(modifier = Modifier
            .background(brush = Brush.horizontalGradient(listOf(GradientOne, GradientTwo)))
            .fillMaxWidth()){

            Box(modifier= Modifier
                .padding(start = 200.dp, top = 50.dp)
                .alpha(0.1f)
                .border(
                    BorderStroke(10.dp, GradientOne), CircleShape
                )
                .size(128.dp)
                .clip(CircleShape)){
            }

            Box(modifier= Modifier
                .padding(start = 180.dp, top = 90.dp)
                .alpha(0.1f)
                .border(
                    BorderStroke(10.dp, GradientOne), CircleShape
                )
                .size(128.dp)
                .clip(CircleShape)){
            }
            Box(modifier= Modifier
                .padding(start = 180.dp, top = 30.dp)
                .alpha(0.1f)
                .border(
                    BorderStroke(10.dp, GradientOne), CircleShape
                )
                .size(128.dp)
                .clip(CircleShape)){
            }
            Column(modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp).clickable { GoTo() }, verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Text("Unleash your creativity with Ai ME!", color = TextWhite, fontSize = 24.sp)
                Text("Createing a photo AI using magical tools in our app", color = TextWhite, fontSize = 16.sp)
                Box(modifier = Modifier.clip(CircleShape)){

                    Box(modifier = Modifier
                        .background(color = VioletGradient)
                        .clickable { OnClick() } ){
                        Text("Create Studio", color = TextWhite, modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp), fontSize = 20.sp)
                    }
                }

            }
        }
    }
}
@Composable
fun SeeAllBar(name: String, onClick:()->Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier.align(Alignment.CenterVertically)){
            Text(name, fontSize = 28.sp,color = TextWhite, fontWeight = FontWeight.Bold)
        }
        Box(modifier = Modifier
            .clickable { onClick() }
            .align(Alignment.CenterVertically)){
            Text("See All ->", fontSize = 16.sp,color = TextWhite,)
        }
    }
}
@Composable
fun ExplorationBar(list: List<ExplorationElement>){
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)){
        items(list.size){
            ExplorationItem(exp = list[it])
        }
    }
}
@Composable
fun ExplorationItem(exp: ExplorationElement){
    Box(modifier = Modifier
        .background(color = BGButton, RoundedCornerShape(8.dp))
        .padding(8.dp)){
        Box(modifier = Modifier.size(width = 312.dp,height = 256.dp)){
            Image(painterResource(id = exp.image),"")
            Row(horizontalArrangement = Arrangement.spacedBy((-8).dp), modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()){

                for(cr in exp.creators){

                    Box(Modifier.size(16.dp).border(BorderStroke(2.dp,Background), CircleShape)){
                        Image(painterResource(id = cr.image),"")
                    }
                }
            }
        }
        Box(){
            Text(text = exp.name, fontSize = 16.sp, fontWeight = FontWeight.Bold,color = TextWhite)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Box(modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .background(Background, CircleShape)){

                Box(){
                    Text(text = "#"+ exp.tag,color = TextColor, modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 4.dp))
                }
            }

            Box(modifier = Modifier
                .background(Background, CircleShape)
                .padding(12.dp)
                .clickable { OnClick() }){
                Icon(painterResource(id =R.drawable.baseline_ios_share_24),"", tint = TextWhite)
            }
        }
    }
}

@Composable
fun TopCreatorsList(list: List<Creator>){

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)){
        for (i in list){
            CreatorItem(cr = i)
        }
    }
}

@Composable
fun CreatorItem(cr: Creator){
    Box(modifier = Modifier
        .background(color = BGButton, shape = CircleShape)
        .fillMaxWidth()
        .clickable { OnClick() }
        .clip(RoundedCornerShape(24.dp))){
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier
            .clip(CircleShape)
            .padding(bottom = 2.dp)){
            Box(modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)){
                Image(painter = painterResource(cr.image),"s", modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp))
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.padding(vertical = 8.dp)) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)){
                    Text(cr.name, fontSize = 18.sp, color = TextWhite)
                    if(cr.isVerified){
                        Icon(painterResource(id = R.drawable.baseline_verified_16),"",tint = Color.Blue, modifier = Modifier.align(Alignment.CenterVertically))
                    }

                }
                Text(cr.name, fontSize = 12.sp, color = TextColor)

            }
        }
    }
}
fun OnClick(){

}