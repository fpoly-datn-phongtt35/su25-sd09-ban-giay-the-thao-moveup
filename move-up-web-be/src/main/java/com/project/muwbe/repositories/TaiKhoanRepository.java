package com.project.muwbe.repositories;

import com.project.muwbe.entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {

    Optional<TaiKhoan> findByEmailAndMatKhau(String email, String matKhau);
    Optional<TaiKhoan> findByEmail(String email);
}
