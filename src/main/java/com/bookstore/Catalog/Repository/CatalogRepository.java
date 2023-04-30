package com.bookstore.Catalog.Repository;

import com.bookstore.Catalog.Entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
    Catalog findByCode(String code);
}
