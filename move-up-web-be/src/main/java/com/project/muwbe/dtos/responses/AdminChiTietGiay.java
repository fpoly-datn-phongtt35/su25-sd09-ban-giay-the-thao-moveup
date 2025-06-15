package com.project.muwbe.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminChiTietGiay {

    private Long id;
    private String sku;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private String mauSac;
    private Integer size;
    private String anh;
    private Long soLuong;
    private Boolean trangThai;

    private Giay giay;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Giay {
        private Long id;
        private String tenSanPham;
        private String thuongHieu;
        private String chatLieu;
        private String xuatXu;
        private String kieuDang;
        private String tuKhoa;
        private Boolean trangThai;

        private DanhMuc danhMuc;
        private DanhMucCon danhMucCon;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DanhMuc {
        private Long id;
        private String tenDanhMuc;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DanhMucCon {
        private Long id;
        private String tenDanhMucCon;
    }
}
