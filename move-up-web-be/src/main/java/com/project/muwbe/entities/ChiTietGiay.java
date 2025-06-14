package com.project.muwbe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_giay")
public class ChiTietGiay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_giay", referencedColumnName = "id")
    private Giay giay;

    @Column(name = "sku")
    private String sku;

    @Column(name = "gia_nhap")
    private Long giaNhap;

    @Column(name = "gia_ban")
    private Long giaBan;

    @Column(name = "mau_sac")
    private String mauSac;

    @Column(name = "size")
    private Integer size;

    @Column(name = "anh")
    private String anh;

    @Column(name = "so_luong")
    private Long soLuong;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}
