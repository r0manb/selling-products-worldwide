package by.r0manb;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.sql.DataSource;
import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Tasks {
    private final DataSource dataSource;

    public Tasks(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void task1() throws SQLException {
        Map<String, Long> regionToUnits = new HashMap<>();
        try (
            Connection conn = dataSource.getConnection();
            Statement st = conn.createStatement();
        ){
            String sql = """
                    SELECT
                        r.name,
                        SUM(o.units_sold) as total_units
                    FROM regions r
                    LEFT JOIN countries c
                        ON r.id = c.region_id
                    LEFT JOIN orders o
                        ON c.id = o.country_id
                    GROUP BY r.name;
                    """;

            try (ResultSet rs = st.executeQuery(sql)){
                while (rs.next()){
                    regionToUnits.put(
                        rs.getString("name"),
                        rs.getLong("total_units")
                    );
                }
            }
        }

        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Long> e : regionToUnits.entrySet()){
            String region = e.getKey();
            long units = e.getValue();

            categoryDataset.addValue(units, region, region);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Распределение количества продаж по регионам",
            "",
            "",
            categoryDataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Задание 1. График распределения продаж по регионам");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(chart));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
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
