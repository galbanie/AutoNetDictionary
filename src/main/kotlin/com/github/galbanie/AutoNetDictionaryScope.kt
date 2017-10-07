package com.github.galbanie

import com.github.galbanie.models.*
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
                input = ParameterAction().apply {
                    parameter = Parameter().apply {
                        type = ParameterType.INPUT
                        field = ""
                        value = "abs"
                        actions.addAll(Action().apply {
                            name = "Only issue"
                        }, Action().apply {
                            name = "Put Down"
                        })
                    }
                }
                constraintOutputs.addAll(
                    ParameterAction().apply {
                        parameter = Parameter().apply{
                            type = ParameterType.OUTPUT
                            field = "BrakeABS"
                            value = "4-wheelABS"
                        }
                    },
                    ParameterAction().apply {
                        parameter = Parameter().apply{
                            type = ParameterType.OUTPUT
                            field = "BrakeABS"
                            value = "2-wheelABS"
                        }
                    },
                    ParameterAction().apply {
                        parameter = Parameter().apply{
                            type = ParameterType.CONSTRAINT
                            field = "section"
                            value = "Electrical"
                        }
                    }
                )
            },
            Entry().apply {
                input = ParameterAction().apply {
                    parameter = Parameter().apply {
                        type = ParameterType.INPUT
                        field = "Note"
                        value = "drum"
                    }
                }
                constraintOutputs.addAll(
                        ParameterAction().apply {
                            parameter = Parameter().apply{
                                type = ParameterType.OUTPUT
                                field = "Product Line"
                                value = "Brake Drum"
                            }
                            actions.add(Action().apply {
                                name = "Only issue"
                            })
                        },
                        ParameterAction().apply {
                            parameter = Parameter().apply{
                                type = ParameterType.OUTPUT
                                field = "RearBrakeType"
                                value = "Drum"
                            }
                        }
                )
            }
    )
}