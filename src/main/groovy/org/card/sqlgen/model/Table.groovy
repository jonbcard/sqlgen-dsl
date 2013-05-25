package org.card.sqlgen.model

import org.card.sqlgen.TemplateUtils
import groovy.transform.Canonical

@Canonical
class Table {
    String tableName
    List<Column> pk = [], columns = []
    List<Index> indexes = []
    List<String> triggers = []


    def createTableSql() {
        TemplateUtils.render( './templates/oracle/table.tmpl.sql', this.properties )
    }

    def createIndexesSql() {
        indexes.collect { index -> index.createIndexSql() }.join( '\n' )
    }

    def createSequenceSql() {
        new Sequence( table: this ).createSequenceSql()
    }
}
