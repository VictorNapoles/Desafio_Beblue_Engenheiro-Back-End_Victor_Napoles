package com.beblue.cashback.service.impl;

import com.beblue.cashback.entity.VinylDisc;
import com.beblue.cashback.entity.enums.Genre;
import com.beblue.cashback.repository.VinylDiscRepository;
import com.beblue.cashback.service.VinylDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VinylDiscServiceImpl implements VinylDiscService {

    @Autowired
    private VinylDiscRepository repository;

    @Override
    public Page<VinylDisc> findByGenre(String genre, int page, int size, String sort) {

        Sort s = Sort.by(Sort.Direction.ASC, sort);
        PageRequest pr = PageRequest.of(page, size, s);

        if(genre == null || genre.isEmpty()){
            return repository.findAll(pr);
        }

        Genre g = Genre.valueOf(genre);

        return repository.findByGenre(g,pr);
    }

    @Override
    public VinylDisc findById(Long id) {

        Optional<VinylDisc> vinylDisc = repository.findById(id);

        return vinylDisc.get();
    }
}
