package com.project.muwbe.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GiayRequest {
    private String tenSanPham;
    private String moTaSanPham;
    private Boolean trangThai;
    private Long idDanhMuc;
    private Long idDanhMucCon;
    private String thuongHieu;
    private String chatLieu;
    private String xuatXu;
    private String kieuDang;
    private String tuKhoa;
    private Integer uuTien;
    private List<ChiTietGiay> chiTietGiay;

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
        private String anh;
        private Long soLuong;
        private Boolean trangThai;
    }

}
