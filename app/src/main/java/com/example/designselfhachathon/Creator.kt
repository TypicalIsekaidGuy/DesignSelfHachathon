package com.example.designselfhachathon

import androidx.annotation.DrawableRes

data class Creator (
    @DrawableRes val image:  Int = R.drawable.creator1,
    val name: String = "Zidane Bounty Hunters",
    val underTag: String = "futuristic imagination",
    val isVerified: Boolean = true
        )