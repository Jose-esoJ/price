package com.example.price.infrastructure.adapter.repository;

import com.example.price.infrastructure.adapter.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId " +
            "AND p.startDate <= :dateInitial AND p.endDate >= :dateInitial " +
            "AND p.priority = (SELECT MAX(p2.priority) FROM PriceEntity p2 WHERE p2.productId = :productId AND p2.brandId = :brandId " +
            "AND p2.startDate <= :dateInitial AND p2.endDate >= :dateInitial)")
    Optional<PriceEntity> findByProductIdAndBrandIdAndDateRange(@Param("productId")Long productId, @Param("brandId")Long brandId, @Param("dateInitial")LocalDateTime dateInitial);
}