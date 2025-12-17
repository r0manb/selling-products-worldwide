package by.r0manb;

import by.r0manb.config.DbConfig;
import by.r0manb.db.Database;
import by.r0manb.db.SQLiteDatabase;
import by.r0manb.model.Order;
import by.r0manb.util.CsvParser;
import by.r0manb.dao.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args)
            throws SQLException, IOException, URISyntaxException {
        SQLiteDatabase database = new SQLiteDatabase(DbConfig.getUrl());
        database.turnOnFK();

//        createTablesAndInsertFromCsv(database);

        Tasks tasks = new Tasks(database.getDatasource());
        tasks.task2();
        tasks.task3();
    }

    private static void createTablesAndInsertFromCsv(Database database)
            throws IOException, URISyntaxException {
        database.createTables();

        List<Order> data = parseData();
        insertData(database.getDatasource(), data);
    }

    private static List<Order> parseData()
            throws IOException, URISyntaxException {
        URL url = Main.class.getClassLoader().getResource("data.csv");
        String path = Paths.get(url.toURI())
                .toAbsolutePath()
                .toString();

        return CsvParser.parseCsv(path);
    }

    private static void insertData(DataSource dataSource, List<Order> data) {
        try (Connection conn = dataSource.getConnection()) {
            for(Order order : data){
                long regionId = RegionDao.getIdOrCreate(conn, order.getRegion());
                long countryId = CountryDao.getIdOrCreate(conn, order.getCountry(), regionId);
                long itemTypeId = ItemTypeDao.getIdOrCreate(conn, order.getItemType());
                long priorityId = OrderPrioritiesDao.getIdOrCreate(conn, order.getOrderPriority());
                long channelId = SalesChannelDao.getIdOrCreate(conn, order.getSalesChannel());

                OrderDao.insert(
                        conn,
                        countryId,
                        itemTypeId,
                        channelId,
                        priorityId,
                        order
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}