-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2023 at 01:18 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaanuns`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE `anggota` (
  `id_anggota` varchar(20) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `prodi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `banyak_buku_dipinjam` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id_anggota`, `nama`, `nim`, `prodi`, `email`, `banyak_buku_dipinjam`) VALUES
('A1', 'Besta', 'K45', 'PTIK', 'hep@gmail.com', 0),
('A3', 'Seli Agustina', 'K3522076', 'PTIK', 'seliagustina@gmail.com', 3),
('A4', 'Maranatha Nur Chiesa', 'K3522042', 'PTIK', 'maranatha25@gmail.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `pengarang` varchar(255) NOT NULL,
  `penerbit` varchar(255) NOT NULL,
  `tahun_terbit` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `tahun_terbit`, `jumlah`) VALUES
('B1', 'Ndaru', 'Ara', 'Baba', 1908, 9),
('B105', 'Ilmu Sosial Budaya Dasar', 'Lies Sudibyo, dkk.', 'Andi', 2013, 3),
('B106', 'Live Coding!', 'Arif Akbarul Huda', 'Andi', 2013, 2),
('B107', 'Manajemen Proyek', 'Ibrar Husen', 'Andi', 2011, 8),
('B108', 'Sosiologi Agama', 'Sindung Haryanto', 'Ar-ruzz Media', 2016, 2),
('B109', 'Teori-teori Pendidikan', 'Nurani Soyomukti', 'Ar-ruzz Media', 2016, 7),
('B11', 'Seni Mendidik Anak Sesuai Tuntunan Islam', 'Dra. Sri Sugiarti , MPD', 'MITRA WACANA', 2013, 2),
('B110', 'Design dan Implementasi Sistem Embedded Mikrokontroler ATMega8535 dengan Bahasa Basic', 'Iswanto', 'Gava Media', 2008, 2),
('B111', 'Pengembangan Program W@p dengan WML dan PHP', 'Bunafit Nugroho', 'Gava Media', 2005, 6),
('B112', 'PLTMH (Pembangkit Listrik Tenaga Mikro Hridro)', 'Hunggul Y. S. H. Nugroho & M. Kudeng Sallata', 'Andi', 2015, 2),
('B114', 'Ndaru', 'Ndaruuuu', 'Naa', 1990, 2),
('B115', 'Arum', 'Ndaru', 'DUnia', 1990, 3),
('B116', 'Ndaru sayangaku', 'Bunga', 'Aru', 1990, 8),
('B117', 'Ara', 'ara', 'ara', 1990, 2),
('B118', 'gaga', 'mama', 'papa', 1990, 1),
('B119', 'Sak sak e', 'Seli', 'Agus', 1945, 2),
('B12', 'Shortcourse Series: Adobe Dreamweaver C56', 'Wahana Komputer', 'Andi Offset', 2013, 4),
('B120', 'Teluk alaska', 'Ara', 'Chiesa', 1990, 1),
('B121', 'Mathari', 'Bulan', 'Tere Liye', 1990, 2),
('B122', 'Mathari', 'Bulan', 'Tere Liye', 1990, 5),
('B123', 'Bubu', 'Yaya', 'Mama', 1990, 2),
('B124', 'Ndaru', 'Ndaru', 'Ndaru', 1990, 2),
('B125', 'Baba', 'Wawa', 'Bibi', 1988, 4),
('B13', 'Smart Creativepreneur', 'Ari Kurnia', 'Andi Offset', 2013, 3),
('B14', 'Strategi Migran Banjar', 'Taufik Arbain', 'LKiS', 2009, 4),
('B15', 'Teknik Dasar Vidiografi', 'Sarwo Nugroho, S.Kom, M.Kom', 'Andi Offset', 2014, 3),
('B16', 'Teknik Sampling ; Analisis Opini Publik', 'Eriyanto', 'LKiS', 2007, 4),
('B17', 'Teologi Pembebasan', 'Fx. Wahono', 'LKiS', 2013, 2),
('B18', 'Dasar-dasar Ilmu perpustakaan', 'Abdul Qadir Shaleh', 'Ar-Ruzz Media', 2016, 2),
('B19', 'Pendidikan di mata Soekarno', 'Syamsul Kurniawan', 'Ar-Ruzz Media', 2016, 5),
('B20', 'Guru Patriot', 'Rh. Widada', 'Ar-Ruzz Media', 2016, 2),
('B21', 'Personality Theories', 'George C. Boeree', 'Ar-Ruzz Media', 2016, 7),
('B22', 'Cara mudah berfilsafat', 'Nicholas Fearn', 'Ar-Ruzz Media', 2016, 2),
('B23', 'Soe Hok Gie', 'Muhammad Rifai', 'Ar-Ruzz Media', 2016, 4),
('B24', 'Tutorial Pemrograman Mobile (J2ME)', 'Azhari', 'Gava Media', 2009, 9),
('B25', 'Pengantar manajemen keuangan', 'Kasmir', 'Prenadamedia Grup', 2010, 4),
('B26', 'Ekonomi moneter', 'Lestari Ambarini', 'IN MEDIA', 2015, 1),
('B27', 'Asuhan kebidanan komunitas', 'Sandra G. J. Tombokan', 'IN MEDIA', 2014, 3),
('B28', 'Ekonomi Politik Media Penyiaran', 'Agus Sudibyo', 'LKIS', 2004, 1),
('B29', 'Dasar-dasar Ilmu Semantik', 'Suhardi', 'Ar-Ruzz Media', 2015, 8),
('B3', 'Kupas Tuntas : Microsoft Windows 8.1', 'Madcoms', 'Andi Offset', 2014, 1),
('B30', 'Pengantar Administrasi Pembangunan', 'EM. Lukman Hakim', 'Ar-Ruzz Media', 2016, 10),
('B31', 'Masyarakat dan Hukum Adat Batak Toba', 'J. C. Vergouwen', 'LKIS', 2004, 9),
('B32', 'Perencanaan Pendidikan', 'Matin', 'Rajawali Pers', 2015, 20),
('B33', 'Antropologi Al-Qur’an', 'Baidhowi', 'LKIS', 2013, 3),
('B34', 'Teori-teori Sosial Dalam Paradigma', 'Wirawan', 'Prenadamedia Grup', 2015, 2),
('B35', 'Akuntansi Perusahaan Manufaktur', 'Reschiwati', 'IN MEDIA', 2014, 2),
('B36', 'Hukum Islam', 'Muhammad Daud Ali', 'Rajawali Pers', 2015, 9),
('B37', 'Manajemen Perubahan', 'Wibowo', 'Rajawali Pers', 2016, 5),
('B38', 'Pengembangan Kurikulum', 'Abdulah Idi', 'Rajawali Pers', 2016, 2),
('B39', 'Alpha Teach Yourself', 'Nancy Mingus', 'Prenadamedia Grup', 2015, 3),
('B4', 'Langkah Mudah Belajar Kalkulus For IT(Khusus P. Jawa)', 'Sudaryono, Untung Rahardja, Edi S. Mulyanta', 'Andi Offset', 2012, 1),
('B40', 'Kamus Lengkap Psikologi', 'James P. Chaplin', 'Rajawali Pers', 2014, 6),
('B41', '	Perawatan Luka Modern Pada Anak', 'Anik Maryunani', 'Sagung Seto', 2016, 2),
('B42', 'Academic Engagement', 'Alimul Muniroh', 'LKIS', 2015, 7),
('B43', 'Dasar-dasar produk televisi', 'Andi Fachrudin', 'Prenadamedia Grup', 2014, 8),
('B44', 'Neurologi Dasar', 'Badrul Munir', 'Sagung Seto', 2015, 9),
('B45', 'Ekonomi Islam', 'Rozalinda', 'Rajawali Pers', 2015, 2),
('B46', 'Metode penelitian survei', 'Abuzar Asra', 'IN MEDIA', 2014, 5),
('B47', 'Teori dan Pemrograman Grafika Komputer dengan Visual C++ V.6 dan Open GL', 'Suyoto', 'Gava Media', 2003, 1),
('B48', 'Hutan Untuk Rakyat', 'Ismatul Hakim', 'LKIS', 2014, 5),
('B49', 'Ulumul Qur’an', 'Muhammad Amin Suma', 'Rajawali Pers', 2014, 2),
('B5', 'Manajemen Penanganan Barang-Barang Berbahaya Pada Angkatan Udara', 'Wynd Riyaldi & M Rifni', 'MITRA WACANA', 2013, 3),
('B50', 'Riset Bisnis dengan Analisi Jalur SPSS', 'Danang Sunyoto', 'Gava Media', 2011, 9),
('B51', 'Rahasia Pembagian Deviden dan Tata Kelola Perusahaan', 'Hery', 'Gava Media', 2013, 2),
('B52', 'Soekarno dan Nasakom', 'Nurani Soyomukti', 'Garasi', 2016, 2),
('B53', 'Sosiologi Perubahan Sosial', 'Nanang Martono', 'Rajawali Pers', 2016, 2),
('B54', 'Memahami Pos-Pos dan Angka-Angka Dalam Laporan Keuangan Untuk Orang Awam', 'Deanta', 'Gava Media', 2016, 2),
('B55', 'Memahami Metode-Metode Penelitian', 'Andi Prastowo', 'Ar-Ruzz Media', 2016, 2),
('B56', 'Teori-Teori Psikologi', 'M. Nur Gufron', 'Ar-Ruzz Media', 2016, 2),
('B57', 'Pendidikan Karakter', 'Fatchul Mu’in', 'Ar-Ruzz Media', 2016, 2),
('B58', 'Official Statistics', 'Puguh Bodro Irawan', 'IN MEDIA', 2016, 2),
('B59', 'Bahasa Indonesia Untuk Perguruan Tinggi', 'Ahmad Bahtiar', 'IN MEDIA', 2014, 2),
('B6', 'Membuat Aplikasi Sistem Aplikasi Menggunakan VB.NET', 'Yulius Ekaq Agung Saputro', 'MITRA WACANA', 2013, 0),
('B60', 'Pengenalan Praktis Step by Step Perawatan Luka Diabetes dengan Metode Perawatan Modern', 'Anik Maryunani', 'IN MEDIA', 2013, 2),
('B61', 'Manajemen Perbankan', 'Kasmir', 'Rajawali Pers', 2015, 2),
('B62', 'Pengantar Komunikasi Kesehatan Untuk Mahasiswa Institusi Kesehatan', 'Firdaus J. Kunoli', 'IN MEDIA', 2013, 2),
('B63', 'Hukum Perumahan', 'Urip Santoso', 'Prenada Media Grup', 2014, 2),
('B64', 'Akuntansi Jasa dan Dagang', 'Hery', 'Gava Media', 2013, 2),
('B65', 'Demiliterisasi Tentara', 'Abdoel Fattah', 'LIK', 2019, 2),
('B66', 'Virologi Klinik', 'Soedarto', 'Sagung Seto', 2010, 2),
('B67', 'Jahiliyah Komtemporer dan Hegemoni Nalar Kekerasan', 'Abd A’la', 'LIK', 2013, 2),
('B68', 'Filsafat Hukum', 'Fokky Fuad Wasitaatmadja', 'Prenadamedia Grup', 2015, 2),
('B69', 'Matinya Dunia Sciberspace', 'Astar Hadi', 'LIK', 2009, 2),
('B7', 'Penelitian Kualitatif	', 'Burhan Bungin', 'PRENADA MEDIA GRUP', 2007, 1),
('B70', 'Kamus Bidan (Bergambar) Dalam Asuhan Kebidanan', 'Anik Maryunani', 'In Media', 2014, 2),
('B71', 'Buku Ajar Keperawatan Bedah I', 'Awan Hariyanto', 'Ar-Ruzz Media', 2015, 2),
('B72', 'Pendidikan dan Psikologi Perkembangan', 'Baharuddin', 'Ar-Ruzz Media', 2016, 2),
('B73', 'Perancangan Percobaan', 'Oramahi', 'Gava Media', 2009, 2),
('B74', 'Studi Ilmu Pendidikan Islam', 'Moh. Haitami Salim', 'Ar-Ruzz Media', 2016, 2),
('B75', 'Hukum Ekonomi Syariah', 'Abdul Manam', 'Prenadamedia Grup', 2016, 2),
('B76', 'Luka Bakar Pediatrik', 'Yefta Moenadjat', 'Sagung Seto', 2016, 2),
('B77', 'The Silent Disaster, Bencana dan Korban Massal', 'Aryono D. Pusponegoro', 'Sagung Seto', 2011, 2),
('B78', 'Studi Islam Komprehensif', 'Abuddin Nata', 'Prenadamedia Grup', 2011, 2),
('B79', 'Membuat Robot Arduino Bersama Profesor Bolabot Menggunakan Interface Phyton', 'Mada Sanjaya', 'Gava Media', 2016, 2),
('B8', 'Panduan Praktis Sistem Kendali Digital', 'Azwardi Dan Cekmas Cekdin	', 'Andi Offset', 2015, 0),
('B80', 'Teknik Hacking Hardware Komputer', 'Efvy Zamidra Zam', 'Gava Media', 2008, 2),
('B81', 'Dekonstruksi Syari’ah (II)', 'Abdullahi Ahmed', 'LIK', 2009, 2),
('B82', 'Anilisis Isi', 'Eriyanto', 'Prenadamedia Grup', 2011, 2),
('B83', 'Trik Pemrogaman Jaringan dengan Visual Basic 6', 'Vygory Viva', 'Gava Media', 2008, 2),
('B84', 'Antarmuka Port Paralel dan Port Serial dengan Delphi 6', 'Iswanto', 'Gava Media', 2008, 2),
('B85', 'Medan Elektromagnetika', 'Mudrik Alaydrus', 'Andi', 2014, 3),
('B86', 'Ekonometrika Terapan', 'Suliyanto', 'Andi', 2011, 2),
('B87', 'Teori dan Aplikasi Struktur Data Menggunakan Java', 'Abdul Kadir', 'Andi', 2014, 4),
('B88', 'Keperawatan Kritis', 'Cynthia Lee Terry & Aura Weaver', 'Andi', 2011, 4),
('B89', 'Pemberdayaan Wanita Dalam Bidang Kesehatan', 'A. August Burns, dkk.', 'Andi', 2010, 1),
('B9', 'Pengantar Membuat Robot	', 'M. Ibnu Malik', 'Gava Media', 2006, 2),
('B90', 'Farmakologi DeMYSTiFieD', 'Mary Kamienski & James Keogh', 'Andi', 2006, 4),
('B91', 'Service, Quality & Satisfaction', 'Fandy Tjiptono & Gregorius Chandra', 'Andi', 2011, 2),
('B92', 'Keperawatan Medikal Bedah', 'Mary DiGiulio, Donna Jackson, Jim Keogh', 'Andi', 2007, 7),
('B93', 'Hukum PT Go Public dan Pasar Modal', 'Nindyo Pramono', 'Andi', 2013, 7),
('B94', 'Sisi Gelap Pulau Dewata', 'Geoffrey Robinson', 'LKiS', 2005, 2),
('B95', 'Konsep Dasar Perancangan Web', 'Fauziah', 'Mitra Wacana Media', 2014, 9),
('B96', 'Psikologi Sosial', 'Bimo Walgito', 'Andi', 2003, 2),
('B97', 'Teori dan Aplikasi Metode Elemen Hingga', 'Prabuono Buyung Kosasih', 'Andi', 2012, 9),
('B98', 'Rekayasa Perangkat Lunak', 'Andri Kristanto', 'Gava Media', 2004, 2),
('B99', 'Membuat Robot', 'Moh. Ibnu Malik', 'Gava Media', 2006, 8);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(20) NOT NULL,
  `id_buku` varchar(20) NOT NULL,
  `id_anggota` varchar(20) NOT NULL,
  `tanggal_peminjaman` datetime NOT NULL,
  `tanggal_max_pengembalian` datetime NOT NULL,
  `tanggal_pengembalian` datetime DEFAULT NULL,
  `denda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_buku`, `id_anggota`, `tanggal_peminjaman`, `tanggal_max_pengembalian`, `tanggal_pengembalian`, `denda`) VALUES
('T1', 'B11', 'A1', '2023-11-15 22:51:28', '2023-11-15 22:51:28', '2023-11-17 09:57:35', 1000),
('T10', 'B7', 'A2', '2023-11-04 17:33:35', '2023-11-09 17:33:35', '2023-11-17 18:54:58', 8000),
('T11', 'B7', 'A8', '2023-11-08 11:40:51', '2023-11-13 11:40:51', '2023-11-17 10:04:55', 3000),
('T12', 'B8', 'A5', '2023-11-04 16:10:03', '2023-11-09 16:10:03', '2023-11-16 08:42:59', 6000),
('T13', 'B7', 'A4', '2023-11-04 13:56:26', '2023-11-09 13:56:26', '2023-11-06 09:58:32', 0),
('T14', 'B7', 'A8', '2023-11-04 17:33:55', '2023-11-09 17:33:55', '2023-11-06 09:58:32', 0),
('T15', 'B7', 'A6', '2023-11-04 14:00:58', '2023-11-09 14:00:58', '2023-11-06 09:58:32', 0),
('T16', 'B7', 'A6', '2023-11-08 11:46:24', '2023-11-13 11:46:24', '2023-11-14 20:45:54', 1000),
('T17', 'B1', 'A1', '2023-11-14 18:57:13', '2023-11-19 18:57:13', '2023-11-15 22:11:03', 0),
('T18', 'B20', 'A1', '2023-11-14 19:36:14', '2023-11-19 19:36:14', '2023-11-14 22:08:18', 0),
('T19', 'B1', 'A1', '2023-11-14 19:40:33', '2023-11-19 19:40:33', '2023-11-15 22:11:03', 0),
('T2', 'B1', 'A1', '2023-11-15 22:55:34', '2023-11-15 22:55:34', '2023-11-15 23:27:25', 0),
('T20', 'B1', 'A1', '2023-11-14 19:50:14', '2023-11-19 19:50:14', '2023-11-15 22:11:03', 0),
('T21', 'B1', 'A1', '2023-11-14 19:50:26', '2023-11-19 19:50:26', '2023-11-15 22:11:03', 0),
('T22', 'B10', 'A1', '2023-11-14 20:24:19', '2023-11-19 20:24:19', '2023-11-14 20:32:09', 0),
('T23', 'B1', 'A1', '2023-11-15 13:43:00', '2023-11-15 13:43:00', '2023-11-15 22:11:03', 0),
('T24', 'B1', 'A1', '2023-11-15 13:44:24', '2023-11-15 13:44:24', '2023-11-15 22:11:03', 0),
('T25', 'B1', 'A1', '2023-11-15 13:51:33', '2023-11-15 13:51:33', '2023-11-15 22:11:03', 0),
('T26', 'B1', 'A1', '2023-11-15 22:58:11', '2023-11-20 22:58:11', '2023-11-17 00:44:38', 0),
('T27', 'B1', 'A1', '2023-11-15 22:58:36', '2023-11-20 22:58:36', '2023-11-15 23:25:40', 0),
('T28', 'B3', 'A1', '2023-11-16 22:48:52', '2023-11-21 22:48:52', '2023-11-17 00:27:13', 0),
('T29', 'B2', 'A1', '2023-11-16 22:52:39', '2023-11-21 22:52:39', '2023-11-17 00:44:38', 0),
('T3', 'B6', 'A9', '2023-11-02 15:27:48', '2023-11-07 15:27:48', '2023-11-16 23:56:02', 9000),
('T30', 'B2', 'A1', '2023-11-16 22:54:22', '2023-11-21 22:54:22', '2023-11-17 00:44:38', 0),
('T31', 'B2', 'A1', '2023-11-16 22:56:48', '2023-11-21 22:56:48', '2023-11-17 01:05:01', 0),
('T32', 'B2', 'A1', '2023-11-16 22:56:56', '2023-11-21 22:56:56', '2023-11-17 08:46:47', 0),
('T33', 'B2', 'A3', '2023-11-16 22:59:10', '2023-11-21 22:59:10', '2023-11-17 09:02:01', 0),
('T34', 'B4', 'A3', '2023-11-17 00:27:43', '2023-11-22 00:27:43', '2023-11-17 09:04:37', 0),
('T35', 'B3', 'A3', '2023-11-17 08:47:21', '2023-11-22 08:47:21', '2023-11-17 09:03:54', 0),
('T36', 'B6', 'A2', '2023-11-17 10:03:29', '2023-11-22 10:03:29', '2023-11-17 18:55:32', 0),
('T37', 'B8', 'A4', '2023-11-17 18:48:25', '2023-11-22 18:48:25', '2023-11-17 18:52:01', 0),
('T38', 'B9', 'A1', '2023-11-17 18:59:42', '2023-11-22 18:59:42', '2023-11-17 19:01:46', 0),
('T39', 'B12', 'A1', '2023-11-17 19:04:37', '2023-11-22 19:04:37', '2023-11-17 19:04:59', 0),
('T4', 'B7', 'A8', '2023-11-04 17:33:00', '2023-11-09 17:33:00', '2023-11-06 09:58:32', 0),
('T5', 'B6', 'A9', '2023-11-03 21:00:27', '2023-11-08 21:00:27', '2023-11-04 17:35:11', 0),
('T6', 'B88', 'A7', '2023-11-04 07:37:43', '2023-11-09 07:37:43', '2023-11-17 00:47:13', 7000),
('T7', 'B7', 'a5', '2023-11-04 10:38:53', '2023-11-09 10:38:53', '2023-11-06 09:58:32', 0),
('T8', 'B5', 'A9', '2023-11-04 10:55:48', '2023-11-09 10:55:48', '2023-11-06 10:19:29', 0),
('T9', 'B5', 'A9', '2023-11-04 13:51:43', '2023-11-09 13:51:43', '2023-11-06 10:19:29', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id_anggota`),
  ADD UNIQUE KEY `id_anggota` (`id_anggota`);

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`),
  ADD UNIQUE KEY `id_buku` (`id_buku`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD UNIQUE KEY `id_transaksi` (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
