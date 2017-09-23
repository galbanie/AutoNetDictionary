package com.github.galbanie.views

import com.github.galbanie.EntryListFound
import com.github.galbanie.EntryListRequest
import com.github.galbanie.models.Action
import com.github.galbanie.models.EntryModel
import com.github.galbanie.models.Parameter
import com.github.galbanie.models.ParameterAction
import com.github.galbanie.utils.ParameterType
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.scene.control.TreeItem
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-24.
 */
class DictionaryArea : View("Dictionary") {

    override fun onDock() {
        fire(EntryListRequest)
        with(workspace){
            button {
                addClass("icon-only")
                graphic = FontAwesomeIconView(FontAwesomeIcon.PLUS_CIRCLE).apply {
                    style {
                        fill = c("#818181")
                    }
                    glyphSize = 20
                }
                action { dock<NewEntry>() }
            }
        }
    }

    val entryModel : EntryModel by inject()
    override val root = borderpane {
        left{

        }
        center{
            stackpane {
                treeview<Any> {
                    root = TreeItem()
                    isShowRoot = false
                    cellFormat {
                        /*graphic = label(param.value) {
                            useMaxWidth = true
                            //addEventFilter(MouseEvent.MOUSE_CLICKED){center(ref.kclass as KClass<UIComponent>)}
                        }*/
                        graphic = when (it) {
                            is ParameterAction -> hbox(10) {
                                label {
                                    style {
                                        fontWeight = FontWeight.BOLD
                                    }
                                    text = if(!it.parameterProperty.value.fieldProperty.value.isEmpty())it.parameterProperty.value.fieldProperty.value else "*"
                                }
                                label {
                                    style {
                                        //fontWeight = FontWeight.SEMI_BOLD
                                    }
                                    text = it.parameterProperty.value.valueProperty.value
                                }
                            }
                            is Action -> label {
                                style {
                                    textFill = Color.BLUE
                                }
                                text = it.nameProperty.value
                            }
                            else -> throw IllegalArgumentException("Invalid value type")
                        }
                    }
                    subscribe<EntryListFound> { event ->
                        populate { parent ->
                            //if (parent == root ) event.entries.map { it.input } else event.entries.find { it.input.equals(parent.value) }!!.output
                            val value = parent.value
                            val misc = arrayListOf<Any>()
                            if (parent == root) event.entries.map { it.input }
                            else if (value is ParameterAction && value.parameter.type.equals(ParameterType.INPUT)) {
                                misc.addAll(event.entries.find { it.input.equals(value) }!!.outputs)
                                misc.addAll(value.actions)
                                misc
                            }
                            else if (value is ParameterAction && value.parameter.type.equals(ParameterType.OUTPUT)) {
                                value.actions
                            }
                            else null
                        }
                    }
                    /*populate { parent ->
                        if (parent == root )
                    }*/
                }
            }
        }
        right {
            
        }
    }
}
