package com.beblue.cashback.service;

import com.beblue.cashback.entity.Sale;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface SaleService {

    Page<Sale> findBetweenDates(Date startDate, Date endDate, int page, int size, String sort);

    Sale findById(Long id);

    Sale create(Sale sale);
}
