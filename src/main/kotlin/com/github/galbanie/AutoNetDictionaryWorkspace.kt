package com.github.galbanie

import com.github.galbanie.controllers.UserController
import com.github.galbanie.views.LoginArea
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-20.
 */
class AutoNetDictionaryWorkspace : Workspace() {
    val userController : UserController by inject()
    init {
        if(userController.isConnected().not()){
            dock<LoginArea>()
        }
    }
}