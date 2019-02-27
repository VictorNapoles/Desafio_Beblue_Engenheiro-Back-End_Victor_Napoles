package com.beblue.cashback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="sale_items")
public class SaleItem {

    @Id
    @SequenceGenerator(name="sale_items_id_seq",
            sequenceName="sale_items_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="sale_items_id_seq")
    private Long id;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal cashbackValue;

    @Column
    private BigDecimal totalValue;

    @JoinColumn(name = "id_vinyl_disc")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private VinylDisc vinylDisc;

    @JoinColumn(name = "id_cashback")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Cashback cashback;

    @JsonIgnore
    @JoinColumn(name = "id_sale")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sale sale;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCashbackValue() {
        return cashbackValue;
    }

    public void setCashbackValue(BigDecimal cashbackValue) {
        this.cashbackValue = cashbackValue;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public VinylDisc getVinylDisc() {
        return vinylDisc;
    }

    public void setVinylDisc(VinylDisc vinylDisc) {
        this.vinylDisc = vinylDisc;
    }

    public Cashback getCashback() {
        return cashback;
    }

    public void setCashback(Cashback cashback) {
        this.cashback = cashback;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
