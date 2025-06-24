package com.project.muwbe.controllers.client;

import com.project.muwbe.dtos.responses.ClientGioHang;
import com.project.muwbe.entities.GioHang;
import com.project.muwbe.entities.KhachHang;
import com.project.muwbe.repositories.GioHangRepository;
import com.project.muwbe.repositories.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client/giohang")
@CrossOrigin("*")
public class ClientGioHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @GetMapping("/{idKhachHang}")
    public ResponseEntity<?> getGioHangByKhachHang(@PathVariable Long idKhachHang) {
        KhachHang khachHang = khachHangRepository.findById(idKhachHang)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy khách hàng với ID: " + idKhachHang));

        GioHang gioHang = gioHangRepository.getGioHangByKhachHang(khachHang)
                .orElseGet(() -> {
                    GioHang newGioHang = new GioHang();
                    newGioHang.setKhachHang(khachHang);
                    newGioHang.setTongSoLuong(0);
                    newGioHang.setTongTien(0L);
                    newGioHang.setTrangThai(true); // or false depending on default
                    newGioHang.setNgayTao(new Timestamp(System.currentTimeMillis()));
                    newGioHang.setNguoiTao(khachHang.getTaiKhoan()); // or set default user if available
                    newGioHang.setChiTietGioHang(List.of());

                    return gioHangRepository.save(newGioHang); // Save and return
                });

        ClientGioHang result = new ClientGioHang(gioHang);
        return ResponseEntity.ok(result);
    }
}
