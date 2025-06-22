CREATE DATABASE move_up_web
GO

USE move_up_web
GO

CREATE TABLE tai_khoan (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	email NVARCHAR(255),
	mat_khau NVARCHAR(255),
	vai_tro BIT, -- 1: CLIENT, 0: EMPLOYEE
	trang_thai NVARCHAR(50) --ACTIVE, INACTIVE, PENDING, ...
)

CREATE TABLE khach_hang (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_khach_hang NVARCHAR(50) UNIQUE,
	id_tai_khoan BIGINT,
	ho_ten NVARCHAR(255),
	so_dien_thoai NVARCHAR(20),
	gioi_tinh BIT, -- 1: Nam, 0: Nu
	ngay_sinh DATETIME,
	id_anh_dai_dien BIGINT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
);

CREATE TABLE dia_chi_khach_hang (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_khach_hang BIGINT,
	tinh_thanh_pho NVARCHAR(100),
	quan_huyen NVARCHAR(100),
	phuong_xa NVARCHAR(100),
	dia_chi_cu_the NVARCHAR(255),
	uu_tien BIT,
	trang_thai BIT -- 1: ACTIVE, 0: INACTIVE
)

CREATE TABLE nhan_vien (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_nhan_vien NVARCHAR(50) UNIQUE,
	id_tai_khoan BIGINT,
	ho_ten NVARCHAR(255),
	so_dien_thoai NVARCHAR(20),
	dia_chi NVARCHAR(255),
	gioi_tinh BIT, -- 1: Nam, 0: Nu
	id_anh_dai_dien BIGINT,
	vai_tro NVARCHAR(100), -- EMPLOYEE, MANAGER, ADMIN, ...
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
);

