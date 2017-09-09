package com.github.galbanie

import com.github.galbanie.controllers.EventController
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.*

class AutoNetDictionaryApp : App(AutoNetDictionaryWorkspace::class, Styles::class, AutoNetDictionaryScope()){
    init {
        find<EventController>(scope)
    }
    override fun start(stage: Stage) {
        super.start(stage)
        //FX.primaryStage.icons += resources.image("/checkmark_on_circle.png")
    }

    override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1024.0, 768.0)
}

/**
 * The main method is needed to support the mvn jfx:run goal.
 */
fun main(args: Array<String>) {
    Application.launch(AutoNetDictionaryApp::class.java, *args)
}