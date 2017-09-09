package com.github.galbanie.views

import com.github.galbanie.models.Parameter
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
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

    override val root = borderpane {
        center{
            stackpane {
                treeview<Parameter> {

                }
            }
        }
    }
}
