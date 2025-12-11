package by.r0manb.db;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLiteDatabase implements Database{
    private DataSource dataSource;

    public SQLiteDatabase(String url){
        this.dataSource = initDataSource(url);
    }

    private DataSource initDataSource(String url){
        SQLiteDataSource dataSource_ = new SQLiteDataSource();
        dataSource_.setUrl(url);

        return dataSource_;
    }

    @Override
    public void createTables() {
        try (
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()
        ) {
            String[] ddl = {
                """
                CREATE TABLE IF NOT EXISTS regions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME TEXT NOT NULL UNIQUE
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS countries (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME TEXT NOT NULL UNIQUE,
                    region_id INTEGER REFERENCES regions(id)
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS item_types (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME TEXT NOT NULL UNIQUE
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS sales_channels (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME TEXT NOT NULL UNIQUE
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS order_priorities (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    NAME TEXT NOT NULL UNIQUE
                );
                """,
                """
                CREATE TABLE IF NOT EXISTS orders (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    country_id INTEGER REFERENCES countries(id),
                    item_type_id INTEGER REFERENCES item_types(id),
                    sales_channel_id INTEGER NOT NULL REFERENCES sales_channels(id),
                    priority_id INTEGER NOT NULL REFERENCES order_priorities(id),
                    order_date TEXT NOT NULL,
                    units_sold INTEGER NOT NULL,
                    total_profit REAL NOT NULL
                )
                """
            };

            for (String sql : ddl){
                st.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void turnOnFK() throws SQLException {
        try (
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement()
        ){
            st.execute("PRAGMA foreign_keys = ON");
        }
    }

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }
}
