package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiayList {
    private Long id;
    private String tenGiay;
    private String moTaGiay;
    private Boolean trangThai;
    private DanhMuc danhMuc;
    private DanhMucCon danhMucCon;
    private String thuongHieu;
    private String chatLieu;
    private String xuatXu;
    private String kieuDang;
    private String tuKhoa;
    private Integer uuTien;
    private Timestamp ngayTao;
    private TaiKhoan nguoiTao;
    private Timestamp ngayCapNhat;
    private TaiKhoan nguoiCapNhat;
    private List<GiayChiTietGiay> chiTietGiay;
    private List<AnhGiayList> anhGiay;

    public GiayList(Giay giay) {
        this.id = giay.getId();
        this.tenGiay = giay.getTenGiay();
        this.moTaGiay = giay.getMoTaGiay();
        this.trangThai = giay.getTrangThai();
        this.danhMuc = giay.getDanhMuc();
        this.danhMucCon = giay.getDanhMucCon();
        this.thuongHieu = giay.getThuongHieu();
        this.chatLieu = giay.getChatLieu();
        this.xuatXu = giay.getXuatXu();
        this.kieuDang = giay.getKieuDang();
        this.tuKhoa = giay.getTuKhoa();
        this.uuTien = giay.getUuTien();
        this.ngayTao = giay.getNgayTao();
        this.nguoiTao = giay.getNguoiTao();
        this.ngayCapNhat = giay.getNgayCapNhat();
        this.nguoiCapNhat = giay.getNguoiCapNhat();

        // Chi tiết giày
        if (giay.getChiTietGiay() != null) {
            this.chiTietGiay = giay.getChiTietGiay().stream()
                    .map(ct -> new GiayChiTietGiay(
                            ct.getId(),
                            ct.getSku(),
                            ct.getGiaNhap(),
                            ct.getGiaBan(),
                            ct.getMauSac(),
                            ct.getSize(),
                            ct.getAnh(),
                            ct.getSoLuong(),
                            ct.getTrangThai()
                    ))
                    .collect(Collectors.toList());
        } else {
            this.chiTietGiay = null;
        }

        // Ảnh giày chính
        if (giay.getAnhGiay() != null) {
            this.anhGiay = giay.getAnhGiay().stream()
                    .map(ag -> new AnhGiayList(ag.getId(), ag.getAnh()))
                    .collect(Collectors.toList());
        } else {
            this.anhGiay = null;
        }
    }
}
