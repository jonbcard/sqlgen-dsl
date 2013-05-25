CREATE <%= unique ? 'UNIQUE ' : '' %>INDEX ${table.tableName}<%= '$' + (suffix ?: columns[0]) %> ON ${table.tableName} (<%= columns.join(', ') %>)
