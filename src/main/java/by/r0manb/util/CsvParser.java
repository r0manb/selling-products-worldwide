package by.r0manb.util;

import by.r0manb.model.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CsvParser {

    public static List<Order> parseCsv(String path) throws IOException {
        List<Order> orders = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Path.of(path))){
            try (CSVReader csvReader = new CSVReader(reader)){
                String[] line;
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null){
                    orders.add(new Order(
                            Region.fromLabel(line[0]),
                            line[1],
                            ItemType.fromLabel(line[2]),
                            ChannelStatus.fromLabel(line[3]),
                            OrderPriority.valueOf(line[4]),
                            DateParser.parseString(line[5]),
                            Integer.parseInt(line[6]),
                            new BigDecimal(line[7])
                    ));
                }
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }

        return orders;
    }
}
