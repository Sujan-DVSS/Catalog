package com.bookstore.Catalog.Response;

import com.bookstore.Catalog.Entity.Catalog;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

// Add @Data Lombok annotation to avoid error code:406 - org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
@Data
public class PageResult {
    private Long totalElements;
    private int totalPages;
    private int currentPage;
    List<Catalog> Data;
    private boolean first;
    private boolean last;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageResult(Page<Catalog> page){
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber()+1;
        this.Data = page.getContent();
        this.first = page.isFirst();
        this.last = page.isLast();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }

}
