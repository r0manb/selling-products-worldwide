package by.r0manb.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DbConfig {
    private static Properties props = new Properties();

    static {
        try (InputStream is = DbConfig.class
                .getClassLoader()
                .getResourceAsStream("db.properties")
        ) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }
}
