package com.beblue.cashback.repository;

import com.beblue.cashback.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

    @Query("select s from Sale s where s.saleDate between :startDate and :endDate")
    Page<Sale> findBetweenDates(@Param("startDate")Date startDate, @Param("endDate")Date endDate, Pageable pageable);

}
