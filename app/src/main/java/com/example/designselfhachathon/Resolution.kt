package com.example.designselfhachathon

data class Resolution (

    val name: String = "1:1",
    val onClick: ()-> Unit = {},
    val index: Int = 0
        )