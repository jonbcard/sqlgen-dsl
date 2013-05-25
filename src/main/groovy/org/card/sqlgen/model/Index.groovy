package org.card.sqlgen.model

import groovy.transform.Canonical
import org.card.sqlgen.TemplateUtils

@Canonical
class Index {
    Table table
    String suffix
    boolean unique
    List<String> columns

    def createIndexSql() {
        TemplateUtils.render( './templates/oracle/index.tmpl.sql', this.properties + [table: table] )
    }
}
