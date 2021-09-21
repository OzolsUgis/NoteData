package com.ugisozols.data


import com.ugisozols.data.collections.Note
import com.ugisozols.data.collections.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo


private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("NotesDatabase")

val users = database.getCollection<User>()
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


suspend fun getNoteForUser(email: String) : List<Note> {
    return notes.find(Note::userEmail eq email).toList()
}

suspend fun saveNote(note: Note) : Boolean {
    val noteExists = notes.findOneById(note.id)!= null
    return if (noteExists){
        notes.updateOneById(note.id, note).wasAcknowledged()
    }else{
        notes.insertOne(note).wasAcknowledged()
    }
}

suspend fun deleteNote(email: String, noteId : String) : Boolean{
    val note = notes.findOne(Note::id eq noteId, Note::userEmail eq email)
    note?.let { note->
        return notes.deleteOneById(note.id).wasAcknowledged()
    } ?: return false
}
