package com.github.galbanie.views

import com.github.galbanie.EntryListFound
import com.github.galbanie.EntryListRequest
import com.github.galbanie.models.EntryModel
import com.github.galbanie.models.Parameter
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
                treeview<Parameter> {
                    root = TreeItem()
                    isShowRoot = false
                    cellFormat { param ->
                        graphic = label(param.value) {
                            useMaxWidth = true
                            //addEventFilter(MouseEvent.MOUSE_CLICKED){center(ref.kclass as KClass<UIComponent>)}
                        }
                    }
                    subscribe<EntryListFound> { event ->
                        populate { parent ->
                            if (parent == root ) event.entries.map { it.input } else event.entries.find { it.input.equals(parent.value) }!!.output
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
