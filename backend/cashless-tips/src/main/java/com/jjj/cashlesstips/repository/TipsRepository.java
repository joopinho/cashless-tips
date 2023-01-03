package com.jjj.cashlesstips.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjj.cashlesstips.dao.entity.Tips;

public interface TipsRepository extends JpaRepository <Tips, Long> {
    
}
