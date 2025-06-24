package com.project.muwbe.controllers.admin;

import com.project.muwbe.dtos.requests.AdminDanhMucConForm;
import com.project.muwbe.entities.DanhMucCon;
import com.project.muwbe.repositories.DanhMucConRepository;
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
@RequestMapping("/danh-muc-con")
@CrossOrigin(origins = "*")
public class DanhMucConController {
    @Autowired
    private DanhMucConRepository danhMucConRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;


    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<DanhMucCon> results = danhMucConRepository.findAll(pageable);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            DanhMucCon result = danhMucConRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục con với ID: " + id));
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AdminDanhMucConForm request) {
        DanhMucCon danhMucCon = new DanhMucCon();

        danhMucCon.setTenDanhMucCon(request.getTenDanhMucCon());
        danhMucCon.setTrangThai(request.getTrangThai());


        danhMucCon.setNgayTao(new Timestamp(System.currentTimeMillis()));
        if (request.getNguoiTao() != null) {
            taiKhoanRepository.findById(request.getNguoiTao()).ifPresent(danhMucCon::setNguoiTao);
        }

        danhMucConRepository.save(danhMucCon);
        return ResponseEntity.ok("Danh mục tạo mới thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AdminDanhMucConForm request) {
        Optional<DanhMucCon> optional = danhMucConRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DanhMucCon danhMucCon = optional.get();
        danhMucCon.setTenDanhMucCon(request.getTenDanhMucCon());
        danhMucCon.setTrangThai(request.getTrangThai());

        danhMucCon.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (request.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(request.getNguoiCapNhat()).ifPresent(danhMucCon::setNguoiCapNhat);
        }

        danhMucConRepository.save(danhMucCon);
        return ResponseEntity.ok("Cập nhật danh mục thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!danhMucConRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        danhMucConRepository.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }
}
