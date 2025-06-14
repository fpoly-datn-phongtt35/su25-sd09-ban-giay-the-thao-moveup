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
@Table(name = "phieu_giam_gia")
public class PhieuGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "")
    private String maGiamGia;

    private String tenGiamGia;

    private Integer phanTramGiam;

    private Long giaTienGiam;

    private Long giaTriToiThieu;

    private Long giaTriToiDa;

    private Integer soLuong;

    private Timestamp ngayBatDau;

    private Timestamp ngayKetThuc;

    private Boolean trangThai;

    private String moTa;

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
