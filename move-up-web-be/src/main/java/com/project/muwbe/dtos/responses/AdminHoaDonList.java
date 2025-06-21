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
    private List<ChiTietHoaDonList> chiTietHoaDon;

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
            ChiTietHoaDonList dto = new ChiTietHoaDonList();
            dto.setId(ct.getId());
            dto.setSoLuong(ct.getSoLuong());
            dto.setThanhTien(ct.getThanhTien());

            ChiTietGiay ctg = ct.getChiTietGiay();
            HoaDonChiTietGiay chiTietGiayDto = new HoaDonChiTietGiay();
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
            HoaDonGiay giayDto = new HoaDonGiay();
            giayDto.setId(giay.getId());
            giayDto.setTenGiay(giay.getTenGiay());
            giayDto.setMoTaGiay(giay.getMoTaGiay());
            giayDto.setTrangThai(giay.getTrangThai());
            giayDto.setDanhMuc(giay.getDanhMuc());
            giayDto.setDanhMucCon(giay.getDanhMucCon());
            giayDto.setThuongHieu(giay.getThuongHieu());
            giayDto.setChatLieu(giay.getChatLieu());
            giayDto.setXuatXu(giay.getXuatXu());
            giayDto.setKieuDang(giay.getKieuDang());
            giayDto.setTuKhoa(giay.getTuKhoa());
            giayDto.setUuTien(giay.getUuTien());
            giayDto.setNgayTao(giay.getNgayTao());
            giayDto.setNguoiTao(giay.getNguoiTao());
            giayDto.setNgayCapNhat(giay.getNgayCapNhat());
            giayDto.setNguoiCapNhat(giay.getNguoiCapNhat());

            if (giay.getAnhGiay() != null) {
                List<AnhGiayList> anhListDtos = giay.getAnhGiay().stream().map(ag -> {
                    AnhGiayList anhDto = new AnhGiayList();
                    anhDto.setId(ag.getId());
                    anhDto.setAnh(ag.getAnh());
                    return anhDto;
                }).toList();
                giayDto.setAnhGiay(anhListDtos);
            }

            chiTietGiayDto.setGiay(giayDto);
            dto.setChiTietGiay(chiTietGiayDto);
            return dto;
        }).toList();
    }
}
