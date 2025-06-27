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
public class ClientGioHang {
    private Long id;
    private KhachHang khachHang;
    private Integer tongSoLuong;
    private Long tongTien;
    private Boolean trangThai;
    private Timestamp ngayTao;
    private TaiKhoan nguoiTao;
    private Timestamp ngayCapNhat;
    private TaiKhoan nguoiCapNhat;
    private List<ClientChiTietGioHangList> chiTietGioHangList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ClientChiTietGioHangList {
        private Long id;
        private HoaDonChiTietGiay chiTietGiay;
        private Integer soLuong;
        private Long thanhTien;
    }

    public ClientGioHang(GioHang gioHang) {
        this.id = gioHang.getId();
        this.khachHang = gioHang.getKhachHang();
        this.tongSoLuong = gioHang.getTongSoLuong();
        this.tongTien = gioHang.getTongTien();
        this.trangThai = gioHang.getTrangThai();
        this.ngayTao = gioHang.getNgayTao();
        this.nguoiTao = gioHang.getNguoiTao();
        this.ngayCapNhat = gioHang.getNgayCapNhat();
        this.nguoiCapNhat = gioHang.getNguoiCapNhat();

        this.chiTietGioHangList = gioHang.getChiTietGioHang().stream().map(ct -> {
            ChiTietGiay ctg = ct.getChiTietGiay();
            Giay giay = ctg.getGiay();

            HoaDonGiay giayDTO = new HoaDonGiay();
            giayDTO.setId(giay.getId());
            giayDTO.setTenGiay(giay.getTenGiay());
            giayDTO.setMoTaGiay(giay.getMoTaGiay());
            giayDTO.setTrangThai(giay.getTrangThai());
            giayDTO.setDanhMuc(giay.getDanhMuc());
            giayDTO.setDanhMucCon(giay.getDanhMucCon());
            giayDTO.setThuongHieu(giay.getThuongHieu());
            giayDTO.setChatLieu(giay.getChatLieu());
            giayDTO.setXuatXu(giay.getXuatXu());
            giayDTO.setKieuDang(giay.getKieuDang());
            giayDTO.setTuKhoa(giay.getTuKhoa());
            giayDTO.setUuTien(giay.getUuTien());
            giayDTO.setNgayTao(giay.getNgayTao());
            giayDTO.setNguoiTao(giay.getNguoiTao());
            giayDTO.setNgayCapNhat(giay.getNgayCapNhat());
            giayDTO.setNguoiCapNhat(giay.getNguoiCapNhat());
            if (giay.getAnhGiay() != null) {
                giayDTO.setAnhGiay(
                        giay.getAnhGiay().stream()
                                .map(ag -> new AnhGiayList(ag.getId(), ag.getAnh()))
                                .collect(Collectors.toList())
                );
            } else {
                giayDTO.setAnhGiay(null);
            }

            HoaDonChiTietGiay dto = new HoaDonChiTietGiay();
            dto.setId(ctg.getId());
            dto.setSku(ctg.getSku());
            dto.setGiay(giayDTO);
            dto.setGiaNhap(ctg.getGiaNhap());
            dto.setGiaBan(ctg.getGiaBan());
            dto.setMauSac(ctg.getMauSac());
            dto.setSize(ctg.getSize());
            dto.setAnh(ctg.getAnh());
            dto.setSoLuong(ctg.getSoLuong());
            dto.setTrangThai(ctg.getTrangThai());

            return new ClientChiTietGioHangList(
                    ct.getId(),
                    dto,
                    ct.getSoLuong(),
                    ct.getThanhTien()
            );
        }).toList();
    }
}
