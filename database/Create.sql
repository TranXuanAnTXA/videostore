USE videostore_db
GO
-- 1. Chèn 4 Thể loại phim cơ bản (Category) - Đã thay bằng link ảnh hiển thị thật
INSERT INTO Categories (CategoryId, CategoryName, Images, Status) 
VALUES 
('CAT01', N'Hành Động - Bom Tấn', 'https://placehold.co/600x400/e74c3c/FFF?text=Action', 1), 
('CAT02', N'Khoa Học Viễn Tưởng', 'https://placehold.co/600x400/3498db/FFF?text=Sci-Fi', 1), 
('CAT03', N'Hoạt Hình Anime', 'https://placehold.co/600x400/2ecc71/FFF?text=Anime', 1), 
('CAT04', N'Tình Cảm Lãng Mạn', 'https://placehold.co/600x400/9b59b6/FFF?text=Romance', 1);

-- 2. Chèn 12 Phim (Videos) - Các link m.media-amazon.com này đều đang hoạt động
-- Thể loại 1: Hành Động (4 phim)
INSERT INTO Videos (Title, Poster, Views, Description, Active, CategoryId) VALUES 
(N'Avengers: Endgame', 'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg', 1500, N'Trận chiến cuối cùng của các siêu anh hùng Marvel chống lại Thanos để cứu lấy vũ trụ.', 1, 'CAT01'),
(N'John Wick: Chapter 4', 'https://image.tmdb.org/t/p/w500/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg', 2300, N'Sát thủ John Wick đối mặt với những kẻ thù nguy hiểm nhất trên toàn cầu.', 1, 'CAT01'),
(N'Mission: Impossible - Fallout', 'https://m.media-amazon.com/images/M/MV5BNjRlZmM0ODktY2RjNS00ZDdjLWJhZGYtNDljNWZkMGM5MTg0XkEyXkFqcGdeQXVyNjAwMjI5MDk@._V1_.jpg', 1100, N'Ethan Hunt và đội IMF chạy đua với thời gian sau một nhiệm vụ thất bại.', 1, 'CAT01'),
(N'Mad Max: Fury Road', 'https://m.media-amazon.com/images/M/MV5BN2EwM2I5OWMtMGQyMi00Zjg1LWJkNTctZTdjYTA4OGUwZjMyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg', 1850, N'Cuộc truy đuổi nghẹt thở trên sa mạc hậu tận thế.', 1, 'CAT01');

-- Thể loại 2: Khoa Học Viễn Tưởng (3 phim)
INSERT INTO Videos (Title, Poster, Views, Description, Active, CategoryId) VALUES 
(N'Interstellar', 'https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg', 3200, N'Một nhóm phi hành gia du hành qua lỗ sâu để tìm kiếm hành tinh sống mới cho nhân loại.', 1, 'CAT02'),
(N'Inception', 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg', 2900, N'Kẻ trộm giấc mơ xâm nhập vào tiềm thức của mục tiêu để cấy ghép ý tưởng.', 1, 'CAT02'),
(N'Dune: Part One', 'https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg', 1450, N'Hành trình vĩ đại của Paul Atreides trên hành tinh cát Arrakis.', 1, 'CAT02');

-- Thể loại 3: Hoạt Hình Anime (3 phim)
INSERT INTO Videos (Title, Poster, Views, Description, Active, CategoryId) VALUES 
(N'Your Name', 'https://m.media-amazon.com/images/M/MV5BODRmZDVmNzUtZDA4ZC00NjhkLWI2M2UtN2M0ZDIzNDcxYThjL2ltYWdlXkEyXkFqcGdeQXVyNTk0MzMzODA@._V1_.jpg', 4500, N'Hai cô cậu học trò tình cờ hoán đổi cơ thể cho nhau qua những giấc mơ.', 1, 'CAT03'),
(N'Spirited Away', 'https://upload.wikimedia.org/wikipedia/vi/3/30/Spirited_Away_poster.JPG', 3800, N'Cô bé Chihiro lạc vào thế giới linh hồn để cứu cha mẹ mình bị biến thành heo.', 1, 'CAT03'),
(N'Spider-Man: Into the Spider-Verse', 'https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_.jpg', 2100, N'Miles Morales khám phá ra các vũ trụ song song và hợp sức cùng những Người Nhện khác.', 1, 'CAT03');

-- Thể loại 4: Tình Cảm Lãng Mạn (2 phim)
INSERT INTO Videos (Title, Poster, Views, Description, Active, CategoryId) VALUES 
(N'La La Land', 'https://m.media-amazon.com/images/M/MV5BMzUzNDM2NzM2MV5BMl5BanBnXkFtZTgwNTM3NTg4OTE@._V1_.jpg', 1700, N'Chuyện tình đẹp nhưng dang dở giữa một nhạc công jazz và một nữ diễn viên trẻ tại Los Angeles.', 1, 'CAT04'),
(N'Titanic', 'https://image.tmdb.org/t/p/w500/9xjZS2rlVxm8SFx8kPC3aIGCOYQ.jpg', 5500, N'Chuyến ra khơi định mệnh của con tàu khổng lồ và tình yêu huyền thoại của Jack - Rose.', 1, 'CAT04');