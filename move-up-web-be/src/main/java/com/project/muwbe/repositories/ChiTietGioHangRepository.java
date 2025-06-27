package com.project.muwbe.repositories;

import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.ChiTietGioHang;
import com.project.muwbe.entities.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Long> {
    Optional<ChiTietGioHang> findByGioHangAndChiTietGiay(GioHang gioHang, ChiTietGiay chiTietGiay);
}
