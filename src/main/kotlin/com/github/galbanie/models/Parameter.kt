package com.github.galbanie.models

import com.github.galbanie.utils.ParameterType
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-31.
 */
class Parameter {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val typeProperty = SimpleObjectProperty<ParameterType>()
    var type by typeProperty

    val fieldProperty = SimpleStringProperty()
    var field by fieldProperty

    val valueProperty = SimpleStringProperty()
    var value by valueProperty
}

class ParameterModel : ItemViewModel<Parameter>() {
    val id = bind(Parameter::idProperty)
    val type = bind(Parameter::typeProperty)
    val field = bind(Parameter::fieldProperty)
    val value = bind(Parameter::valueProperty)
}
