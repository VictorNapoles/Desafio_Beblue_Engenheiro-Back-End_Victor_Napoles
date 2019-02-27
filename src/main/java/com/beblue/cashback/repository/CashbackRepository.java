package com.beblue.cashback.repository;

import com.beblue.cashback.entity.Cashback;
import com.beblue.cashback.entity.enums.Genre;
import org.springframework.data.repository.CrudRepository;

public interface CashbackRepository extends CrudRepository<Cashback,Long> {

    Cashback findByDayAndGenre(int day, Genre genre);
}
