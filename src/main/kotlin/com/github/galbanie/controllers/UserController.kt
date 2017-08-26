package com.github.galbanie.controllers

import com.github.galbanie.models.Session
import com.github.galbanie.views.DictionaryArea
import com.github.galbanie.views.LoginArea
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-24.
 */
class UserController : Controller() {
    val sessionProperty = SimpleObjectProperty<Session>(null)
    val session : Session? get() = sessionProperty.value

    init {
        sessionProperty.onChange {
            if(it == null){
                workspace.dock<LoginArea>()
            }
            else{
                workspace.dock<DictionaryArea>()
            }
        }
    }

    fun isConnected() : Boolean{
        return session != null
    }

    fun login(username : String, password : String) : Boolean{
        return true
    }
}