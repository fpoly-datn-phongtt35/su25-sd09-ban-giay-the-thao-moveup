package com.project.muwbe.repositories;

import com.project.muwbe.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    KhachHang findByMaKhachHang(String maKhachHang);

    List<KhachHang> findByHoTenContainingIgnoreCase(String hoTen);

    List<KhachHang> findBySoDienThoaiContaining(String soDienThoai);
}
