package com.ugisozols.data

import com.ugisozols.data.collections.Folder
import com.ugisozols.data.collections.Note
import com.ugisozols.data.collections.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import reactor.core.publisher.toMono


private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("NoteDB")

val users = database.getCollection<User>()
val folders = database.getCollection<Folder>()
val notes = database.getCollection<Note>()


suspend fun checkIfUserExistsByEmail (email : String) : Boolean {
    return users.findOne(User::email eq email) != null
}

suspend fun checkPasswordForEmail(email: String, passwordToCheck : String) : Boolean {
    val realPassword = users.findOne(User::email eq email)?.password ?: return false
    return realPassword == passwordToCheck
}

suspend fun registerUser(user: User): Boolean {
    return users.insertOne(user).wasAcknowledged()
}

suspend fun getFolderForUser(email: String) : List<Folder> {
    return folders.find(Folder::userEmail eq email).toList()
}


suspend fun getNoteForUser(email: String) : List<Note> {
    return notes.find(Note::userEmail eq email).toList()
}