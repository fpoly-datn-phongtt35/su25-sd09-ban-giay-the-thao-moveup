package com.project.muwbe.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;@AllArgsConstructor

@NoArgsConstructor
@Getter
@Setter


public class AdminHoaDonForm {
    private String maHoaDon;
    private Long idCoSo;
    private Long idKhachHang;
    private String tenKhachHang;
    private String soDienThoaiKhachHang;
    private String emailKhachHang;
    private String diaChiKhachHang;
    private Long idNhanVien;
    private String ghiChu;
    private String ghiChuKhachHang;
    private Boolean donVanChuyen;
    private Long tongThanhToan;
    private Long tienKhachTra;
    private String loaiHinhThanhToan;
    private String trangThaiGiaoDich;
    private String trangThaiHoaDon;
    private Timestamp ngayTao;
    private Long nguoiTao;
    private Timestamp ngayCapNhat;
    private Long nguoiCapNhat;
    private List<AdminChiTietHoaDon> chiTietHoaDon;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdminChiTietHoaDon {
        private Long idChiTietGiay;
        private Integer soLuong;
        private Long thanhTien;
    }
}
