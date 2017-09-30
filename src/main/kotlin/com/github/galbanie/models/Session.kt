package com.github.galbanie.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import org.joda.time.DateTime
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-24.
 */

class Session {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val userProperty = SimpleObjectProperty<User>()
    var user by userProperty

    val isUserProcessProperty = SimpleBooleanProperty(false)
    var isUserProcess by isUserProcessProperty

    /*val dateLoginProperty = SimpleObjectProperty<DateTime>()
    var dateLogin by dateLoginProperty

    val dateLogoutProperty = SimpleObjectProperty<DateTime>()
    var dateLogout by dateLogoutProperty*/
}

class SessionModel : ItemViewModel<Session>() {
    val id = bind(Session::idProperty)
    val user = bind(Session::userProperty)
    val isUserProcess = bind(Session::isUserProcessProperty)
    /*val dateLogin = bind(Session::dateLoginProperty)
    val dateLogout = bind(Session::dateLogoutProperty)*/
}
