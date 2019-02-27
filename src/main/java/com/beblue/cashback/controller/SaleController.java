package com.beblue.cashback.controller;

import com.beblue.cashback.controller.dto.SaleRequestDto;
import com.beblue.cashback.entity.Sale;
import com.beblue.cashback.entity.SaleItem;
import com.beblue.cashback.entity.VinylDisc;
import com.beblue.cashback.service.SaleService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService service;

   @GetMapping
   public ResponseEntity<Page<Sale>> search(@RequestParam(required = false) @ApiParam(value = "Format: yyyy-MM-dd HH:mm") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date startDate,
                           @RequestParam(required = false) @ApiParam(value = "Format: yyyy-MM-dd HH:mm") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")  Date endDate,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "saleDate") String sort ){

       try{
           Page<Sale> sales = service.findBetweenDates(startDate, endDate, page, size, sort);
           HttpStatus status = HttpStatus.OK;

           if(sales.getContent().isEmpty()) {
               status = HttpStatus.NO_CONTENT;
           }

           return new ResponseEntity<Page<Sale>>(sales, status);
       }catch (IllegalArgumentException e){
           return new ResponseEntity("Sales not found.",HttpStatus.NO_CONTENT);
       }
   }

   @GetMapping("/{id}")
   public ResponseEntity<Sale> find(@PathVariable("id") Long id){

       try{

           Sale sale = service.findById(id);

           return new ResponseEntity<Sale>(sale, HttpStatus.OK);
       }catch (NoSuchElementException e){
           return new ResponseEntity("Sale not found.",HttpStatus.NO_CONTENT);
       }
    }

    @PostMapping
    public ResponseEntity sale(@RequestBody SaleRequestDto dto){

       Sale sale = new Sale();

       if(dto.getItems() != null){

           List<SaleItem> saleItems = new ArrayList<>();

           dto.getItems().forEach(item -> {

               VinylDisc vinylDisc = new VinylDisc();
               vinylDisc.setId(item.getIdVinylDisc());

               SaleItem saleItem = new SaleItem();
               saleItem.setQuantity(item.getQuantity());
               saleItem.setVinylDisc(vinylDisc);
               saleItems.add(saleItem);
           });

           sale.setItems(saleItems);
       }else{
           return new ResponseEntity("Items not found on sale.",HttpStatus.UNPROCESSABLE_ENTITY);
       }

       service.create(sale);

       return new ResponseEntity(HttpStatus.CREATED);
    }
}
