package com.project.muwbe.repositories;

import com.project.muwbe.entities.GioHang;
import com.project.muwbe.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    Optional<GioHang> getGioHangByKhachHang(KhachHang khachHang);
}
