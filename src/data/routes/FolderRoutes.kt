package com.ugisozols.data.routes

import com.ugisozols.data.collections.Folder
import com.ugisozols.data.collections.User
import com.ugisozols.data.deleteFolder
import com.ugisozols.data.getFolderForUser
import com.ugisozols.data.requests.DeleteFolder
import com.ugisozols.data.saveFolder
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.folderRoutes(){
    route("/getFolder"){
        authenticate {
            get {
                val email = call.principal<UserIdPrincipal>()!!.name
                val folder = getFolderForUser(email)
                call.respond(HttpStatusCode.OK, folder)
            }
        }
    }

    route("/addFolder"){
        authenticate {
            post {
                val folder = try {
                    call.receive<Folder>()
                }catch (e: ContentTransformationException){
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                if (saveFolder(folder)){
                    call.respond(HttpStatusCode.OK)
                }else{
                    call.respond(HttpStatusCode.Conflict)
                }
            }
        }
    }

    route("deleteFolder"){
        authenticate {
            post {
                val email = call.principal<UserIdPrincipal>()!!.name
                val request = try {
                    call.receive<DeleteFolder>()
                }catch (e:ContentTransformationException){
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                if(deleteFolder(email,request.folderId)){
                    call.respond(HttpStatusCode.OK)
                }else{
                    call.respond(HttpStatusCode.Conflict)
                }
            }
        }
    }
}