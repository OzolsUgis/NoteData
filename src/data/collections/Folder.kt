package com.ugisozols.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Folder(
    val folderName : String,
    val note: Note? = null,
    val userEmail : String,
    @BsonId
    val id : String = ObjectId().toString()
)
