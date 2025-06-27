package org.yearup.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int userId;
    private LocalDateTime date;
    private String address;
    private String city;
    private String state;
    private String zip;
    private BigDecimal shippingAmount;
}
