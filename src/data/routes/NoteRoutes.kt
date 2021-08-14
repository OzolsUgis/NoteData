package com.ugisozols.data.routes

import com.ugisozols.data.*
import com.ugisozols.data.collections.Folder
import com.ugisozols.data.collections.Note
import com.ugisozols.data.collections.User
import com.ugisozols.data.requests.DeleteFolder
import com.ugisozols.data.requests.DeleteNote
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.noteRoutes(){
    route("/getNotes"){
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val notes = getNoteForUser(email)
                call.respond(HttpStatusCode.OK, notes)
            }
        }
    }

    route("/addNotes"){
        authenticate {
            post {
                val note = try {
                    call.receive<Note>()
                }catch (e: ContentTransformationException){
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                if (saveNote(note)){
                    call.respond(HttpStatusCode.OK)
                }else{
                    call.respond(HttpStatusCode.Conflict)
                }
            }
        }
    }

    route("deleteNote"){
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name
                val request = try {
                    call.receive<DeleteNote>()
                }catch (e:ContentTransformationException){
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                if(deleteNote(email,request.noteId)){
                    call.respond(HttpStatusCode.OK)
                }else{
                    call.respond(HttpStatusCode.Conflict)
                }
            }
        }
    }
}