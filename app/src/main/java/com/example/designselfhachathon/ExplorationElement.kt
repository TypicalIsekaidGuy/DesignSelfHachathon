package com.example.designselfhachathon

import androidx.annotation.DrawableRes

data class ExplorationElement(
    @DrawableRes val image:  Int = R.drawable.firstpic,
    val name: String = "Abstract portrait with light effects",
    val tag: String = "light",
    val creators: List<Creator>,
    val onClick: ()->Unit = { OnClick() }
    )
