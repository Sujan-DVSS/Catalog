package com.bookstore.Catalog.Service;

import com.bookstore.Catalog.Entity.Catalog;
import com.bookstore.Catalog.Exceptions.ProductAlreadyExistsException;
import com.bookstore.Catalog.Exceptions.ProductNotFoundException;
import com.bookstore.Catalog.Repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    final CatalogRepository catalogRepository;

    public Catalog getCatalogByCode(String code){
        return catalogRepository.findByCode(code);
    }
    public List<Catalog> getAllCatalogs(){
        return (List<Catalog>) catalogRepository.findAll();
    }

    public Catalog createCatalog(Catalog newCatalog){
        if(catalogRepository.findByCode(newCatalog.getCode()) != null)
            throw new ProductAlreadyExistsException(newCatalog.getCode());
        return catalogRepository.save(newCatalog);
    }

    public ResponseEntity<Catalog> updateCatalog(String code, Catalog catalogDetails){
        Catalog catalog =  catalogRepository.findByCode(code);
        if(catalog == null)
            throw new ProductNotFoundException("Product with code " + code + " not found");
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

    public void deleteCatalog(String code) {
        catalogRepository.delete(catalogRepository.findByCode(code));
    }
}
