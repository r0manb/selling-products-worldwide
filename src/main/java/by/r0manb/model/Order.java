package by.r0manb.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private Region region;
    private String country;
    private ItemType itemType;
    private ChannelStatus salesChannel;
    private OrderPriority orderPriority;
    private LocalDate orderDate;
    private int unitsSold;
    private BigDecimal totalProfit;

    public Order(
        Region region,
        String country,
        ItemType itemType,
        ChannelStatus salesChannel,
        OrderPriority orderPriority,
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
                ", country='" + country + '\'' +
                ", itemType=" + itemType +
                ", salesChannel=" + salesChannel +
                ", orderPriority=" + orderPriority +
                ", orderDate=" + orderDate +
                ", unitsSold=" + unitsSold +
                ", totalProfit=" + totalProfit +
                '}';
    }

    public Region getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ChannelStatus getSalesChannel() {
        return salesChannel;
    }

    public OrderPriority getOrderPriority() {
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
