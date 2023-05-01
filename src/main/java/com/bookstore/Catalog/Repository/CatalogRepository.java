package com.bookstore.Catalog.Repository;

import com.bookstore.Catalog.Entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
    Catalog findByCode(String code);

    @Query("SELECT c FROM Catalog c WHERE c.name LIKE %?1%"
            + " OR c.description LIKE %?1%")
    Page<Catalog> findByTitleContaining(String title, Pageable pageable);
}
