package by.r0manb.dao;

import by.r0manb.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
}
