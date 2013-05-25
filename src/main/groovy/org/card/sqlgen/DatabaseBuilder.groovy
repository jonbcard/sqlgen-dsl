package org.card.sqlgen

import org.card.sqlgen.model.Column
import org.card.sqlgen.model.Database
import org.card.sqlgen.model.Index
import org.card.sqlgen.model.Table

class DatabaseBuilder {

    class TableBuilder {

        Table table

        // DSL-keyword shortcuts
        boolean unique = true

        Table make( String name, Closure tableDef ) {
            table = new Table()
            table.tableName = name

            runClosure tableDef

            return table
        }

        def pk( String name, String type="NUMBER", String modifiers="NOT NULL" ) {
            table.pk << new Column( name: name, type: type, modifiers: modifiers )
        }

        def column( String name, String type, String modifiers="" ) {
            table.columns << new Column( name: name, type: type, modifiers: modifiers )
        }

        def index( boolean unique=false, String ... columns ) {
            table.indexes << new Index( columns: columns, unique: unique, table: table )
        }

        def trigger( String name ) {
            table.triggers << name
        }

        def propertyMissing( String name ) {
            if( name in ['audit', 'replicate'] ) {
                table."$name" = true
            }
        }

        private runClosure( Closure runClosure ) {
            Closure runClone = runClosure.clone() // make this thread safe
            runClone.delegate = this
            runClone.resolveStrategy = Closure.DELEGATE_ONLY
            runClone()
        }
    }


    TableBuilder builder = new TableBuilder()
    Database db

    def table( String name, Closure tableDef ) {
        db.tables << builder.make( name, tableDef )
    }

    Database make( Closure dbDef ) {
        db = new Database()
        runClosure dbDef

        return db
    }

    private runClosure( Closure runClosure ) {
        Closure runClone = runClosure.clone() // make this thread safe
        runClone.delegate = this
        runClone.resolveStrategy = Closure.DELEGATE_ONLY
        runClone()
    }
}
