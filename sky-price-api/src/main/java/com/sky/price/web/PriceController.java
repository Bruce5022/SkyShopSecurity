package com.sky.price.web;

import com.sky.price.model.PriceInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/price")
public class PriceController {

    @GetMapping("/{id}")
    public PriceInfo getPrice(@PathVariable Long id){

        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setId(id);
        priceInfo.setPrice(new BigDecimal("12.5"));
        return priceInfo;
    }

}
