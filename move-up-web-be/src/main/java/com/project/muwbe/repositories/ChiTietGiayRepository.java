package com.project.muwbe.repositories;

import com.project.muwbe.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietGiayRepository extends JpaRepository<ChiTietHoaDon, Long> {
}
