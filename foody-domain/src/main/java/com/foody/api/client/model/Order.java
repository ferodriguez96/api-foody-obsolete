package com.foody.api.client.model;

import com.foody.api.client.model.Enums.OrderStatus;

import java.util.List;

public class Order {
    private String id;
    private List<OrderedItem> orderedItems;
    private OrderStatus orderStatus;
}
