package org.card.sqlgen

def db( Closure dbClosure ) {
    return new DatabaseBuilder().make( dbClosure )
}

// Example usage
db {
    table 'PERSON', {

        pk      'PERSON_ID',          'NUMBER'

        column  'FIRST_NAME',       'VARCHAR2(40)'
        column  'LAST_NAME',        'NUMBER',       'NOT NULL'

        trigger 'AUDIT'
    }
}.createSql()
