package com.project.muwbe.repositories;

import com.project.muwbe.entities.DanhMuc;
import com.project.muwbe.entities.Giay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {
    Page<DanhMuc> findAll(Pageable pageable);
}
