package com.project.muwbe.repositories;

import com.project.muwbe.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    KhachHang findByMaKhachHang(String maKhachHang);

    List<KhachHang> findByHoTenContainingIgnoreCase(String hoTen);

    List<KhachHang> findBySoDienThoaiContaining(String soDienThoai);
}
