package com.project.muwbe.controllers;

import com.project.muwbe.dtos.requests.KhachHangRequest;
import com.project.muwbe.entities.KhachHang;
import com.project.muwbe.entities.TaiKhoan;
import com.project.muwbe.repositories.KhachHangRepository;
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
@RequestMapping("/khach-hang")
@CrossOrigin(origins = "*")
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

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
        Page<KhachHang> results = khachHangRepository.findAll(pageable);
        return ResponseEntity.ok(results);
    }

    // Lấy chi tiết theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            KhachHang result = khachHangRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + id));
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Thêm mới khách hàng
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody KhachHangRequest request) {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(request.getMaKhachHang());
        kh.setHoTen(request.getHoTen());
        kh.setSoDienThoai(request.getSoDienThoai());
        kh.setGioiTinh(request.getGioiTinh());
        kh.setNgaySinh(new Timestamp(request.getNgaySinh().getTime()));

        if (request.getIdTaiKhoan() != null) {
            Optional<TaiKhoan> tk = taiKhoanRepository.findById(request.getIdTaiKhoan());
            tk.ifPresent(kh::setTaiKhoan);
        }

        kh.setNgayTao(new Timestamp(System.currentTimeMillis()));

        if (request.getNguoiTao() != null) {
            taiKhoanRepository.findById(request.getNguoiTao()).ifPresent(kh::setNguoiTao);
        }

        khachHangRepository.save(kh);
        return ResponseEntity.ok("Khách hàng tạo mới thành công");
    }

    // Cập nhật khách hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody KhachHangRequest request) {
        Optional<KhachHang> optional = khachHangRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        KhachHang kh = optional.get();
        kh.setMaKhachHang(request.getMaKhachHang());
        kh.setHoTen(request.getHoTen());
        kh.setSoDienThoai(request.getSoDienThoai());
        kh.setGioiTinh(request.getGioiTinh());
        kh.setNgaySinh(new Timestamp(request.getNgaySinh().getTime()));

        if (request.getIdTaiKhoan() != null) {
            taiKhoanRepository.findById(request.getIdTaiKhoan()).ifPresent(kh::setTaiKhoan);
        }

        kh.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
        if (request.getNguoiCapNhat() != null) {
            taiKhoanRepository.findById(request.getNguoiCapNhat()).ifPresent(kh::setNguoiCapNhat);
        }

        khachHangRepository.save(kh);
        return ResponseEntity.ok("Cập nhật khách hàng thành công");
    }

    // Xoá khách hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!khachHangRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        khachHangRepository.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");
    }
}
