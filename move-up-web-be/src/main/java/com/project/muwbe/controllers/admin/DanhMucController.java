package com.project.muwbe.controllers.admin;

import com.project.muwbe.dtos.requests.AdminDanhMucForm;
import com.project.muwbe.entities.DanhMuc;
import com.project.muwbe.repositories.DanhMucRepository;
import com.project.muwbe.repositories.TaiKhoanRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/danh-muc")
@CrossOrigin(origins = "*")
public class DanhMucController {

    @Autowired
    private DanhMucRepository danhMucRepository;

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
            Page<DanhMuc> results = danhMucRepository.findAll(pageable);
            return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            DanhMuc result = danhMucRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục với ID: " + id));
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AdminDanhMucForm request) {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setTenDanhMuc(request.getTenDanhMuc());
        danhMuc.setTrangThai(request.getTrangThai());

        danhMuc.setNgayTao(new Timestamp(System.currentTimeMillis()));

        if (request.getNguoiTao() != null) {
            taiKhoanRepository.findById(request.getNguoiTao()).ifPresent(danhMuc::setNguoiTao);
        }

        danhMucRepository.save(danhMuc);
        return ResponseEntity.ok("Danh mục tạo mới thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AdminDanhMucForm request) {
        Optional<DanhMuc> optional = danhMucRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DanhMuc danhMuc = optional.get();
        danhMuc.setTenDanhMuc(request.getTenDanhMuc());
        danhMuc.setTrangThai(request.getTrangThai());

        danhMuc.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (request.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(request.getNguoiCapNhat()).ifPresent(danhMuc::setNguoiCapNhat);
        }

        danhMucRepository.save(danhMuc);
        return ResponseEntity.ok("Cập nhật danh mục thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!danhMucRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        danhMucRepository.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }

}
