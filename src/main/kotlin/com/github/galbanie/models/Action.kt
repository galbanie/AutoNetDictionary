package com.github.galbanie.models

import com.github.galbanie.utils.Priority
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Created by Galbanie on 2017-09-16.
 */
class Action {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val nameProperty = SimpleStringProperty()
    var name by nameProperty

    val priorityProperty = SimpleObjectProperty<Priority>()
    var priority by priorityProperty
}

class ActionModel : ItemViewModel<Action>() {
    val id = bind { item?.idProperty }
    val name = bind { item?.nameProperty }
}
