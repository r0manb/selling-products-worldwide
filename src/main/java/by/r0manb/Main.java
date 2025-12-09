package by.r0manb;

import by.r0manb.model.Order;
import by.r0manb.util.CsvParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URL url = Main.class.getClassLoader().getResource("data.csv");
        String path = Paths.get(url.toURI())
                .toAbsolutePath()
                .toString();

        List<Order> orders = CsvParser.parseCsv(path);
        System.out.println(orders);
    }
}