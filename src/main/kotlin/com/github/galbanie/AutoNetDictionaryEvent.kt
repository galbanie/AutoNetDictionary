package com.github.galbanie

import com.github.galbanie.models.Entry
import tornadofx.*

/**
 * Created by Galbanie on 2017-09-09.
 */

// Events Entry
object EntryListRequest : FXEvent(EventBus.RunOn.BackgroundThread)
class EntryListFound(val entries : List<Entry>) : FXEvent()
class EntryCreated(val entry : Entry) : FXEvent()
class EntryUpdated(val entry : Entry) : FXEvent()
class EntryRemoved(val entry : Entry) : FXEvent()