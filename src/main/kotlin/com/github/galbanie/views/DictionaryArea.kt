package com.github.galbanie.views

import com.github.galbanie.EntryListFound
import com.github.galbanie.EntryListRequest
import com.github.galbanie.models.EntryModel
import com.github.galbanie.models.Parameter
import com.github.galbanie.utils.ParameterType
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.scene.control.TreeItem
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
        center{
            stackpane {
                treeview<Any> {
                    root = TreeItem()
                    isShowRoot = false
                    cellFormat { param ->
                        /*graphic = label(param.value) {
                            useMaxWidth = true
                            //addEventFilter(MouseEvent.MOUSE_CLICKED){center(ref.kclass as KClass<UIComponent>)}
                        }*/
                    }
                    subscribe<EntryListFound> { event ->
                        populate { parent ->
                            //if (parent == root ) event.entries.map { it.input } else event.entries.find { it.input.equals(parent.value) }!!.output
                            val value = parent.value
                            if (parent == root) event.entries.map { it.input }
                            else if (value is Parameter && value.type.equals(ParameterType.INPUT)) event.entries.find { it.input.equals(parent.value) }!!.output
                            else null
                        }
                    }
                    /*populate { parent ->
                        if (parent == root )
                    }*/
                }
            }
        }
    }
}
