package com.beblue.cashback.service.impl;

import com.beblue.cashback.entity.Cashback;
import com.beblue.cashback.entity.Sale;
import com.beblue.cashback.entity.SaleItem;
import com.beblue.cashback.entity.VinylDisc;
import com.beblue.cashback.entity.enums.Genre;
import com.beblue.cashback.repository.CashbackRepository;
import com.beblue.cashback.repository.SaleRepository;
import com.beblue.cashback.repository.VinylDiscRepository;
import com.beblue.cashback.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository repository;

    @Autowired
    private CashbackRepository cashbackRepository;

    @Autowired
    private VinylDiscRepository vinylDiscRepository;

    @Override
    public Page<Sale> findBetweenDates(Date startDate, Date endDate, int page, int size, String sort) {

        Sort s = Sort.by(Sort.Direction.DESC, sort);
        PageRequest pr = PageRequest.of(page, size, s);

        if(startDate == null || endDate == null){
            return repository.findAll(pr);
        }

        return repository.findBetweenDates(startDate,endDate,pr);
    }

    @Override
    public Sale findById(Long id) {
        Optional<Sale> sale = repository.findById(id);
        return sale.get();
    }

    @Override
    public Sale create(Sale sale) {

        if(sale.getItems() != null){
            LocalDate localDate = LocalDate.now();
            int dayOfWeek = localDate.getDayOfWeek().ordinal();

             sale.setTotalValue(new BigDecimal(0));
            sale.setCashbackValue(new BigDecimal(0));
            sale.setSaleDate(new Date());

            sale.getItems().forEach(saleItem -> {

                VinylDisc disc = vinylDiscRepository.findById(saleItem.getVinylDisc().getId()).get();
                Genre genre = disc.getGenre();
                Cashback cashback = cashbackRepository.findByDayAndGenre(dayOfWeek,genre);

                saleItem.setVinylDisc(disc);
                saleItem.setCashback(cashback);
                saleItem.setTotalValue(disc.getValue().multiply(BigDecimal.valueOf(saleItem.getQuantity())));
                saleItem.setCashbackValue(getCashbackValue(saleItem,cashback));

                sale.setTotalValue(sale.getTotalValue().add(saleItem.getTotalValue()));
                sale.setCashbackValue(sale.getCashbackValue().add(saleItem.getCashbackValue()));

                saleItem.setSale(sale);

            });
        }

        return repository.save(sale);
    }

    private BigDecimal getCashbackValue(SaleItem saleItem, Cashback cashback){
        BigDecimal itemValue = saleItem.getTotalValue();
        BigDecimal cashbackPercentage = BigDecimal.valueOf(cashback.getPercentage());
        BigDecimal decimalCashbackPercentage = cashbackPercentage.divide(new BigDecimal(100));
        BigDecimal result =  itemValue.multiply(decimalCashbackPercentage);

        return result;
    }
}