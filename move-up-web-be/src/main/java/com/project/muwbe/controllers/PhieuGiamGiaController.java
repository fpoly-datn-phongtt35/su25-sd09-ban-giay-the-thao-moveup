package com.project.muwbe.controllers;


import com.project.muwbe.dtos.requests.PhieuGiamGiaRequest;
import com.project.muwbe.entities.PhieuGiamGia;
import com.project.muwbe.repositories.PhieuGiamGiaRepository;
import com.project.muwbe.repositories.TaiKhoanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/phieu-giam-gia")
@CrossOrigin(origins = "*")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<PhieuGiamGia> results = phieuGiamGiaRepository.findAll(pageable);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            PhieuGiamGia phieuGiamGia = phieuGiamGiaRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy phiếu giảm giá với ID: " + id));
            return ResponseEntity.ok(phieuGiamGia);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PhieuGiamGiaRequest phieuGiamGiaRequest) {
        try {
            PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
            Optional<PhieuGiamGia> existingPhieuGiamGia = phieuGiamGiaRepository.findByMaGiamGia(phieuGiamGia.getMaGiamGia());
            if (existingPhieuGiamGia.isPresent()) {
                throw new IllegalArgumentException("Phiếu giảm giá đã tồn tại: " + phieuGiamGia.getMaGiamGia());
            }

            phieuGiamGia.setMaGiamGia(phieuGiamGiaRequest.getMaGiamGia());
            phieuGiamGia.setTenGiamGia(phieuGiamGiaRequest.getTenGiamGia());
            phieuGiamGia.setPhanTramGiam(phieuGiamGiaRequest.getPhanTramGiam());
            phieuGiamGia.setGiaTienGiam(phieuGiamGiaRequest.getGiaTienGiam());
            phieuGiamGia.setGiaTriToiDa(phieuGiamGiaRequest.getGiaTriToiDa());
            phieuGiamGia.setGiaTriToiThieu(phieuGiamGiaRequest.getGiaTriToiThieu());
            phieuGiamGia.setSoLuong(phieuGiamGiaRequest.getSoLuong());
            phieuGiamGia.setNgayBatDau(new Timestamp(phieuGiamGiaRequest.getNgayBatDau().getTime()));
            phieuGiamGia.setNgayKetThuc(new Timestamp(phieuGiamGiaRequest.getNgayKetThuc().getTime()));
            phieuGiamGia.setTrangThai(phieuGiamGiaRequest.getTrangThai());
            phieuGiamGia.setMoTa(phieuGiamGiaRequest.getMoTa());
            phieuGiamGia.setNgayTao(new Timestamp(System.currentTimeMillis()));
            phieuGiamGia.setNguoiTao(taiKhoanRepository.findById(phieuGiamGiaRequest.getNguoiTao()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + phieuGiamGiaRequest.getNguoiTao())));

            phieuGiamGiaRepository.save(phieuGiamGia);

            return ResponseEntity.ok().body("Phiếu giảm giá được tạo thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody PhieuGiamGiaRequest phieuGiamGiaRequest) {
        Optional<PhieuGiamGia> optional = phieuGiamGiaRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PhieuGiamGia phieuGiamGia = optional.get();
        phieuGiamGia.setMaGiamGia(phieuGiamGiaRequest.getMaGiamGia());
        phieuGiamGia.setTenGiamGia(phieuGiamGiaRequest.getTenGiamGia());
        phieuGiamGia.setPhanTramGiam(phieuGiamGiaRequest.getPhanTramGiam());
        phieuGiamGia.setGiaTienGiam(phieuGiamGiaRequest.getGiaTienGiam());
        phieuGiamGia.setGiaTriToiDa(phieuGiamGiaRequest.getGiaTriToiDa());
        phieuGiamGia.setGiaTriToiThieu(phieuGiamGiaRequest.getGiaTriToiThieu());
        phieuGiamGia.setSoLuong(phieuGiamGiaRequest.getSoLuong());
        phieuGiamGia.setNgayBatDau(new Timestamp(phieuGiamGiaRequest.getNgayBatDau().getTime()));
        phieuGiamGia.setNgayKetThuc(new Timestamp(phieuGiamGiaRequest.getNgayKetThuc().getTime()));
        phieuGiamGia.setTrangThai(phieuGiamGiaRequest.getTrangThai());
        phieuGiamGia.setMoTa(phieuGiamGiaRequest.getMoTa());

        phieuGiamGia.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (phieuGiamGiaRequest.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(phieuGiamGiaRequest.getNguoiCapNhat()).ifPresent(phieuGiamGia::setNguoiCapNhat);
        }

        phieuGiamGiaRepository.save(phieuGiamGia);
        return ResponseEntity.ok("Cập nhật phiếu giảm giá thành công");
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!phieuGiamGiaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        phieuGiamGiaRepository.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }
}
