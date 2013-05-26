package org.card.sqlgen

import groovy.sql.Sql

def db( Closure dbClosure ) {
    return new DatabaseBuilder().make( dbClosure )
}

// Example usage
def database = db {
    table 'EMPLOYEES', {

        pk      'EMPLOYEE_ID',      'NUMBER(6,0)'

        column  'FIRST_NAME',       'VARCHAR2(20 BYTE)'
        column  'LAST_NAME',        'VARCHAR2(25 BYTE)',    'NOT NULL'
        column  'EMAIL',            'VARCHAR2(25 BYTE)'
        column  'PHONE_NUMBER',     'VARCHAR2(20BYTE)'
        column  'HIRE_DATE',        'DATE',                 'NOT NULL'
        column  'SALARY',           'NUMBER(8,2)'
        column  'COMMISSION_PCT',   'NUMBER(2,2)'
    }
}
