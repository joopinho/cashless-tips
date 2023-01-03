package com.jjj.cashlesstips.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jjj.cashlesstips.dao.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT o FROM Order o WHERE o.externalId = ?1")
    Optional<Order> findByExternalId(String externalId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.creator WHERE o.linkId = ?1")
    Optional<Order> findByLinkId(String linkId);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.creator WHERE o.id = ?1")
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = "creator")
    @Query("SELECT o FROM Order o WHERE o.createdBy = ?1")
    Page<Order> findByUserId(Long userId, Pageable pageable);
}
