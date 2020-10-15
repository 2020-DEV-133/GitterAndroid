package com.debin.gitterandroid.network.TransferModel

data class CommitTransfer (

    val author : AuthorTransfer,
    val message : String,
    val url : String,
    val comment_count : Int
)