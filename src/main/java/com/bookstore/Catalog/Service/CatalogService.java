package com.bookstore.Catalog.Service;

import com.bookstore.Catalog.Entity.Catalog;
import com.bookstore.Catalog.Exceptions.ProductAlreadyExistsException;
import com.bookstore.Catalog.Exceptions.ProductNotFoundException;
import com.bookstore.Catalog.Repository.CatalogRepository;
import com.bookstore.Catalog.Response.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {
    final CatalogRepository catalogRepository;

    private final static int PAGESIZE = 25;

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

    public PageResult getBooksByPage(int pageNumber) {
        Pageable pageable = PageRequest.of(getPageNumberOneIndexed(pageNumber), PAGESIZE);
        Page<Catalog> catalogsOnPage = catalogRepository.findAll(pageable);
        return new PageResult(catalogsOnPage);
        //catalogRepository
       // return catalogsOnPage;
    }

    public PageResult searchBooksByKeyword(String keyword, int pageNumber){
        int num = getPageNumberOneIndexed(pageNumber);
        Pageable pageable = PageRequest.of(num,PAGESIZE);
        Page<Catalog> page = catalogRepository.findAll(pageable);
        return new PageResult(catalogRepository.findByTitleContaining(keyword,pageable));
    }
    public int getPageNumberOneIndexed(int pageNo){
        return (pageNo > 0 ? pageNo-1: -1);
    }
}
