package com.ugisozols.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Note(
    val title : String,
    val date : Long,
    val content : String,
    val userEmail : String,
    @BsonId
    val id : String = ObjectId().toString()
)
