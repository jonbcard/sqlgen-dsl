package org.card.sqlgen.model

import groovy.transform.Canonical
import org.card.sqlgen.TemplateUtils

@Canonical
class Sequence {
    Table table

    def createSequenceSql() {
        TemplateUtils.render( './templates/oracle/sequence.tmpl.sql', this.properties )
    }
}
