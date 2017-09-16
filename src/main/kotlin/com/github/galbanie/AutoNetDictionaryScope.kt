package com.github.galbanie

import com.github.galbanie.models.Action
import com.github.galbanie.models.Entry
import com.github.galbanie.models.Parameter
import com.github.galbanie.models.Session
import com.github.galbanie.utils.ParameterType
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-20.
 */
class AutoNetDictionaryScope : Scope() {

    val sessionProperty = SimpleObjectProperty<Session>()
    val session : Session? by sessionProperty

    val actions = FXCollections.observableArrayList<Action>()
    val entries = FXCollections.observableArrayList<Entry>(
            Entry().apply {
                input = Parameter().apply {
                    type = ParameterType.INPUT
                    field = ""
                    value = "abs"
                }
                output.addAll(
                    Parameter().apply {
                        type = ParameterType.OUTPUT
                        field = "BrakeABS"
                        value = "4-wheelABS"
                    },
                    Parameter().apply {
                        type = ParameterType.OUTPUT
                        field = "BrakeABS"
                        value = "2-wheelABS"
                    }
                )
            },
            Entry().apply {
                input = Parameter().apply {
                    type = ParameterType.INPUT
                    field = "Product Line"
                    value = "Brake Drum"
                }
                output.addAll(
                    Parameter().apply {
                        type = ParameterType.OUTPUT
                        field = "RearBrakeType"
                        value = "Drum"
                    }
                )
            }
    )
}