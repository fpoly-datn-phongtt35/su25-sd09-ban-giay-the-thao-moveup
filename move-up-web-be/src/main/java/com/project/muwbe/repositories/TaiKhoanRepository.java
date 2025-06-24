package com.project.muwbe.repositories;

import com.project.muwbe.entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {

    Optional<TaiKhoan> findByEmailAndMatKhau(String email, String matKhau);
    Optional<TaiKhoan> findByEmail(String email);
}
