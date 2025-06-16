package com.project.muwbe.controllers;
import com.project.muwbe.dtos.requests.GiayRequest;
import com.project.muwbe.dtos.responses.AdminGiayList;
import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.DanhMuc;
import com.project.muwbe.entities.DanhMucCon;
import com.project.muwbe.entities.Giay;
import com.project.muwbe.repositories.ChiTietGiayRepository;
import com.project.muwbe.repositories.DanhMucConRepository;
import com.project.muwbe.repositories.DanhMucRepository;
import com.project.muwbe.repositories.GiayRepository;
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

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/giay")
@CrossOrigin(origins = "*")
public class GiayController {
    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private ChiTietGiayRepository chiTietGiayRepository;

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private DanhMucConRepository danhMucConRepository;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(direction, sortBy));
        Page<Giay> list = giayRepository.findAll(pageable);
        Page<AdminGiayList> results = list.map(AdminGiayList::new);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));
            AdminGiayList result = new AdminGiayList(giay);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    // Create a new product
    @Transactional
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GiayRequest form) {
        try {
            if (giayRepository.findByTenSanPham(form.getTenSanPham()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Sản phẩm với tên " + form.getTenSanPham() + " đã tồn tại");
            }

            Giay giay = new Giay();
            giay.setTenSanPham(form.getTenSanPham());
            giay.setMoTaSanPham(form.getMoTaSanPham());
            giay.setTrangThai(form.getTrangThai());

            if (form.getIdDanhMuc() != null) {
                DanhMuc danhMuc = danhMucRepository.findById(form.getIdDanhMuc())
                        .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục với ID: " + form.getIdDanhMuc()));
                giay.setDanhMuc(danhMuc);
            }

            if (form.getIdDanhMucCon() != null) {
                DanhMucCon danhMucCon = danhMucConRepository.findById(form.getIdDanhMucCon())
                        .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục con với ID: " + form.getIdDanhMucCon()));
                giay.setDanhMucCon(danhMucCon);
            }

            giay.setThuongHieu(form.getThuongHieu());
            giay.setChatLieu(form.getChatLieu());
            giay.setXuatXu(form.getXuatXu());
            giay.setKieuDang(form.getKieuDang());
            giay.setTuKhoa(form.getTuKhoa());
            giay.setUuTien(form.getUuTien());

            Giay savedGiay = giayRepository.save(giay);

            if (form.getChiTietGiay() != null && !form.getChiTietGiay().isEmpty()) {
                for (GiayRequest.ChiTietGiay chiTietForm : form.getChiTietGiay()) {
                    ChiTietGiay chiTiet = new ChiTietGiay();
                    chiTiet.setGiay(savedGiay);
                    chiTiet.setSku(chiTietForm.getSku());
                    chiTiet.setGiaNhap(chiTietForm.getGiaNhap());
                    chiTiet.setGiaBan(chiTietForm.getGiaBan());
                    chiTiet.setMauSac(chiTietForm.getMauSac());
                    chiTiet.setSize(chiTietForm.getSize());
                    chiTiet.setAnh(chiTietForm.getAnh());
                    chiTiet.setSoLuong(chiTietForm.getSoLuong());
                    chiTiet.setTrangThai(chiTietForm.getTrangThai());
                    chiTietGiayRepository.save(chiTiet);
                }
            }

            return ResponseEntity.ok(new AdminGiayList(savedGiay));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody GiayRequest form) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));

            // Check if another product with the same name exists
            Optional<Giay> existingGiay = giayRepository.findByTenSanPham(form.getTenSanPham());
            if (existingGiay.isPresent() && !existingGiay.get().getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Sản phẩm với tên " + form.getTenSanPham() + " đã tồn tại");
            }

            giay.setTenSanPham(form.getTenSanPham());
            giay.setMoTaSanPham(form.getMoTaSanPham());
            giay.setTrangThai(form.getTrangThai());

            if (form.getIdDanhMuc() != null) {
                DanhMuc danhMuc = danhMucRepository.findById(form.getIdDanhMuc())
                        .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục với ID: " + form.getIdDanhMuc()));
                giay.setDanhMuc(danhMuc);
            } else {
                giay.setDanhMuc(null);
            }

            if (form.getIdDanhMucCon() != null) {
                DanhMucCon danhMucCon = danhMucConRepository.findById(form.getIdDanhMucCon())
                        .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục con với ID: " + form.getIdDanhMucCon()));
                giay.setDanhMucCon(danhMucCon);
            } else {
                giay.setDanhMucCon(null);
            }

            giay.setThuongHieu(form.getThuongHieu());
            giay.setChatLieu(form.getChatLieu());
            giay.setXuatXu(form.getXuatXu());
            giay.setKieuDang(form.getKieuDang());
            giay.setTuKhoa(form.getTuKhoa());
            giay.setUuTien(form.getUuTien());

            chiTietGiayRepository.deleteByGiayId(id);

            if (form.getChiTietGiay() != null && !form.getChiTietGiay().isEmpty()) {
                for (GiayRequest.ChiTietGiay chiTietForm : form.getChiTietGiay()) {
                    ChiTietGiay chiTiet = new ChiTietGiay();
                    chiTiet.setGiay(giay);
                    chiTiet.setSku(chiTietForm.getSku());
                    chiTiet.setGiaNhap(chiTietForm.getGiaNhap());
                    chiTiet.setGiaBan(chiTietForm.getGiaBan());
                    chiTiet.setMauSac(chiTietForm.getMauSac());
                    chiTiet.setSize(chiTietForm.getSize());
                    chiTiet.setAnh(chiTietForm.getAnh());
                    chiTiet.setSoLuong(chiTietForm.getSoLuong());
                    chiTiet.setTrangThai(chiTietForm.getTrangThai());
                    chiTietGiayRepository.save(chiTiet);
                }
            }

            Giay updatedGiay = giayRepository.save(giay);
            return ResponseEntity.ok(new AdminGiayList(updatedGiay));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));

            chiTietGiayRepository.deleteByGiayId(id);

            giayRepository.delete(giay);
            return ResponseEntity.ok("Xóa sản phẩm thành công");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

}
