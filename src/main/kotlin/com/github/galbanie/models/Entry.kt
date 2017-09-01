package com.github.galbanie.models

import javafx.beans.property.*
import javafx.collections.FXCollections
import org.joda.time.DateTime
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-31.
 */
class Entry {
    val inputProperty = SimpleObjectProperty<Parameter>()
    var input by inputProperty

    val outputProperty = SimpleListProperty<Parameter>(FXCollections.observableArrayList())
    var output by outputProperty

    val isValidProperty = SimpleBooleanProperty()
    var isValid by isValidProperty

    val creatorProperty = SimpleObjectProperty<User>()
    var creator by creatorProperty

    val reporterProperty = SimpleObjectProperty<User>()
    var reporter by reporterProperty

    val dateCreationProperty = SimpleObjectProperty<DateTime>()
    var dateCreation by dateCreationProperty

    val dateModifiedProperty = SimpleObjectProperty<DateTime>()
    var dateModified by dateModifiedProperty
}