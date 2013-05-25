package org.card.sqlgen.model

import groovy.transform.Canonical
import org.card.sqlgen.TemplateUtils

@Canonical
class Database {

    String schemaName

    List<Table> tables = []

    def createSql() {
        println TemplateUtils.render( './templates/oracle/main.tmpl.sql', [database: this] )
    }

    def createTableSql() {
        tables.collect() { it.createTableSql() }.join( '\n' )
    }

    def createSequenceSql() {
        tables.collect() { it.createSequenceSql() }.join( '\n' )
    }

    def createIndexSql() {
        tables.collect() { it.createIndexesSql() }.join( '\n' )
    }
}
