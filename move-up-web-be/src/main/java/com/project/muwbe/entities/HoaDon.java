package com.project.muwbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hoa_don")
public class HoaDon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoa_don", unique = true, nullable = false)
    private String maHoaDon;

    @ManyToOne
    @JoinColumn(name = "id_co_so", referencedColumnName = "id")
    private CoSo coSo;

    @ManyToOne
    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    private KhachHang khachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column(name = "so_dien_thoai_khach_hang")
    private String soDienThoaiKhachHang;

    @Column(name = "email_khach_hang")
    private String emailKhachHang;

    @Column(name = "dia_chi_khach_hang")
    private String diaChiKhachHang;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien", referencedColumnName = "id")
    private NhanVien nhanVien;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ghi_chu_khach_hang")
    private String ghiChuKhachHang;

    @Column(name = "don_van_chuyen")
    private Boolean donVanChuyen;

    @Column(name = "tong_thanh_toan")
    private Long tongThanhToan;

    @Column(name = "tien_khach_tra")
    private Long tienKhachTra;

    @Column(name = "loai_hinh_thanh_toan")
    private String loaiHinhThanhToan;

    @Column(name = "trang_thai_giao_dich")
    private String trangThaiGiaoDich;

    @Column(name = "trang_thai_hoa_don")
    private String trangThaiHoaDon;

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

    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY)
    private List<ChiTietHoaDon> chiTietHoaDon;
}
