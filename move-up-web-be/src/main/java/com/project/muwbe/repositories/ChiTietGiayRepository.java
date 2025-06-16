package com.project.muwbe.repositories;

import com.project.muwbe.entities.ChiTietGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChiTietGiayRepository extends JpaRepository<ChiTietGiay, Long> {

    Page<ChiTietGiay> findAll(Pageable pageable);

    @Modifying
    @Query("DELETE FROM ChiTietGiay ct WHERE ct.giay.id = :giayId")
    void deleteByGiayId(Long giayId);
}
