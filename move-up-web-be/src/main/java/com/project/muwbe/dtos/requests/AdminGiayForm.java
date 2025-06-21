package com.project.muwbe.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminGiayForm {
    private String tenGiay;
    private String moTaGiay;
    private Boolean trangThai;
    private Long idDanhMuc;
    private Long idDanhMucCon;
    private String thuongHieu;
    private String chatLieu;
    private String xuatXu;
    private String kieuDang;
    private String tuKhoa;
    private Integer uuTien;
    private Long nguoiTao;
    private Long nguoiCapNhat;
    private List<ChiTietGiay> chiTietGiay;
    private List<AnhGiay> anhGiay;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ChiTietGiay {
        private String sku;
        private Long giaNhap;
        private Long giaBan;
        private String mauSac;
        private Integer size;
        private Long idAnh;
        private Long soLuong;
        private Boolean trangThai;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AnhGiay {
        private Long idAnh;
    }
}