CREATE TABLE giay (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_giay NVARCHAR(255),
	mo_ta_giay NVARCHAR(255),
	trang_thai BIT, -- 1: ACTIVE, 0: INACTIVE
	id_danh_muc BIGINT,
	id_danh_muc_con BIGINT,
	thuong_hieu NVARCHAR(255),
	chat_lieu NVARCHAR(255),
	xuat_xu NVARCHAR(255),
	kieu_dang NVARCHAR(255),
	tu_khoa NVARCHAR(255),
	uu_tien INT, -- Từ 1 đến vô cực, 1 là cao nhất
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE chi_tiet_giay (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_giay BIGINT,
	sku NVARCHAR(255) UNIQUE, --Mã sản phẩm chi tiết
	gia_nhap BIGINT,
	gia_ban BIGINT,
	mau_sac NVARCHAR(100),
	size INT,
	id_anh BIGINT,
	so_luong BIGINT,
	trang_thai BIT -- 1: ACTIVE, 0: INACTIVE
);

CREATE TABLE danh_muc_con (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_danh_muc BIGINT,
	ten_danh_muc_con NVARCHAR(255),
	trang_thai BIT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE danh_muc (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_danh_muc NVARCHAR(255),
	trang_thai BIT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

/* CREATE TABLE nha_cung_cap (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_nha_cung_cap NVARCHAR(50),
	ten_nha_cung_cap NVARCHAR(255),
	dia_chi NVARCHAR(255),
	so_dien_thoai NVARCHAR(20)
) */

CREATE TABLE gio_hang (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_khach_hang BIGINT,
	tong_so_luong INT,
	tong_tien BIGINT,
	trang_thai BIT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE chi_tiet_gio_hang (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_gio_hang BIGINT,
	id_chi_tiet_giay BIGINT,
	so_luong INT,
	thanh_tien BIGINT
)

CREATE TABLE hoa_don (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_hoa_don NVARCHAR(50) UNIQUE,
	id_co_so BIGINT,
	id_khach_hang BIGINT,
	ten_khach_hang NVARCHAR(255),
	so_dien_thoai_khach_hang NVARCHAR(20),
	email_khach_hang NVARCHAR(255),
	dia_chi_khach_hang NVARCHAR(255),
	id_nhan_vien BIGINT,
	ghi_chu NVARCHAR(255),
	ghi_chu_khach_hang NVARCHAR(255),
	don_van_chuyen BIT,
	tong_thanh_toan BIGINT,
	tien_khach_tra BIGINT,
	loai_hinh_thanh_toan NVARCHAR(50),
	trang_thai_giao_dich NVARCHAR(50),
	trang_thai_hoa_don NVARCHAR(100),
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE chi_tiet_hoa_don (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_hoa_don BIGINT,
	id_chi_tiet_giay BIGINT,
	so_luong INT,
	thanh_tien BIGINT
)

CREATE TABLE co_so (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_cua_hang NVARCHAR(255),
	dia_chi_cua_hang NVARCHAR(255),
	so_dien_thoai_cua_hang NVARCHAR(20),
	ma_so_thue_cua_hang NVARCHAR(50),
	id_quan_ly BIGINT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE hoa_don_giam_gia (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_phieu_giam_gia BIGINT,
	id_dot_giam_gia BIGINT,
	id_hoa_don BIGINT
)

CREATE TABLE phieu_giam_gia (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ma_giam_gia NVARCHAR(50),
	ten_giam_gia NVARCHAR(255),
	phan_tram_giam INT,
	gia_tien_giam BIGINT,
	gia_tri_toi_thieu BIGINT,
	gia_tri_toi_da BIGINT,
	so_luong INT,
	ngay_bat_dau DATETIME,
	ngay_ket_thuc DATETIME,
	trang_thai BIT,
	mo_ta NVARCHAR(255),
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE chi_tiet_phieu_giam_gia (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_phieu_giam_gia BIGINT,
	id_giay BIGINT,
	id_danh_muc BIGINT,
	id_danh_muc_con BIGINT,
	toan_cua_hang BIT
)

CREATE TABLE dot_giam_gia (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_dot_giam_gia NVARCHAR(255),
	mo_ta NVARCHAR(255),
	phan_tram_giam INT,
	gia_tien_giam BIGINT,
	gia_tri_toi_thieu BIGINT,
	gia_tri_toi_da BIGINT,
	ngay_bat_dau DATETIME,
	ngay_ket_thuc DATETIME,
	trang_thai BIT,
	ngay_tao DATETIME,
	nguoi_tao BIGINT,
	ngay_cap_nhat DATETIME,
	nguoi_cap_nhat BIGINT
)

CREATE TABLE chi_tiet_dot_giam_gia (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_dot_giam_gia BIGINT,
	id_giay BIGINT,
	id_danh_muc BIGINT,
	id_danh_muc_con BIGINT,
	toan_cua_hang BIT
)

CREATE TABLE phieu_da_su_dung (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_khach_hang BIGINT,
	id_phieu_giam_gia BIGINT,
	id_hoa_don BIGINT,
	ngay_tao DATETIME
)

CREATE TABLE anh (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	ten_anh NVARCHAR(255),
	duong_dan NVARCHAR(255),
	ngay_tao DATETIME
)

CREATE TABLE anh_giay (
	id BIGINT PRIMARY KEY IDENTITY(1,1),
	id_giay BIGINT,
	id_anh BIGINT
)

-- Foreign keys for khach_hang
ALTER TABLE khach_hang ADD FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(id);
ALTER TABLE khach_hang ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE khach_hang ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);
ALTER TABLE khach_hang ADD FOREIGN KEY (id_anh_dai_dien) REFERENCES anh(id);

-- Foreign keys for dia_chi_khach_hang
ALTER TABLE dia_chi_khach_hang ADD FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id);

-- Foreign keys for nhan_vien
ALTER TABLE nhan_vien ADD FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(id);
ALTER TABLE nhan_vien ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE nhan_vien ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);
ALTER TABLE nhan_vien ADD FOREIGN KEY (id_anh_dai_dien) REFERENCES anh(id);

-- Foreign keys for giay
ALTER TABLE giay ADD FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id);
ALTER TABLE giay ADD FOREIGN KEY (id_danh_muc_con) REFERENCES danh_muc_con(id);

-- Foreign keys for chi_tiet_giay
ALTER TABLE chi_tiet_giay ADD FOREIGN KEY (id_giay) REFERENCES giay(id);
ALTER TABLE chi_tiet_giay ADD FOREIGN KEY (id_anh) REFERENCES anh(id);

-- Foreign keys for danh_muc_con
ALTER TABLE danh_muc_con ADD FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id);
ALTER TABLE danh_muc_con ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE danh_muc_con ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for danh_muc
ALTER TABLE danh_muc ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE danh_muc ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for gio_hang
ALTER TABLE gio_hang ADD FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id);
ALTER TABLE gio_hang ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE gio_hang ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for chi_tiet_gio_hang
ALTER TABLE chi_tiet_gio_hang ADD FOREIGN KEY (id_gio_hang) REFERENCES gio_hang(id);
ALTER TABLE chi_tiet_gio_hang ADD FOREIGN KEY (id_chi_tiet_giay) REFERENCES chi_tiet_giay(id);

-- Foreign keys for hoa_don
ALTER TABLE hoa_don ADD FOREIGN KEY (id_co_so) REFERENCES co_so(id);
ALTER TABLE hoa_don ADD FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id);
ALTER TABLE hoa_don ADD FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id);
ALTER TABLE hoa_don ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE hoa_don ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for chi_tiet_hoa_don
ALTER TABLE chi_tiet_hoa_don ADD FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id);
ALTER TABLE chi_tiet_hoa_don ADD FOREIGN KEY (id_chi_tiet_giay) REFERENCES chi_tiet_giay(id);

