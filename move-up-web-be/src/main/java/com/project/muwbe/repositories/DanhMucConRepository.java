package com.project.muwbe.repositories;

import com.project.muwbe.entities.DanhMucCon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanhMucConRepository extends JpaRepository<DanhMucCon, Long> {
    Page<DanhMucCon> findAll(Pageable pageable);

}
