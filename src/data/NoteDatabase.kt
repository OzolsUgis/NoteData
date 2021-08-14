package com.ugisozols.data

import com.ugisozols.data.collections.Folder
import com.ugisozols.data.collections.Note
import com.ugisozols.data.collections.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo


private val client = KMongo.createClient().coroutine
private val database = client.getDatabase("NotesDatabase")

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

suspend fun saveFolder(folder: Folder) : Boolean {
    val folderExists = folders.findOneById(folder.id)!= null
    return if (folderExists){
                folders.updateOneById(folder.id, folder).wasAcknowledged()
            }else{
                folders.insertOne(folder).wasAcknowledged()
    }
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

suspend fun deleteFolder(email: String, folderId : String) : Boolean {
    val folder = folders.findOne(Folder::id eq folderId, Folder::userEmail eq email)
    folder?.let { folder->
        return folders.deleteOneById(folder.id).wasAcknowledged()
    } ?: return false
}