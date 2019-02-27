package com.beblue.cashback.service;

import com.beblue.cashback.entity.VinylDisc;
import org.springframework.data.domain.Page;

public interface VinylDiscService {

    Page<VinylDisc> findByGenre(String genre, int page, int size, String sort);

    VinylDisc findById(Long id);
}
