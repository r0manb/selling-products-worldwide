package by.r0manb;

import by.r0manb.config.DbConfig;
import by.r0manb.db.SQLiteDatabase;
import by.r0manb.model.Order;
import by.r0manb.util.CsvParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        createTables();
    }

    private static void createTables() throws SQLException {
        SQLiteDatabase database = new SQLiteDatabase(DbConfig.getUrl());
        database.turnOnFK();
        database.createTables();
    }

    private void parseData() throws IOException, URISyntaxException {
        URL url = Main.class.getClassLoader().getResource("data.csv");
        String path = Paths.get(url.toURI())
                .toAbsolutePath()
                .toString();

        List<Order> orders = CsvParser.parseCsv(path);
        System.out.println(orders);
    }
}