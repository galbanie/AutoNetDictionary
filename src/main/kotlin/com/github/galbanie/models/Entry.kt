package com.github.galbanie.models

import javafx.beans.property.*
import javafx.collections.FXCollections
import org.joda.time.DateTime
import tornadofx.*

/**
 * Created by Galbanie on 2017-08-31.
 */
class Entry {
    val inputProperty = SimpleObjectProperty<ParameterAction>()
    var input by inputProperty

    val constraintOutputsProperty = SimpleListProperty<ParameterAction>(FXCollections.observableArrayList())
    var constraintOutputs by constraintOutputsProperty

    val isConfirmProperty = SimpleBooleanProperty()
    var isConfirm by isConfirmProperty

    val creatorProperty = SimpleObjectProperty<User>()
    var creator by creatorProperty

    val reporterProperty = SimpleObjectProperty<User>()
    var reporter by reporterProperty

    val dateCreationProperty = SimpleObjectProperty<DateTime>()
    var dateCreation by dateCreationProperty

    val dateModifiedProperty = SimpleObjectProperty<DateTime>()
    var dateModified by dateModifiedProperty
}

class EntryModel : ItemViewModel<Entry>() {
    val input = bind(Entry::inputProperty)
    val output = bind(Entry::constraintOutputsProperty)
    val isConfirm = bind(Entry::isConfirmProperty)
    val creator = bind(Entry::creatorProperty)
    val reporter = bind(Entry::reporterProperty)
    val dateCreation = bind(Entry::dateCreationProperty)
    val dateModified = bind(Entry::dateModifiedProperty)
}
