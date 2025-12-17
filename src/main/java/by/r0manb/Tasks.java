package by.r0manb;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tasks {
    private final DataSource dataSource;

    public Tasks(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void task2() throws SQLException {
        try (
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
        ){
            String sql = """
                    SELECT
                        c.name,
                        SUM(o.total_profit) as total_sum
                    FROM countries c
                    LEFT JOIN regions r
                        ON c.region_id = r.id
                    LEFT JOIN orders o
                        ON c.id = o.country_id
                    WHERE r.name IN ('Asia', 'Europe')
                    GROUP BY c.NAME
                    ORDER BY total_sum DESC
                    LIMIT 1;
                    """;

            System.out.println("------ Задание 2 ------");
            try (ResultSet rs = st.executeQuery(sql)){
                if (rs.next()){
                    String name = rs.getString("name");
                    BigDecimal totalSum = rs.getBigDecimal("total_sum");

                    System.out.printf("%s: %.2f\n\n", name, totalSum);
                }
            }
        }
    }

    public void task3() throws SQLException {
        try (
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
        ){
            String sql = """
                    SELECT
                        c.NAME,
                        SUM(o.total_profit) as total_sum
                    FROM countries c
                    LEFT JOIN regions r
                          ON c.region_id = r.id
                    LEFT JOIN orders o
                        ON c.id = o.country_id
                    WHERE r.name IN ('Middle East and North Africa', 'Sub-Saharan Africa')
                    GROUP BY c.NAME
                    HAVING total_sum BETWEEN 420000 AND 440000
                    ORDER BY total_sum DESC
                    LIMIT 1;
                    """;

            System.out.println("------ Задание 3 ------");
            try (ResultSet rs = st.executeQuery(sql)){
                if (rs.next()){
                    String name = rs.getString("name");
                    BigDecimal totalSum = rs.getBigDecimal("total_sum");

                    System.out.printf("%s: %f.2\n\n", name, totalSum);
                }else {
                    System.out.println("Страны по заданным условиям не найдено!\n");
                }
            }
        }
    }
}
