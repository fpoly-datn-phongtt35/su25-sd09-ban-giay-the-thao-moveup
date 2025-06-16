package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.DanhMuc;
import com.project.muwbe.entities.DanhMucCon;
import com.project.muwbe.entities.Giay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminGiayList {
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
    private List<AdminChiTietGiay> chiTietGiay;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AdminChiTietGiay{
        private Long id;
        private String sku;
        private Long giaNhap;
        private Long giaBan;
        private String mauSac;
        private Integer size;
        private String anh;
        private Long soLuong;
        private Boolean trangThai;
    }

    public AdminGiayList(Giay giay) {
        this.id = giay.getId();
        this.tenSanPham = giay.getTenSanPham();
        this.moTaSanPham = giay.getMoTaSanPham();
        this.trangThai = giay.getTrangThai();
        this.danhMuc = giay.getDanhMuc();
        this.danhMucCon = giay.getDanhMucCon();
        this.thuongHieu = giay.getThuongHieu();
        this.chatLieu = giay.getChatLieu();
        this.xuatXu = giay.getXuatXu();
        this.kieuDang = giay.getKieuDang();
        this.tuKhoa = giay.getTuKhoa();
        this.uuTien = giay.getUuTien();

        // ✅ Safe check for null
        if (giay.getChiTietGiay() != null) {
            this.chiTietGiay = giay.getChiTietGiay().stream().map(ct -> new AdminChiTietGiay(
                    ct.getId(),
                    ct.getSku(),
                    ct.getGiaNhap(),
                    ct.getGiaBan(),
                    ct.getMauSac(),
                    ct.getSize(),
                    ct.getAnh(),
                    ct.getSoLuong(),
                    ct.getTrangThai()
            )).collect(Collectors.toList());
        } else {
            this.chiTietGiay = null;
        }
    }
}
