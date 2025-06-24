package com.project.muwbe.repositories;

import com.project.muwbe.entities.CoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoSoRepository extends JpaRepository<CoSo, Long> {
}