-- Foreign keys for co_so
ALTER TABLE co_so ADD FOREIGN KEY (id_quan_ly) REFERENCES nhan_vien(id);
ALTER TABLE co_so ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE co_so ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for hoa_don_giam_gia
ALTER TABLE hoa_don_giam_gia ADD FOREIGN KEY (id_phieu_giam_gia) REFERENCES phieu_giam_gia(id);
ALTER TABLE hoa_don_giam_gia ADD FOREIGN KEY (id_dot_giam_gia) REFERENCES dot_giam_gia(id);
ALTER TABLE hoa_don_giam_gia ADD FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id);

-- Foreign keys for chi_tiet_phieu_giam_gia
ALTER TABLE chi_tiet_phieu_giam_gia ADD FOREIGN KEY (id_phieu_giam_gia) REFERENCES phieu_giam_gia(id);
ALTER TABLE chi_tiet_phieu_giam_gia ADD FOREIGN KEY (id_giay) REFERENCES giay(id);
ALTER TABLE chi_tiet_phieu_giam_gia ADD FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id);
ALTER TABLE chi_tiet_phieu_giam_gia ADD FOREIGN KEY (id_danh_muc_con) REFERENCES danh_muc_con(id);

-- Foreign keys for dot_giam_gia
ALTER TABLE dot_giam_gia ADD FOREIGN KEY (nguoi_tao) REFERENCES tai_khoan(id);
ALTER TABLE dot_giam_gia ADD FOREIGN KEY (nguoi_cap_nhat) REFERENCES tai_khoan(id);

-- Foreign keys for chi_tiet_dot_giam_gia
ALTER TABLE chi_tiet_dot_giam_gia ADD FOREIGN KEY (id_dot_giam_gia) REFERENCES dot_giam_gia(id);
ALTER TABLE chi_tiet_dot_giam_gia ADD FOREIGN KEY (id_giay) REFERENCES giay(id);
ALTER TABLE chi_tiet_dot_giam_gia ADD FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id);
ALTER TABLE chi_tiet_dot_giam_gia ADD FOREIGN KEY (id_danh_muc_con) REFERENCES danh_muc_con(id);

-- Foreign keys for phieu_da_su_dung
ALTER TABLE phieu_da_su_dung ADD FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id);
ALTER TABLE phieu_da_su_dung ADD FOREIGN KEY (id_phieu_giam_gia) REFERENCES phieu_giam_gia(id);
ALTER TABLE phieu_da_su_dung ADD FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id);

-- Foreign keys for anh_giay
ALTER TABLE anh_giay ADD FOREIGN KEY (id_giay) REFERENCES giay(id);
ALTER TABLE anh_giay ADD FOREIGN KEY (id_anh) REFERENCES anh(id);

INSERT INTO tai_khoan (email, mat_khau, vai_tro, trang_thai) VALUES 
	('user1@mail.com', 'pass1', 1, 'ACTIVE'),
	('user2@mail.com', 'pass2', 0, 'ACTIVE'),
	('user3@mail.com', 'pass3', 1, 'ACTIVE')

