package com.github.galbanie.controllers

import com.github.galbanie.AutoNetDictionaryScope
import com.github.galbanie.EntryListFound
import com.github.galbanie.EntryListRequest
import tornadofx.*

/**
 * Created by Galbanie on 2017-09-09.
 */
class EventController : Controller() {
    override val scope  = super.scope as AutoNetDictionaryScope
    init {
        // Entry Events
        subscribe<EntryListRequest> {
            fire(EntryListFound(scope.entries))
        }
    }
}