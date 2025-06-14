package com.project.muwbe.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "mo_ta_san_pham")
    private String moTaSanPham;

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

    @OneToMany(mappedBy = "giay", fetch = FetchType.LAZY)
    private List<ChiTietGiay> chiTietGiay;
}
