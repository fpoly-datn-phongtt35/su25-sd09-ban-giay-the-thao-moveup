package com.project.muwbe.controllers.client;

import com.project.muwbe.dtos.requests.ClientGioHangForm;
import com.project.muwbe.dtos.responses.ClientGioHang;
import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.ChiTietGioHang;
import com.project.muwbe.entities.GioHang;
import com.project.muwbe.entities.KhachHang;
import com.project.muwbe.repositories.ChiTietGiayRepository;
import com.project.muwbe.repositories.ChiTietGioHangRepository;
import com.project.muwbe.repositories.GioHangRepository;
import com.project.muwbe.repositories.KhachHangRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/client/giohang")
@CrossOrigin("*")
public class ClientGioHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private ChiTietGiayRepository chiTietGiayRepository;

    @Autowired
    private ChiTietGioHangRepository chiTietGioHangRepository;

    @GetMapping("/{idKhachHang}")
    public ResponseEntity<?> getGioHangByKhachHang(@PathVariable Long idKhachHang) {
        KhachHang khachHang = khachHangRepository.findById(idKhachHang)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + idKhachHang));

        GioHang gioHang = gioHangRepository.findFirstByKhachHangAndTrangThaiFalseOrderByNgayTaoDesc(khachHang)
                .orElseGet(() -> {
                    GioHang newGioHang = new GioHang();
                    newGioHang.setKhachHang(khachHang);
                    newGioHang.setTongSoLuong(0);
                    newGioHang.setTongTien(0L);
                    newGioHang.setTrangThai(false); // not completed yet
                    newGioHang.setNgayTao(new Timestamp(System.currentTimeMillis()));
                    newGioHang.setNguoiTao(khachHang.getTaiKhoan());
                    newGioHang.setChiTietGioHang(List.of());
                    return gioHangRepository.save(newGioHang);
                });

        ClientGioHang result = new ClientGioHang(gioHang);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToGioHang(@RequestBody ClientGioHangForm form) {
        try {
            KhachHang khachHang = khachHangRepository.findById(form.getIdKhachHang())
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng"));
            ChiTietGiay chiTietGiay = chiTietGiayRepository.findById(form.getIdChiTietGiay())
                    .orElseThrow(() -> new NoSuchElementException("Không tìm thấy chi tiết giày"));
            if (form.getSoLuong() > chiTietGiay.getSoLuong()) {
                throw new BadRequestException("Số lượng vượt quá số lượng tồn kho");
            }
            GioHang gioHang = gioHangRepository.findFirstByKhachHangAndTrangThaiFalseOrderByNgayTaoDesc(khachHang)
                    .orElseGet(() -> {
                        GioHang newGioHang = new GioHang();
                        newGioHang.setKhachHang(khachHang);
                        newGioHang.setTongSoLuong(0);
                        newGioHang.setTongTien(0L);
                        newGioHang.setTrangThai(false); // not completed yet
                        newGioHang.setNgayTao(new Timestamp(System.currentTimeMillis()));
                        newGioHang.setNguoiTao(khachHang.getTaiKhoan());
                        newGioHang.setChiTietGioHang(List.of());
                        return gioHangRepository.save(newGioHang);
                    });
            Optional<ChiTietGioHang> existing = chiTietGioHangRepository.findByGioHangAndChiTietGiay(gioHang, chiTietGiay);

            ChiTietGioHang chiTiet;
            if (existing.isPresent()) {
                chiTiet = existing.get();
                chiTiet.setSoLuong(chiTiet.getSoLuong() + form.getSoLuong());
            } else {
                chiTiet = new ChiTietGioHang();
                chiTiet.setGioHang(gioHang);
                chiTiet.setChiTietGiay(chiTietGiay);
                chiTiet.setSoLuong(form.getSoLuong());
            }

            chiTietGioHangRepository.save(chiTiet);

            Long giaBan = chiTietGiay.getGiaBan() != null ? chiTietGiay.getGiaBan() : 0L;
            gioHang.setTongSoLuong(gioHang.getTongSoLuong() + form.getSoLuong());
            gioHang.setTongTien(gioHang.getTongTien() + (giaBan * form.getSoLuong()));
            gioHang.setNgayCapNhat(new Timestamp(System.currentTimeMillis()));
            gioHang.setNguoiCapNhat(khachHang.getTaiKhoan());
            gioHangRepository.save(gioHang);

            return ResponseEntity.ok("Thêm vào giỏ hàng thành công");
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
