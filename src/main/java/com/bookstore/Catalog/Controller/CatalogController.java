package com.bookstore.Catalog.Controller;

import com.bookstore.Catalog.Entity.Catalog;
import com.bookstore.Catalog.Exceptions.ProductNotFoundException;
import com.bookstore.Catalog.Repository.CatalogRepository;
import com.bookstore.Catalog.Response.PageResult;
import com.bookstore.Catalog.Service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CatalogController {

    @Autowired
    private CatalogRepository catalogRepository;

    final CatalogService catalogService;


    @GetMapping("/products")

    public @ResponseBody PageResult getBooksbyPageNumber(@RequestParam(name = "page", defaultValue = "0")int pageNumber){
        return catalogService.getBooksByPage(pageNumber);
    }
    @GetMapping("/products/{code}")
    public ResponseEntity<Catalog> getBookByISBN(@PathVariable String code){
        Catalog catalog = catalogRepository.findByCode(code);
        if(catalog == null){
            throw new ProductNotFoundException("Product with code " + code + " not found");
        }
            return ResponseEntity.ok(catalogService.getCatalogByCode(code));

    }
    @GetMapping("/products/all")
    public List<Catalog> getAllBooks(){
        return catalogService.getAllCatalogs();
    }
    @PostMapping("/products")
    public ResponseEntity<Catalog> addCatalog(@RequestBody @Valid Catalog catalog){
        Catalog postedCatalog = catalogService.createCatalog(catalog);
        //Return only the status
        //return ResponseEntity.status(HttpStatus.CREATED).build();

        //Return the status along with the created object
        return ResponseEntity.status(HttpStatus.CREATED).body(postedCatalog);
    }

    @PutMapping(value = "/products/{code}")
    public ResponseEntity<Catalog> updateCatalog(@Valid @RequestBody Catalog catalogDetails, @PathVariable String code){
        return catalogService.updateCatalog(code, catalogDetails);

    }

    @DeleteMapping("products/{code}")
    public void deleteCatalogByCode(@PathVariable String code){
        if(catalogRepository.findByCode(code) == null)
            throw new ProductNotFoundException("Product with code " + code + " not found");
        catalogService.deleteCatalog(code);

    }
}
