package com.bookstore.Catalog.Controller;

import com.bookstore.Catalog.Entity.Catalog;
import com.bookstore.Catalog.Exceptions.GlobalExceptionController;
import com.bookstore.Catalog.Exceptions.ProductNotFoundException;
import com.bookstore.Catalog.Repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("/products/{code}")
    public ResponseEntity<Catalog> getBookByISBN(@PathVariable String code){
        Catalog catalog = catalogRepository.findByCode(code);
        if(catalog == null){
            throw new ProductNotFoundException("Product with code " + code + " not found");
        }
            return ResponseEntity.ok(catalogRepository.findByCode(code));

    }
    @PostMapping("/products")
    public void addCatalog(@RequestBody Catalog catalog){
        catalogRepository.save(catalog);
    }

    @PutMapping(value = "/products/{code}")
    public ResponseEntity<Catalog> updateCatalog(@RequestBody Catalog catalogDetails, @PathVariable String code){

        Catalog catalog =  catalogRepository.findByCode(code);
        catalog.setCode(catalogDetails.getCode());
        catalog.setDescription(catalogDetails.getDescription());
        catalog.setDiscount(catalogDetails.getDiscount());
        catalog.setName(catalogDetails.getName());
        catalog.setPrice(catalogDetails.getPrice());
        catalog.setImage_url(catalogDetails.getImage_url());
        catalog.setSalePrice(catalogDetails.getSalePrice());
        final Catalog updatedCatalog = catalogRepository.save(catalog);
        return ResponseEntity.ok(updatedCatalog);
    }
}
