package com.beblue.cashback.controller;

import com.beblue.cashback.entity.VinylDisc;
import com.beblue.cashback.service.VinylDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/vinyl-discs")
public class VinylDiscController {

    @Autowired
    private VinylDiscService service;

    @GetMapping
    public ResponseEntity<Page<VinylDisc>> search(@RequestParam(required = false) String genre,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "name") String sort ){
        try{
            Page<VinylDisc> vinylDiscs = service.findByGenre(genre, page, size, sort);
            HttpStatus status = HttpStatus.OK;

            if(vinylDiscs.getContent().isEmpty()) {
                status = HttpStatus.NO_CONTENT;
            }

            return new ResponseEntity<Page<VinylDisc>>(vinylDiscs, status);
        }catch (IllegalArgumentException e){
            return new ResponseEntity("Genre not found.",HttpStatus.NO_CONTENT);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<VinylDisc> find(@PathVariable("id") Long id){

        try{

            VinylDisc vinylDisc = service.findById(id);

            return new ResponseEntity<VinylDisc>(vinylDisc, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("Vinyl Disc not found.",HttpStatus.NO_CONTENT);
        }


    }
}
