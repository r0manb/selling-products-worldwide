import by.r0manb.config.DbConfig;
import by.r0manb.dao.OrderDao;
import by.r0manb.db.SQLiteDatabase;
import by.r0manb.model.Order;
import by.r0manb.util.CsvParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class CsvToDatabaseConsistencyTest {

    @Test
    public void testCsvAndDatabaseDataShouldMatch()
            throws SQLException, URISyntaxException, IOException {
        URL url = CsvToDatabaseConsistencyTest.class.getClassLoader().getResource("data.csv");
        String path = Paths.get(url.toURI())
                .toAbsolutePath()
                .toString();
        
        List<Order> ordersFromCsv = CsvParser.parseCsv(path);
        List<Order> ordersFromDb;

        DataSource dataSource = new SQLiteDatabase(DbConfig.getUrl()).getDatasource();
        try (Connection conn = dataSource.getConnection()){
            ordersFromDb = OrderDao.getAll(conn);
        }

        assertEquals(ordersFromCsv, ordersFromDb);
    }
}
