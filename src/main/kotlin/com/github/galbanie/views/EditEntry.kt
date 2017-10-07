package com.github.galbanie.views

import com.github.galbanie.models.Action
import com.github.galbanie.utils.ParameterType
import javafx.util.converter.DefaultStringConverter
import tornadofx.*

/**
 * Created by Galbanie on 2017-09-03.
 */
class EditEntry : Fragment() {
    override fun onUndock() {
        workspace.viewStack.remove(this)
    }
    override val root = borderpane {
        center {
            form {
                fieldset("Input") {
                    field("Type") {
                        combobox<ParameterType> {
                            items.setAll(ParameterType.values().toList())
                        }
                    }
                    field("Field") {
                        textfield {

                        }
                    }
                    field("Value") {
                        textfield {

                        }
                    }
                    field("Actions") {
                        tableview<Action> {
                            isEditable = true
                            column("Name", Action::name).useTextField(DefaultStringConverter())
                            column("Titre", Action::priority)
                        }
                    }
                }
                fieldset("Output - Constraint") {

                }
            }
        }
    }
}
