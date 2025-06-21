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
@Table(name = "giay")
public class Giay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_giay")
    private String tenGiay;

    @Column(name = "mo_ta_giay")
    private String moTaGiay;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc", referencedColumnName = "id")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc_con", referencedColumnName = "id")
    private DanhMucCon danhMucCon;

    @Column(name = "thuong_hieu")
    private String thuongHieu;

    @Column(name = "chat_lieu")
    private String chatLieu;

    @Column(name = "xuat_xu")
    private String xuatXu;

    @Column(name = "kieu_dang")
    private String kieuDang;

    @Column(name = "tu_khoa")
    private String tuKhoa;

    @Column(name = "uu_tien")
    private Integer uuTien;

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

    @OneToMany(mappedBy = "giay", fetch = FetchType.LAZY)
    private List<AnhGiay> anhGiay;

    @OneToMany(mappedBy = "giay", fetch = FetchType.LAZY)
    private List<ChiTietGiay> chiTietGiay;
}
