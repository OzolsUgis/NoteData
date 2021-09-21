package com.ugisozols.data.routes

import com.ugisozols.data.checkIfUserExistsByEmail
import com.ugisozols.data.collections.User
import com.ugisozols.data.registerUser
import com.ugisozols.data.requests.AccountRequests
import com.ugisozols.data.responses.MainResponses
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.registerRoute(){
    route("/register"){
        post {
            val request = try {
                call.receive<AccountRequests>()
            }catch (e: ContentTransformationException){
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val userExists = checkIfUserExistsByEmail(request.email)
            if(!userExists){
                if (registerUser(User(request.email,request.password))){
                    call.respond(MainResponses(true, "Account created"))
                }else{
                    call.respond(MainResponses(false,"An unknown error occurred"))
                }
            }else{
                call.respond(MainResponses(false, "User with that email already exists"))
            }
        }
    }
}