package by.r0manb.model;

import java.math.BigDecimal;
import java.time.LocalDate;


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
}
