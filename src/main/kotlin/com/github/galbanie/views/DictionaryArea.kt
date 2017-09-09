package com.github.galbanie.views

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
                    cellFormat {

                    }
                    /*populate { parent ->
                        if (parent == root )
                    }*/
                }
            }
        }
    }
}
