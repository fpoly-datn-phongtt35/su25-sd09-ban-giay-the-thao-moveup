package com.project.muwbe.repositories;

import com.project.muwbe.entities.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, Long> {

    Page<PhieuGiamGia> findAll(Pageable pageable);

    Optional<PhieuGiamGia> findByMaGiamGia(String maGiamGia);
}
