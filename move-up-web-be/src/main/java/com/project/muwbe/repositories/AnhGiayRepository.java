package com.project.muwbe.repositories;

import com.project.muwbe.entities.AnhGiay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnhGiayRepository extends JpaRepository<AnhGiay,Long> {
}
