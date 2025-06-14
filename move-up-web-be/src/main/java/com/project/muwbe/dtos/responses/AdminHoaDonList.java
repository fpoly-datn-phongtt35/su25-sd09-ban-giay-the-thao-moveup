package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminHoaDonList {
    private Long id;
    private String maHoaDon;
    private CoSo coSo;
    private KhachHang khachHang;
    private String tenKhachHang;
    private String soDienThoaiKhachHang;
    private String emailKhachHang;
    private String diaChiKhachHang;
    private NhanVien nhanVien;
    private String ghiChu;
    private String ghiChuKhachHang;
    private Boolean donVanChuyen;
    private Long tongThanhToan;
    private Long tienKhachTra;
    private String loaiHinhThanhToan;
    private String trangThaiGiaoDich;
    private String trangThaiHoaDon;
    private Timestamp ngayTao;
    private TaiKhoan nguoiTao;
    private Timestamp ngayCapNhat;
    private TaiKhoan nguoiCapNhat;
    private List<AdminChiTietHoaDon> chiTietHoaDon;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AdminChiTietHoaDon {
        private Long id;
        private AdminChiTietGiay chiTietGiay;
        private Integer soLuong;
        private Long thanhTien;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AdminChiTietGiay {
        private Long id;
        private AdminGiay giay;
        private String sku;
        private Long giaNhap;
        private Long giaBan;
        private String mauSac;
        private Integer size;
        private String anh;
        private Long soLuong;
        private Boolean trangThai;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AdminGiay {
        private Long id;
        private String tenSanPham;
        private String moTaSanPham;
        private Boolean trangThai;
        private DanhMuc danhMuc;
        private DanhMucCon danhMucCon;
        private String thuongHieu;
        private String chatLieu;
        private String xuatXu;
        private String kieuDang;
        private String tuKhoa;
        private Integer uuTien;
    }

    public AdminHoaDonList(HoaDon hoaDon) {
        this.id = hoaDon.getId();
        this.maHoaDon = hoaDon.getMaHoaDon();
        this.coSo = hoaDon.getCoSo();
        this.khachHang = hoaDon.getKhachHang();
        this.tenKhachHang = hoaDon.getTenKhachHang();
        this.soDienThoaiKhachHang = hoaDon.getSoDienThoaiKhachHang();
        this.emailKhachHang = hoaDon.getEmailKhachHang();
        this.diaChiKhachHang = hoaDon.getDiaChiKhachHang();
        this.nhanVien = hoaDon.getNhanVien();
        this.ghiChu = hoaDon.getGhiChu();
        this.ghiChuKhachHang = hoaDon.getGhiChuKhachHang();
        this.donVanChuyen = hoaDon.getDonVanChuyen();
        this.tongThanhToan = hoaDon.getTongThanhToan();
        this.tienKhachTra = hoaDon.getTienKhachTra();
        this.loaiHinhThanhToan = hoaDon.getLoaiHinhThanhToan();
        this.trangThaiGiaoDich = hoaDon.getTrangThaiGiaoDich();
        this.trangThaiHoaDon = hoaDon.getTrangThaiHoaDon();
        this.ngayTao = hoaDon.getNgayTao();
        this.nguoiTao = hoaDon.getNguoiTao();
        this.ngayCapNhat = hoaDon.getNgayCapNhat();
        this.nguoiCapNhat = hoaDon.getNguoiCapNhat();

        this.chiTietHoaDon = hoaDon.getChiTietHoaDon().stream().map(ct -> {
            AdminChiTietHoaDon dto = new AdminChiTietHoaDon();
            dto.setId(ct.getId());
            dto.setSoLuong(ct.getSoLuong());
            dto.setThanhTien(ct.getThanhTien());

            ChiTietGiay ctg = ct.getChiTietGiay();
            AdminChiTietGiay chiTietGiayDto = new AdminChiTietGiay();
            chiTietGiayDto.setId(ctg.getId());
            chiTietGiayDto.setSku(ctg.getSku());
            chiTietGiayDto.setGiaBan(ctg.getGiaBan());
            chiTietGiayDto.setGiaNhap(ctg.getGiaNhap());
            chiTietGiayDto.setMauSac(ctg.getMauSac());
            chiTietGiayDto.setSize(ctg.getSize());
            chiTietGiayDto.setAnh(ctg.getAnh());
            chiTietGiayDto.setSoLuong(ctg.getSoLuong());
            chiTietGiayDto.setTrangThai(ctg.getTrangThai());

            Giay giay = ctg.getGiay();
            AdminGiay giayDto = new AdminGiay();
            giayDto.setId(giay.getId());
            giayDto.setTenSanPham(giay.getTenSanPham());
            giayDto.setMoTaSanPham(giay.getMoTaSanPham());
            giayDto.setTrangThai(giay.getTrangThai());
            giayDto.setDanhMuc(giay.getDanhMuc());
            giayDto.setDanhMucCon(giay.getDanhMucCon());
            giayDto.setThuongHieu(giay.getThuongHieu());
            giayDto.setChatLieu(giay.getChatLieu());
            giayDto.setXuatXu(giay.getXuatXu());
            giayDto.setKieuDang(giay.getKieuDang());
            giayDto.setTuKhoa(giay.getTuKhoa());
            giayDto.setUuTien(giay.getUuTien());

            chiTietGiayDto.setGiay(giayDto);
            dto.setChiTietGiay(chiTietGiayDto);
            return dto;
        }).toList();
    }
}
