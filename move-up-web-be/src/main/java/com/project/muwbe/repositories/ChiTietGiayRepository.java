package com.project.muwbe.repositories;

import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.ChiTietHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietGiayRepository extends JpaRepository<ChiTietGiay, Long> {

    Page<ChiTietGiay> findAll(Pageable pageable);
}
