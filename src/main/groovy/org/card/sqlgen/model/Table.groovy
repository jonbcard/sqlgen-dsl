package org.card.sqlgen.model

import org.card.sqlgen.TemplateUtils
import groovy.transform.Canonical

@Canonical
class Table {
    String tableName
    List<Column> pk = [], columns = []
    List<String> triggers = []

    def createSql() {
        TemplateUtils.render( './templates/oracle/table.tmpl.sql', this.properties )
    }
}
