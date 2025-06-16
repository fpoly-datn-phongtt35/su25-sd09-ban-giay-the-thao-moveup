package com.project.muwbe.controllers;


import com.project.muwbe.dtos.requests.AdminPhieuGiamGiaForm;
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
    public ResponseEntity<?> create(@Valid @RequestBody AdminPhieuGiamGiaForm adminPhieuGiamGiaForm) {
        try {
            PhieuGiamGia phieuGiamGia = new PhieuGiamGia();
            Optional<PhieuGiamGia> existingPhieuGiamGia = phieuGiamGiaRepository.findByMaGiamGia(phieuGiamGia.getMaGiamGia());
            if (existingPhieuGiamGia.isPresent()) {
                throw new IllegalArgumentException("Phiếu giảm giá đã tồn tại: " + phieuGiamGia.getMaGiamGia());
            }

            phieuGiamGia.setMaGiamGia(adminPhieuGiamGiaForm.getMaGiamGia());
            phieuGiamGia.setTenGiamGia(adminPhieuGiamGiaForm.getTenGiamGia());
            phieuGiamGia.setPhanTramGiam(adminPhieuGiamGiaForm.getPhanTramGiam());
            phieuGiamGia.setGiaTienGiam(adminPhieuGiamGiaForm.getGiaTienGiam());
            phieuGiamGia.setGiaTriToiDa(adminPhieuGiamGiaForm.getGiaTriToiDa());
            phieuGiamGia.setGiaTriToiThieu(adminPhieuGiamGiaForm.getGiaTriToiThieu());
            phieuGiamGia.setSoLuong(adminPhieuGiamGiaForm.getSoLuong());
            phieuGiamGia.setNgayBatDau(new Timestamp(adminPhieuGiamGiaForm.getNgayBatDau().getTime()));
            phieuGiamGia.setNgayKetThuc(new Timestamp(adminPhieuGiamGiaForm.getNgayKetThuc().getTime()));
            phieuGiamGia.setTrangThai(adminPhieuGiamGiaForm.getTrangThai());
            phieuGiamGia.setMoTa(adminPhieuGiamGiaForm.getMoTa());
            phieuGiamGia.setNgayTao(new Timestamp(System.currentTimeMillis()));
            phieuGiamGia.setNguoiTao(taiKhoanRepository.findById(adminPhieuGiamGiaForm.getNguoiTao()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + adminPhieuGiamGiaForm.getNguoiTao())));

            phieuGiamGiaRepository.save(phieuGiamGia);

            return ResponseEntity.ok().body("Phiếu giảm giá được tạo thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody AdminPhieuGiamGiaForm adminPhieuGiamGiaForm) {
        Optional<PhieuGiamGia> optional = phieuGiamGiaRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PhieuGiamGia phieuGiamGia = optional.get();
        phieuGiamGia.setMaGiamGia(adminPhieuGiamGiaForm.getMaGiamGia());
        phieuGiamGia.setTenGiamGia(adminPhieuGiamGiaForm.getTenGiamGia());
        phieuGiamGia.setPhanTramGiam(adminPhieuGiamGiaForm.getPhanTramGiam());
        phieuGiamGia.setGiaTienGiam(adminPhieuGiamGiaForm.getGiaTienGiam());
        phieuGiamGia.setGiaTriToiDa(adminPhieuGiamGiaForm.getGiaTriToiDa());
        phieuGiamGia.setGiaTriToiThieu(adminPhieuGiamGiaForm.getGiaTriToiThieu());
        phieuGiamGia.setSoLuong(adminPhieuGiamGiaForm.getSoLuong());
        phieuGiamGia.setNgayBatDau(new Timestamp(adminPhieuGiamGiaForm.getNgayBatDau().getTime()));
        phieuGiamGia.setNgayKetThuc(new Timestamp(adminPhieuGiamGiaForm.getNgayKetThuc().getTime()));
        phieuGiamGia.setTrangThai(adminPhieuGiamGiaForm.getTrangThai());
        phieuGiamGia.setMoTa(adminPhieuGiamGiaForm.getMoTa());

        phieuGiamGia.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (adminPhieuGiamGiaForm.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(adminPhieuGiamGiaForm.getNguoiCapNhat()).ifPresent(phieuGiamGia::setNguoiCapNhat);
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
