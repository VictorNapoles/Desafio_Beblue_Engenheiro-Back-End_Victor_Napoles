package com.beblue.cashback.controller.dto;

public class SaleItemRequestDto {

    private Long idVinylDisc;
    private Integer quantity;


    public Long getIdVinylDisc() {
        return idVinylDisc;
    }

    public void setIdVinylDisc(Long idVinylDisc) {
        this.idVinylDisc = idVinylDisc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
