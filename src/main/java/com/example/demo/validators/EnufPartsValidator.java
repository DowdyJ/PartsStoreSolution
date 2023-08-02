package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null)
            return true;

        myContext=context;

        ProductService repo = myContext.getBean(ProductServiceImpl.class);

        if (product.getId() == 0)
            return true;

        boolean isViolation = false;

        constraintValidatorContext.disableDefaultConstraintViolation();

        Product myProduct = repo.findById((int) product.getId());
        for (Part p : myProduct.getParts()) {
            if (p.getInv() < (product.getInv() - myProduct.getInv())) {
                constraintValidatorContext
                    .buildConstraintViolationWithTemplate(
                    "There aren't enough parts in inventory for part " +
                        p.getName()
                    )
                    .addConstraintViolation();
                isViolation = true;
            }
            else if ((p.getInv() - (product.getInv() - myProduct.getInv())) < p.getMinInventory()) {
                constraintValidatorContext
                    .buildConstraintViolationWithTemplate(
                        "Insufficient part inventory. This would put current inventory for part " +
                            p.getName() +
                            " below minimum acceptable inventory of " +
                            p.getMinInventory()
                        )
                    .addConstraintViolation();

                isViolation = true;
            }
        }

        return !isViolation;
    }
}
