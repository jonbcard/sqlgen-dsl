CREATE TABLE ${tableName}
(<% pk.each() { col -> %>
, ${col.name} ${col.type} ${col.modifiers}<% } %><% columns.each() { col -> %>
, ${col.name} ${col.type} ${col.modifiers}<% } %>
, CONSTRAINT PK_${tableName} PRIMARY KEY (<%= pk['name'].join(', ') %>)
  ENABLE
);
