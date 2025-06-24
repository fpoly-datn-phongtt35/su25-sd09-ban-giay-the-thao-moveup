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
public class ClientGioHangForm {
    private Long id;
    private Long idKhachHang;
    private Integer tongSoLuong;
    private Long tongTien;
    private Boolean trangThai;
    private Timestamp ngayTao;
    private Long idNguoiTao;
    private Timestamp ngayCapNhat;
    private Long idNguoiCapNhat;
    private List<ClientChiTietGioHang> chiTietGioHang;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ClientChiTietGioHang {
        private Long id;
        private Long idChiTietGiay;
        private Integer soLuong;
        private Long thanhTien;
    }
}
