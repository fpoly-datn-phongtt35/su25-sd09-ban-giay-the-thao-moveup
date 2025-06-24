package com.project.muwbe.repositories;

import com.project.muwbe.entities.Giay;
import com.project.muwbe.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GiayRepository extends JpaRepository<Giay, Long> {

    Page<Giay> findAll(Pageable pageable);

    Optional<Giay> findBytenGiay(String tenGiay);
}
