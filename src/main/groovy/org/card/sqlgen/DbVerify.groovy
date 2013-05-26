package org.card.sqlgen

import groovy.sql.Sql
import org.card.sqlgen.model.Database
import org.card.sqlgen.model.Table

class DbVerify {

    static testTableSql = '''
        select count(*)
        from   all_tables
        where  table_name = ?
        and    owner = sys_context( 'userenv', 'current_schema' )
        '''

    static testColumnSql = '''
        select count(*)
        from   all_tab_columns
        where  owner = sys_context( 'userenv', 'current_schema' )
        and    table_name = ?
        and    column_name = ?
        '''

    Sql connection
    Database dbDef

    private cacheMissingTables
    private cacheMissingColumns

    /**
     * Returns a list of all missing tables
     */
    def List<Table> missingTables() {
        if( cacheMissingTables != null)
            return cacheMissingTables

        List<Table> missing = []
        dbDef.tables.each { table ->
            def count = connection.firstRow( testTableSql, [table.tableName])[0]
            if( count == 0 )
                missing << table
        }
        cacheMissingTables = missing
        cacheMissingTables
    }

    def missingColumns() {
        if( cacheMissingColumns != null)
            return cacheMissingColumns

        def results = [:]

        def existingTables = dbDef.tables - missingTables()
        existingTables.each { table ->

            def missingCol = []

            table.columns.each { column ->

                def count = connection.firstRow( testColumnSql, [table.tableName, column.name])[0]
                println count
                if( count == 0 )
                    missingCol << column
            }

            if( !missingCol.isEmpty() )  {
                results[table] = missingCol
            }
        }
        cacheMissingColumns = results
        cacheMissingColumns
    }
}
