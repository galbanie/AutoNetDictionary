package com.github.galbanie.views

import com.github.galbanie.models.Parameter
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-24.
 */
class DictionaryArea : View("Dictionary") {
    override val root = borderpane {
        center{
            stackpane {
                treeview<Parameter> {

                }
            }
        }
    }
}
