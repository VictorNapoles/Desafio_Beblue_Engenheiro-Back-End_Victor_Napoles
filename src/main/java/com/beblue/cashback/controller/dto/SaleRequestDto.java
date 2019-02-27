package com.beblue.cashback.controller.dto;

import java.util.List;

public class SaleRequestDto {
    private List<SaleItemRequestDto> items;


    public List<SaleItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<SaleItemRequestDto> items) {
        this.items = items;
    }
}
