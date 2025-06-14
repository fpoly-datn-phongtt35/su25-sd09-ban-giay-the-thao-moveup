package com.project.muwbe.repositories;

import com.project.muwbe.entities.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ChiTietHoaDon c WHERE c.hoaDon.id = :hoaDonId")
    void deleteByHoaDonId(@Param("hoaDonId") Long hoaDonId);
}
