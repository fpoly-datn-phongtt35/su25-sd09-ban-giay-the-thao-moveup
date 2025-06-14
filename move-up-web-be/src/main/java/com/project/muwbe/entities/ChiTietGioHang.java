package com.project.muwbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "chi_tiet_gio_hang")
public class ChiTietGioHang {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_gio_hang", referencedColumnName = "id")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "id_chi_tiet_giay", referencedColumnName = "id")
    private ChiTietGiay chiTietGiay;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "thanh_tien")
    private Long thanhTien;
}
