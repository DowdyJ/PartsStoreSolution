package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class BuyProductController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/buyproduct")
    public String buyProduct(@Valid @RequestParam("productID") int theId,  Model theModel) {

        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product=productService.findById(theId);


        if (product.getInv() <= 0) {
            return "buyNegativeError";
        }
        else {
            product.setInv(product.getInv() - 1);
            productService.save(product);
            return "buySuccess";
        }
    }
}
