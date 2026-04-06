USE QuanLyHocSinh;
GO

-- 1. Thêm Đối tượng ưu tiên
INSERT INTO DoiTuongUuTien (MaDT, TenDT, TiLeGiamHocPhi) 
VALUES 
('DT01', N'Hộ nghèo', 0.5), 
('DT02', N'Con thương binh', 1.0);

-- 2. Thêm Giáo viên (để làm GVCN)
INSERT INTO GiaoVien (MaGV, HoTen, NgaySinh, SDT) 
VALUES 
('GV01', N'Nguyễn Bá Đạt', '1985-05-20', '0901234567'), 
('GV02', N'Trần Thu Trang', '1990-11-15', '0912345678');

-- 3. Thêm Lớp
INSERT INTO Lop (MaLop, TenLop, NienKhoa, MaGVCN) 
VALUES 
('L10A1', N'10A1', '2023-2026', 'GV01'), 
('L10A2', N'10A2', '2023-2026', 'GV02'),
('L11A1', N'11A1', '2022-2025', 'GV01');
GO 
abc tesst
v
abc tesst
abc tesst
abc tesst
abc tesst
abc tesst
abc tesst
abc tesst