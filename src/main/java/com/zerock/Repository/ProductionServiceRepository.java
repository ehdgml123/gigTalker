package com.zerock.Repository;


import com.zerock.Entity.ProductionService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionServiceRepository extends JpaRepository<ProductionService, Long> {

    List<ProductionService> findByCategory(String category);
}
