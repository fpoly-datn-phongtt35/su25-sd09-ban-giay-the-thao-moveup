package com.project.muwbe.controllers;

import com.project.muwbe.dtos.requests.AdminHoaDonForm;
import com.project.muwbe.dtos.responses.AdminHoaDonList;
import com.project.muwbe.entities.ChiTietHoaDon;
import com.project.muwbe.entities.HoaDon;
import com.project.muwbe.repositories.*;
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
@RequestMapping("/hoa-don")
@CrossOrigin(origins = "*")
public class HoaDonController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private CoSoRepository coSoRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private NhanVienRepository nhanVienRepository;

    @Autowired
    private ChiTietGiayRepository chiTietGiayRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<HoaDon> list = hoaDonRepository.findAll(pageable);
        Page<AdminHoaDonList> results = list.map(AdminHoaDonList::new);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            HoaDon hoaDon = hoaDonRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy hóa đơn với ID: " + id));
            AdminHoaDonList result = new AdminHoaDonList(hoaDon);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AdminHoaDonForm form) {
        try {
            HoaDon hoaDon = new HoaDon();
            Optional<HoaDon> existingHoaDon = hoaDonRepository.findByMaHoaDon(form.getMaHoaDon());
            if (existingHoaDon.isPresent()) {
                throw new IllegalArgumentException("Mã hóa đơn đã tồn tại: " + form.getMaHoaDon());
            }
            hoaDon.setMaHoaDon(form.getMaHoaDon());
            hoaDon.setCoSo(coSoRepository.findById(form.getIdCoSo()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy cơ sở với ID: " + form.getIdCoSo())));
            hoaDon.setKhachHang(form.getIdKhachHang() != null ? khachHangRepository.findById(form.getIdKhachHang()).orElse(null) : null);
            hoaDon.setTenKhachHang(form.getTenKhachHang());
            hoaDon.setSoDienThoaiKhachHang(form.getSoDienThoaiKhachHang());
            hoaDon.setEmailKhachHang(form.getEmailKhachHang());
            hoaDon.setDiaChiKhachHang(form.getDiaChiKhachHang());
            hoaDon.setNhanVien(nhanVienRepository.findById(form.getIdNhanVien()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy nhân viên với ID: " + form.getIdNhanVien())));
            hoaDon.setGhiChu(form.getGhiChu());
            hoaDon.setGhiChuKhachHang(form.getGhiChuKhachHang());
            hoaDon.setDonVanChuyen(form.getDonVanChuyen());
            hoaDon.setTongThanhToan(form.getTongThanhToan());
            hoaDon.setTienKhachTra(form.getTienKhachTra());
            hoaDon.setLoaiHinhThanhToan(form.getLoaiHinhThanhToan());
            hoaDon.setTrangThaiGiaoDich(form.getTrangThaiGiaoDich());
            hoaDon.setTrangThaiHoaDon(form.getTrangThaiHoaDon());
            hoaDon.setNgayTao(new Timestamp(System.currentTimeMillis()));
            hoaDon.setNguoiTao(taiKhoanRepository.findById(form.getNguoiTao()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + form.getNguoiTao())));

            hoaDonRepository.save(hoaDon);

//            for (AdminHoaDonForm.AdminChiTietHoaDon chiTietForm : form.getChiTietHoaDon()) {
//                ChiTietHoaDon chiTiet = new ChiTietHoaDon();
//                chiTiet.setHoaDon(hoaDon);
//                chiTiet.setChiTietGiay(chiTietGiayRepository.findById(chiTietForm.getIdChiTietGiay()).orElseThrow().getChiTietGiay());
//                chiTiet.setSoLuong(chiTietForm.getSoLuong());
//                chiTiet.setThanhTien(chiTietForm.getThanhTien());
//                chiTietHoaDonRepository.save(chiTiet);
//            }

            return ResponseEntity.ok().body("Hóa đơn được tạo thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable Long id, @RequestBody AdminHoaDonForm form) {
        try {
            HoaDon hoaDon = hoaDonRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy hóa đơn với ID: " + id));
            if ("Chờ xác nhận".equals(hoaDon.getTrangThaiHoaDon())) {
                hoaDon.setCoSo(coSoRepository.findById(form.getIdCoSo()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy cơ sở với ID: " + form.getIdCoSo())));
                hoaDon.setKhachHang(form.getIdKhachHang() != null ? khachHangRepository.findById(form.getIdKhachHang()).orElse(null) : null);
                hoaDon.setTenKhachHang(form.getTenKhachHang());
                hoaDon.setSoDienThoaiKhachHang(form.getSoDienThoaiKhachHang());
                hoaDon.setEmailKhachHang(form.getEmailKhachHang());
                hoaDon.setDiaChiKhachHang(form.getDiaChiKhachHang());
                hoaDon.setGhiChu(form.getGhiChu());
                hoaDon.setGhiChuKhachHang(form.getGhiChuKhachHang());
                hoaDon.setTrangThaiGiaoDich(form.getTrangThaiGiaoDich());
                hoaDon.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
                hoaDon.setNguoiCapNhat(taiKhoanRepository.findById(form.getNguoiCapNhat()).orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + form.getNguoiCapNhat())));
            } else {
                hoaDon.setTrangThaiHoaDon(form.getTrangThaiHoaDon());
            }
            hoaDonRepository.save(hoaDon);
            return ResponseEntity.ok("Hóa đơn đã được cập nhật thành công.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            HoaDon hoaDon = hoaDonRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy hóa đơn với ID: " + id));
            if ("Chờ xác nhận".equals(hoaDon.getTrangThaiHoaDon())) {
                chiTietHoaDonRepository.deleteByHoaDonId(id);
                hoaDonRepository.delete(hoaDon);
                return ResponseEntity.ok("Hóa đơn đã xóa thành công");
            } else {
                hoaDon.setTrangThaiHoaDon("Đã hủy");
                hoaDonRepository.save(hoaDon);
                return ResponseEntity.ok("Hóa đơn đã được hủy thành công");
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
