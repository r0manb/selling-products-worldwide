package by.r0manb.db;

import javax.sql.DataSource;


public interface Database {
    DataSource getDatasource();
    void createTables();
}
