package com.project.muwbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "co_so")
public class CoSo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_cua_hang")
    private String tenCuaHang;

    @Column(name = "dia_chi_cua_hang")
    private String diaChiCuaHang;

    @Column(name = "so_dien_thoai_cua_hang")
    private String soDienThoaiCuaHang;

    @Column(name = "ma_so_thue_cua_hang")
    private String maSoThueCuaHang;

    @ManyToOne
    @JoinColumn(name = "id_quan_ly", referencedColumnName = "id")
    private NhanVien quanLyCuaHang;

    @Column(name = "ngay_tao")
    private Timestamp ngayTao;

    @ManyToOne
    @JoinColumn(name = "nguoi_tao", referencedColumnName = "id")
    private TaiKhoan nguoiTao;

    @Column(name = "ngay_cap_nhat")
    private Timestamp ngayCapNhat;

    @ManyToOne
    @JoinColumn(name = "nguoi_cap_nhat", referencedColumnName = "id")
    private TaiKhoan nguoiCapNhat;
}
