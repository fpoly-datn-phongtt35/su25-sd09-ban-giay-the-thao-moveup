package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.ChiTietGiay;
import com.project.muwbe.entities.Giay;
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
        private String moTaSanPham;
        private String thuongHieu;
        private String chatLieu;
        private String xuatXu;
        private String kieuDang;
        private String tuKhoa;
        private Integer uuTien;
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

    public AdminChiTietGiay(ChiTietGiay entity) {
        this.id = entity.getId();
        this.sku = entity.getSku();
        this.giaNhap = entity.getGiaNhap() != null ? BigDecimal.valueOf(entity.getGiaNhap()) : null;
        this.giaBan = entity.getGiaBan() != null ? BigDecimal.valueOf(entity.getGiaBan()) : null;
        this.mauSac = entity.getMauSac();
        this.size = entity.getSize();
        this.anh = entity.getAnh();
        this.soLuong = entity.getSoLuong();
        this.trangThai = entity.getTrangThai();

        if (entity.getGiay() != null) {
            com.project.muwbe.entities.Giay g = entity.getGiay();
            this.giay = new Giay(
                    g.getId(),
                    g.getTenSanPham(),
                    g.getMoTaSanPham(),
                    g.getThuongHieu(),
                    g.getChatLieu(),
                    g.getXuatXu(),
                    g.getKieuDang(),
                    g.getTuKhoa(),
                    g.getUuTien(),
                    g.getTrangThai(),
                    g.getDanhMuc() != null
                            ? new DanhMuc(g.getDanhMuc().getId(), g.getDanhMuc().getTenDanhMuc())
                            : null,
                    g.getDanhMucCon() != null
                            ? new DanhMucCon(g.getDanhMucCon().getId(), g.getDanhMucCon().getTenDanhMucCon())
                            : null
            );
        }
    }

}
