package com.project.muwbe.repositories;

import com.project.muwbe.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @EntityGraph(attributePaths = {
            "coSo",
            "khachHang",
            "nhanVien",
            "nguoiTao",
            "nguoiCapNhat"
    })
    Page<HoaDon> findAll(Pageable pageable);

    Optional<HoaDon> findByMaHoaDon(String maHoaDon);
}
