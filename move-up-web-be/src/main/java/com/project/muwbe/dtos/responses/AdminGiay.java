package com.project.muwbe.dtos.responses;

import com.project.muwbe.entities.Giay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminGiay {
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

    public AdminGiay(Giay entity) {
        this.id = entity.getId();
        this.tenSanPham = entity.getTenSanPham();
        this.moTaSanPham = entity.getMoTaSanPham();
        this.thuongHieu = entity.getThuongHieu();
        this.chatLieu = entity.getChatLieu();
        this.xuatXu = entity.getXuatXu();
        this.kieuDang = entity.getKieuDang();
        this.tuKhoa = entity.getTuKhoa();
        this.uuTien = entity.getUuTien();
        this.trangThai = entity.getTrangThai();

        if (entity.getDanhMuc() != null) {
            this.danhMuc = new DanhMuc(
                    entity.getDanhMuc().getId(),
                    entity.getDanhMuc().getTenDanhMuc()
            );
        }

        if (entity.getDanhMucCon() != null) {
            this.danhMucCon = new DanhMucCon(
                    entity.getDanhMucCon().getId(),
                    entity.getDanhMucCon().getTenDanhMucCon()
            );
        }
    }
}
