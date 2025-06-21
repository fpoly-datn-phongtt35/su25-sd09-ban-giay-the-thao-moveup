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
@Table(name = "dot_giam_gia")
public class DotGiamGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_dot_giam_gia")
    private String tenDotGiamGia;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "phan_tram_giam")
    private Integer phanTramGiam;

    @Column(name = "gia_tien_giam")
    private Long giaTienGiam;

    @Column(name = "gia_tri_toi_thieu")
    private Long giaTriToThieu;

    @Column(name = "gia_tri_toi_da")
    private Long giaTriToDa;

    @Column(name = "ngay_bat_dau")
    private Timestamp ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Timestamp ngayKetThuc;

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
