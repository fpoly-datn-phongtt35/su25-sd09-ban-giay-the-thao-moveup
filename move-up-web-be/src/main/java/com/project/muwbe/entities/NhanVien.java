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
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nhan_vien", unique = true, nullable = false)
    private String maNhanVien;

    @OneToOne
    @JoinColumn(name = "id_tai_khoan", referencedColumnName = "id")
    private TaiKhoan taiKhoan;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @Column(name = "anh_dai_dien")
    private String anhDaiDien;

    @Column(name = "vai_tro")
    private String vaiTro;

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
