package com.github.galbanie.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.*

/**
 * Created by Galbanie on 2017-09-16.
 */
class ParameterAction {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val parameterProperty = SimpleObjectProperty<Parameter>()
    var parameter by parameterProperty

    val actionsProperty = SimpleListProperty<Action>(FXCollections.observableArrayList())
    var actions by actionsProperty
}

class ParameterActionModel : ItemViewModel<ParameterAction>() {
    val id = bind(ParameterAction::idProperty)
    val parameter = bind(ParameterAction::parameterProperty)
    val actions = bind(ParameterAction::actionsProperty)
}
