package com.bookstore.Catalog.Repository;

import com.bookstore.Catalog.Entity.Catalog;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface CatalogRepository extends CrudRepository<Catalog, UUID> {
    Catalog findByCode(String code);
}
