package org.card.sqlgen.model

import groovy.transform.Canonical

@Canonical
class Database {
    List<Table> tables = []

    def createSql() {
        tables.each() { table ->
            println table.createSql()
        }
    }
}
