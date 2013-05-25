package org.card.sqlgen.model

import groovy.transform.Canonical

@Canonical
class Column {
    String name, type, modifiers
}
