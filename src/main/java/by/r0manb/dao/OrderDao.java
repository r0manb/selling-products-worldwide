package by.r0manb.dao;

import by.r0manb.model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderDao {
    public static void insert(
        Connection conn,
        long countryId,
        long itemTypeId,
        long channelId,
        long priorityId,
        Order order
    ) throws SQLException {
        String insert = """
            INSERT INTO orders(
                country_id, item_type_id, sales_channel_id, priority_id,
                order_date, units_sold, total_profit
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement ps = conn.prepareStatement(insert)){
            ps.setLong(1, countryId);
            ps.setLong(2, itemTypeId);
            ps.setLong(3, channelId);
            ps.setLong(4, priorityId);
            ps.setString(5, order.getOrderDate().toString());
            ps.setInt(6, order.getUnitsSold());
            ps.setBigDecimal(7, order.getTotalProfit());

            ps.executeUpdate();
        }
    }

    public static List<Order> getAll(Connection conn) {
        List<Order> orders = new ArrayList<>();

        String sql = """
                SELECT
                    r.name  AS region,
                    c.name  AS country,
                    it.name AS item_type,
                    sc.name AS sales_channel,
                    op.name AS order_priority,
                    o.order_date,
                    o.units_sold,
                    o.total_profit
                FROM orders o
                JOIN countries c       ON o.country_id = c.id
                JOIN regions r         ON c.region_id = r.id
                JOIN item_types it     ON o.item_type_id = it.id
                JOIN sales_channels sc ON o.sales_channel_id = sc.id
                JOIN order_priorities op ON o.priority_id = op.id
                """;

        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()){
                orders.add(new Order(
                        rs.getString("region"),
                        rs.getString("country"),
                        rs.getString("item_type"),
                        rs.getString("sales_channel"),
                        rs.getString("order_priority"),
                        LocalDate.parse(rs.getString("order_date")),
                        rs.getInt("units_sold"),
                        rs.getBigDecimal("total_profit").setScale(2)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }
}
