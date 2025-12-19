package by.r0manb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class Order {
    private String region;
    private String country;
    private String itemType;
    private String salesChannel;
    private String orderPriority;
    private LocalDate orderDate;
    private int unitsSold;
    private BigDecimal totalProfit;

    public Order(
        String region,
        String country,
        String itemType,
        String salesChannel,
        String orderPriority,
        LocalDate orderDate,
        int unitsSold,
        BigDecimal totalProfit
    ) {
        this.region = region;
        this.country = country;
        this.itemType = itemType;
        this.salesChannel = salesChannel;
        this.orderPriority = orderPriority;
        this.orderDate = orderDate;
        this.unitsSold = unitsSold;
        this.totalProfit = totalProfit;
    }

    @Override
    public String toString() {
        return "Order{" +
                "region=" + region +
                ", country=" + country +
                ", itemType=" + itemType +
                ", salesChannel=" + salesChannel +
                ", orderPriority=" + orderPriority +
                ", orderDate=" + orderDate +
                ", unitsSold=" + unitsSold +
                ", totalProfit=" + totalProfit +
                '}';
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getItemType() {
        return itemType;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public String getOrderPriority() {
        return orderPriority;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return unitsSold == order.unitsSold &&
                Objects.equals(region, order.region) &&
                Objects.equals(country, order.country) &&
                Objects.equals(itemType, order.itemType) &&
                Objects.equals(salesChannel, order.salesChannel) &&
                Objects.equals(orderPriority, order.orderPriority) &&
                Objects.equals(orderDate, order.orderDate) &&
                Objects.equals(totalProfit, order.totalProfit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                region,
                country,
                itemType,
                salesChannel,
                orderPriority,
                orderDate,
                unitsSold,
                totalProfit
        );
    }
}
