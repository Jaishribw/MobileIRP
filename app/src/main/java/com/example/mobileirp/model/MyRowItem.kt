package com.example.mobileirp.model

import androidx.annotation.Keep

@Keep
data class MyRowItem(
    val pageId: Int,
    val id: Int,
    val name: String
)