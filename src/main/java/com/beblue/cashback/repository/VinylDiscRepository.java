package com.beblue.cashback.repository;

import com.beblue.cashback.entity.VinylDisc;
import com.beblue.cashback.entity.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VinylDiscRepository extends PagingAndSortingRepository<VinylDisc, Long> {

    Page<VinylDisc> findByGenre(Genre genre, Pageable pageable);


}
