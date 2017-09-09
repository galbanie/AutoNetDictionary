package com.github.galbanie.views

import tornadofx.*

/**
 * Created by Galbanie on 2017-09-03.
 */
class NewEntry : Fragment("New Entry") {
    override fun onUndock() {
        workspace.viewStack.remove(this)
    }
    override val root = borderpane {

    }
}
