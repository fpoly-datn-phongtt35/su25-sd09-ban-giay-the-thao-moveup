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
@Table(name = "danh_muc_con")
public class DanhMucCon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc", referencedColumnName = "id")
    private DanhMuc danhMuc;

    @Column(name = "ten_danh_muc_con")
    private String tenDanhMucCon;

    @Column(name = "trang_thai")
    private Boolean trangThai;

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
