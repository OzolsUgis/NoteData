package com.ugisozols

import com.ugisozols.data.checkPasswordForEmail
import com.ugisozols.data.routes.loginRoute
import com.ugisozols.data.routes.noteRoutes
import com.ugisozols.data.routes.registerRoute
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }
    install(CallLogging)
    install(DefaultHeaders)
    install(Authentication){
        configureAuth()
    }
    install(Routing){
        loginRoute()
        registerRoute()
        noteRoutes()
    }



}

fun Authentication.Configuration.configureAuth(){
    basic {
        realm = "Note Database"
        validate { credentials->
            val email = credentials.name
            val password = credentials.password

            if(checkPasswordForEmail(email,password)){
                UserIdPrincipal(email)
            }else null
        }
    }
}
