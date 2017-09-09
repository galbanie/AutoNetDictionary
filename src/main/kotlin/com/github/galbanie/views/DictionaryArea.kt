package com.github.galbanie.views

import com.github.galbanie.models.EntryModel
import com.github.galbanie.models.Parameter
import javafx.scene.control.TreeItem
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-24.
 */
class DictionaryArea : View("Dictionary") {
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
