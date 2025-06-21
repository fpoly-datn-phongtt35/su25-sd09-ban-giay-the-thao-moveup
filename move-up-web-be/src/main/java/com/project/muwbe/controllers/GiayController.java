package com.project.muwbe.controllers;
import com.project.muwbe.dtos.requests.AdminGiayForm;
import com.project.muwbe.dtos.responses.GiayList;
import com.project.muwbe.entities.*;
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

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private AnhRepository anhRepository;
    @Autowired
    private AnhGiayRepository anhGiayRepository;

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
        Page<GiayList> results = list.map(GiayList::new);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));
            GiayList result = new GiayList(giay);
            return ResponseEntity.ok(result);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AdminGiayForm form) {
        try {
            if (giayRepository.findBytenGiay(form.getTenGiay()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Sản phẩm với tên " + form.getTenGiay() + " đã tồn tại");
            }

            Giay giay = new Giay();
            giay.setTenGiay(form.getTenGiay());
            giay.setMoTaGiay(form.getMoTaGiay());
            giay.setTrangThai(form.getTrangThai());

            if (form.getIdDanhMuc() != null) {
                DanhMuc danhMuc = danhMucRepository.findById(form.getIdDanhMuc())
                        .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục với ID: " + form.getIdDanhMuc()));
                giay.setDanhMuc(danhMuc);
            } else if (form.getIdDanhMucCon() != null) {
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
            giay.setNgayTao(new Timestamp(System.currentTimeMillis()));
//            giay.setNguoiTao(
//                    taiKhoanRepository.findById(form.getNguoiTao())
//                            .orElseThrow(() -> new NoSuchElementException("Không tìm thấy tài khoản với ID: " + form.getNguoiTao()))
//            );

            Giay savedGiay = giayRepository.save(giay);

            if (form.getChiTietGiay() != null && !form.getChiTietGiay().isEmpty()) {
                for (AdminGiayForm.ChiTietGiay chiTietForm : form.getChiTietGiay()) {
                    ChiTietGiay chiTiet = new ChiTietGiay();
                    chiTiet.setGiay(savedGiay);
                    chiTiet.setSku(chiTietForm.getSku());
                    chiTiet.setGiaNhap(chiTietForm.getGiaNhap());
                    chiTiet.setGiaBan(chiTietForm.getGiaBan());
                    chiTiet.setMauSac(chiTietForm.getMauSac());
                    chiTiet.setSize(chiTietForm.getSize());
                    chiTiet.setAnh(
                            anhRepository.findById(chiTietForm.getIdAnh())
                                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy ảnh với ID: " + chiTietForm.getIdAnh()))
                    );
                    chiTiet.setSoLuong(chiTietForm.getSoLuong());
                    chiTiet.setTrangThai(chiTietForm.getTrangThai());
                    chiTietGiayRepository.save(chiTiet);
                }
            }

            if (form.getAnhGiay() != null && !form.getAnhGiay().isEmpty()) {
                for (AdminGiayForm.AnhGiay anhGiayForm : form.getAnhGiay()) {
                    AnhGiay anhGiay = new AnhGiay();
                    anhGiay.setGiay(savedGiay);
                    anhGiay.setAnh(
                            anhRepository.findById(anhGiayForm.getIdAnh())
                                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy ảnh với ID: " +  anhGiayForm.getIdAnh()))
                    );
                    anhGiayRepository.save(anhGiay);
                }
            }

            return ResponseEntity.ok(new GiayList(savedGiay));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody AdminGiayForm form) {
        try {
            Giay giay = giayRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy giày với ID: " + id));

            Optional<Giay> existingGiay = giayRepository.findBytenGiay(form.getTenGiay());
            if (existingGiay.isPresent() && !existingGiay.get().getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Sản phẩm với tên " + form.getTenGiay() + " đã tồn tại");
            }

            giay.setTenGiay(form.getTenGiay());
            giay.setMoTaGiay(form.getMoTaGiay());
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
                for (AdminGiayForm.ChiTietGiay chiTietForm : form.getChiTietGiay()) {
                    ChiTietGiay chiTiet = new ChiTietGiay();
                    chiTiet.setGiay(giay);
                    chiTiet.setSku(chiTietForm.getSku());
                    chiTiet.setGiaNhap(chiTietForm.getGiaNhap());
                    chiTiet.setGiaBan(chiTietForm.getGiaBan());
                    chiTiet.setMauSac(chiTietForm.getMauSac());
                    chiTiet.setSize(chiTietForm.getSize());
                    chiTiet.setSoLuong(chiTietForm.getSoLuong());
                    chiTiet.setTrangThai(chiTietForm.getTrangThai());
                    chiTietGiayRepository.save(chiTiet);
                }
            }

            Giay updatedGiay = giayRepository.save(giay);
            return ResponseEntity.ok(new GiayList(updatedGiay));
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