INSERT INTO khach_hang (ma_khach_hang, id_tai_khoan, ho_ten, so_dien_thoai, gioi_tinh, ngay_sinh, id_anh_dai_dien, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES 
('KH001', 1, N'Khách Hàng 1', '0900000001', 1, '2024-09-10 00:00:00', NULL, '2025-06-07 00:00:00', 1, '2025-06-07 00:00:00', 1),
('KH002', 2, N'Khách Hàng 2', '0900000002', 0, '2024-11-15 00:00:00', NULL, '2025-06-07 00:00:00', 2, '2025-06-07 00:00:00', 2),
('KH003', 3, N'Khách Hàng 3', '0900000003', 1, '2024-07-21 00:00:00', NULL, '2025-06-07 00:00:00', 3, '2025-06-07 00:00:00', 3);

INSERT INTO nhan_vien (ma_nhan_vien, id_tai_khoan, ho_ten, so_dien_thoai, dia_chi, gioi_tinh, id_anh_dai_dien, vai_tro, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES 
('NV001', 1, N'Nhân Viên 1', '0910000001', N'Địa chỉ NV1', 1, NULL, 'EMPLOYEE', '2025-06-07 00:00:00', 1, '2025-06-07 00:00:00', 1),
('NV002', 2, N'Nhân Viên 2', '0910000002', N'Địa chỉ NV2', 0, NULL, 'EMPLOYEE', '2025-06-07 00:00:00', 2, '2025-06-07 00:00:00', 2),
('NV003', 3, N'Nhân Viên 3', '0910000003', N'Địa chỉ NV3', 1, NULL, 'EMPLOYEE', '2025-06-07 00:00:00', 3, '2025-06-07 00:00:00', 3);

INSERT INTO dia_chi_khach_hang(id_khach_hang, tinh_thanh_pho, quan_huyen, phuong_xa, dia_chi_cu_the, uu_tien, trang_thai) VALUES
(1, N'TP Hà Nội', N'huyện Đông Anh', N'xã Nam Hồng', N'nhà 123', 1, 1),
(2, N'HCM', N'huyện Đông Anh', N'xã Nam Hồng', N'nhà 123', 0, 1),
(3, N'TP Hà Nội', N'huyện Sóc Sơn', N'xã Phú Cường', N'nhà 123', 1, 0)

-- danh_muc
INSERT INTO danh_muc (ten_danh_muc, trang_thai, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
(N'Giày thể thao', 1, '2025-01-01', 1, '2025-01-10', 1),
(N'Giày da', 1, '2025-01-02', 2, '2025-01-11', 2),
(N'Sneaker', 1, '2025-01-03', 3, '2025-01-12', 3);

-- danh_muc_con
INSERT INTO danh_muc_con (id_danh_muc, ten_danh_muc_con, trang_thai, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
(1, N'Giày chạy bộ', 1, '2025-01-05', 1, '2025-01-15', 1),
(2, N'Giày công sở', 1, '2025-01-06', 2, '2025-01-16', 2),
(3, N'Giày thời trang', 1, '2025-01-07', 3, '2025-01-17', 3);

-- giay
INSERT INTO giay (ten_giay, mo_ta_giay, trang_thai, id_danh_muc_con, thuong_hieu, chat_lieu, xuat_xu, kieu_dang, tu_khoa, uu_tien) VALUES
(N'Giày Nike Air Zoom', N'Giày thể thao chạy bộ chất lượng cao', 1, 1, N'Nike', N'Vải lưới', N'Mỹ', N'Thể thao', N'chạy, nike, thể thao', 1),
(N'Giày Oxford nam da bò', N'Phong cách công sở cổ điển', 1, 2, N'Bally', N'Da bò', N'Italia', N'Công sở', N'da, nam, oxford', 2),
(N'Sneaker Adidas Streetwear', N'Phong cách trẻ trung cá tính', 1, 3, N'Adidas', N'Canvas', N'Đức', N'Casual', N'adidas, sneaker, street', 3);

-- chi_tiet_giay
INSERT INTO chi_tiet_giay (id_giay, sku, gia_nhap, gia_ban, mau_sac, size, id_anh, so_luong, trang_thai) VALUES
(1, 'NK-AIRZ-001', 1500000, 2500000, N'Xanh đen', 42, NULL, 10, 1),
(2, 'OX-DA-002', 1200000, 2200000, N'Nâu', 41, NULL, 5, 1),
(3, 'ADD-STREET-003', 1300000, 2300000, N'Trắng', 43, NULL, 8, 1);

INSERT INTO co_so (
	ten_cua_hang, dia_chi_cua_hang, so_dien_thoai_cua_hang, ma_so_thue_cua_hang,
	id_quan_ly, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
(N'Cửa hàng Giày Alpha', N'12 Lý Thái Tổ, Hà Nội', '0912345678', 'MST001', 1, GETDATE(), 1, GETDATE(), 1),
(N'Cửa hàng Giày Beta', N'34 Nguyễn Huệ, TP.HCM', '0987654321', 'MST002', 1, GETDATE(), 1, GETDATE(), 1),
(N'Cửa hàng Giày Gamma', N'56 Trần Hưng Đạo, Đà Nẵng', '0909090909', 'MST003', 1, GETDATE(), 1, GETDATE(), 1);


INSERT INTO hoa_don (ma_hoa_don, id_co_so, id_khach_hang, ten_khach_hang, so_dien_thoai_khach_hang,email_khach_hang, 
dia_chi_khach_hang, id_nhan_vien, ghi_chu, ghi_chu_khach_hang,don_van_chuyen, tong_thanh_toan, tien_khach_tra, loai_hinh_thanh_toan,
trang_thai_giao_dich, trang_thai_hoa_don, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
('HD001', 1, 1, N'Nguyễn Văn A', '0912345678', 'a@gmail.com', N'123 Trần Hưng Đạo', 1, N'Giao hàng nhanh', N'Không đổi địa chỉ', 1, 1000000, 1000000, N'Tiền mặt', N'Thành công', N'Đã thanh toán', GETDATE(), 1, GETDATE(), 1),
('HD002', 1, 1, N'Trần Thị B', '0987654321', 'b@gmail.com', N'456 Lý Thường Kiệt', 1, N'Giao trong giờ hành chính', N'Cẩn thận sản phẩm', 1, 2000000, 2000000, N'Chuyển khoản', N'Thành công', N'Đã thanh toán', GETDATE(), 1, GETDATE(), 1),
('HD003', 1, 1, N'Lê Văn C', '0909090909', 'c@gmail.com', N'789 Nguyễn Huệ', 1, N'Giao cuối tuần', N'Không gọi điện', 0, 1500000, 1500000, N'Momo', N'Thành công', N'Đã thanh toán', GETDATE(), 1, GETDATE(), 1);

INSERT INTO chi_tiet_hoa_don (id_hoa_don, id_chi_tiet_giay, so_luong, thanh_tien) VALUES
(2, 1, 2, 1000000)


-- phieu_giam_gia
INSERT INTO phieu_giam_gia (ma_giam_gia, ten_giam_gia, phan_tram_giam, gia_tien_giam, gia_tri_toi_thieu, gia_tri_toi_da, so_luong, ngay_bat_dau, ngay_ket_thuc, trang_thai, mo_ta, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
('PGG001', N'Giảm giá khai trương', 10, 100000, 500000, 2000000, 100, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, N'Áp dụng toàn bộ sản phẩm', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1),
('PGG002', N'Ưu đãi sinh nhật', 15, 150000, 700000, 2500000, 50, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, N'Chỉ cho khách VIP', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1),
('PGG003', N'Flash sale', 20, 200000, 1000000, 3000000, 200, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, N'Áp dụng từ 12h-14h', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1);

-- dot_giam_gia
INSERT INTO dot_giam_gia (ten_dot_giam_gia, mo_ta, phan_tram_giam, gia_tien_giam, gia_tri_toi_thieu, gia_tri_toi_da, ngay_bat_dau, ngay_ket_thuc, trang_thai, ngay_tao, nguoi_tao, ngay_cap_nhat, nguoi_cap_nhat) VALUES
(N'Tết Sale', N'Ưu đãi lớn dịp Tết', 25, 250000, 1000000, 3000000, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1),
(N'Mid-year Sale', N'Giữa năm giảm giá', 30, 300000, 1500000, 3500000, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1),
(N'Black Friday', N'Ngày hội mua sắm', 40, 400000, 2000000, 5000000, '2025-06-07 05:26:02', '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1, '2025-06-07 05:26:02', 1);

/*-- hoa_don_giam_gia
INSERT INTO hoa_don_giam_gia (id_phieu_giam_gia, id_dot_giam_gia, id_hoa_don) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3); */

-- chi_tiet_phieu_giam_gia
INSERT INTO chi_tiet_phieu_giam_gia (id_phieu_giam_gia, id_giay, id_danh_muc, id_danh_muc_con, toan_cua_hang) VALUES
(1, 1, 1, 1, 1),
(2, 2, 1, 2, 0),
(3, 3, 2, 3, 1);

-- chi_tiet_dot_giam_gia
INSERT INTO chi_tiet_dot_giam_gia (id_dot_giam_gia, id_giay, id_danh_muc, id_danh_muc_con, toan_cua_hang) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 0),
(3, 3, 3, 3, 1);

/*-- phieu_da_su_dung
INSERT INTO phieu_da_su_dung (id_khach_hang, id_phieu_giam_gia, id_hoa_don, ngay_tao) VALUES
(1, 1, 1, '2025-06-07 05:26:02'),
(2, 2, 2, '2025-06-07 05:26:02'),
(3, 3, 3, '2025-06-07 05:26:02'); */

SELECT * FROM chi_tiet_giay;
