package com.project.muwbe.repositories;

import com.project.muwbe.entities.Anh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnhRepository extends JpaRepository<Anh, Long> {
}
