package com.ugisozols.data.routes

import com.ugisozols.data.checkPasswordForEmail
import com.ugisozols.data.requests.AccountRequests
import com.ugisozols.data.responses.MainResponses
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.features.ContentTransformationException
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.loginRoute(){
    route("/login"){
        post {
            val request = try {
                call.receive<AccountRequests>()
            }catch (e : ContentTransformationException){
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email,request.password)
            if(isPasswordCorrect){
                call.respond(MainResponses(true,"You are now logged in"))
            }else{
                call.respond(MainResponses(false,"Email or password incorrect"))
            }
        }
    }
}
