CREATE TABLE ${tableName}
(<% pk.each() { col -> %>
, ${col.name} ${col.type} ${col.modifiers}<% } %><% columns.each() { col -> %>
, ${col.name} ${col.type} ${col.modifiers}<% } %>
, CONSTRAINT ${tableName}_PK PRIMARY KEY (<% pk.each() { col -> %>, ${col.name}<% } %>)
  ENABLE
);