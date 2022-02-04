-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 31, 2022 at 07:48 AM
-- Server version: 5.7.33
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_blessingvisual`
--
CREATE DATABASE IF NOT EXISTS `db_blessingvisual` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db_blessingvisual`;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_barang`
--

CREATE TABLE `tbl_barang` (
  `id_barang` varchar(4) NOT NULL,
  `id_jenis` int(2) NOT NULL,
  `merk` varchar(16) NOT NULL,
  `nm_barang` varchar(32) NOT NULL,
  `harga` float NOT NULL,
  `image` varchar(32) DEFAULT 'default_image.jpg',
  `status` enum('Tersedia','Disewa','Diservis') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_barang`
--

INSERT INTO `tbl_barang` (`id_barang`, `id_jenis`, `merk`, `nm_barang`, `harga`, `image`, `status`) VALUES
('B001', 1, 'Canon', 'EOS R3', 200000, 'EOS R3.png', 'Tersedia'),
('B002', 1, 'Nikon', 'D7000', 500000, 'Nikon D7000.png', 'Tersedia'),
('B003', 1, 'Lumix', 'GH5', 200000, 'Lumix GH5.png', 'Tersedia'),
('B004', 1, 'Canon', '70D', 200000, 'EOS R3.png', 'Tersedia'),
('B005', 1, 'Nikon', 'D700', 200000, 'Nikon D700.png', 'Tersedia'),
('B006', 2, 'Sony', 'A7RIII', 200000, 'Sony A7RIII.png', 'Tersedia'),
('B007', 2, 'Canon', 'EF 32/1.4 STM', 100000, 'default_image.jpg', 'Tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_detail_order`
--

CREATE TABLE `tbl_detail_order` (
  `id_order` varchar(10) NOT NULL,
  `id_barang` varchar(4) NOT NULL,
  `total_harga` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_detail_order`
--

INSERT INTO `tbl_detail_order` (`id_order`, `id_barang`, `total_harga`) VALUES
('O001310122', 'B007', 200000),
('O002310122', 'B007', 200000),
('O003310122', 'B007', 200000),
('O004310122', 'B001', 200000),
('O005310122', 'B001', 200000),
('O006310122', 'B001', 200000),
('O007310122', 'B001', 200000),
('O008310122', 'B001', 200000),
('O009310122', 'B001', 200000),
('O010310122', 'B001', 200000),
('O011310122', 'B001', 200000),
('O012310122', 'B001', 200000),
('O013310122', 'B001', 200000),
('O014310122', 'B001', 200000),
('O015310122', 'B001', 200000),
('O016310122', 'B001', 200000),
('O017310122', 'B001', 200000),
('O018310122', 'B001', 200000),
('O019310122', 'B001', 800000),
('O019310122', 'B003', 800000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jenis`
--

CREATE TABLE `tbl_jenis` (
  `id_jenis` int(2) NOT NULL,
  `jenis` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_jenis`
--

INSERT INTO `tbl_jenis` (`id_jenis`, `jenis`) VALUES
(1, 'Kamera'),
(2, 'Lensa'),
(3, 'Memori'),
(4, 'Baterai'),
(5, 'Tripod'),
(6, 'Lampu'),
(7, 'Lainnya');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_order`
--

CREATE TABLE `tbl_order` (
  `id_order` varchar(10) NOT NULL,
  `id_pelanggan` varchar(5) NOT NULL,
  `tgl_order` datetime NOT NULL,
  `id_pegawai_order` varchar(4) NOT NULL,
  `durasi` int(3) NOT NULL,
  `tgl_kembali` datetime DEFAULT NULL,
  `id_pegawai_kembali` varchar(4) DEFAULT NULL,
  `status` enum('Ongoing','Finished','Canceled') DEFAULT NULL,
  `total_bayar` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_order`
--

INSERT INTO `tbl_order` (`id_order`, `id_pelanggan`, `tgl_order`, `id_pegawai_order`, `durasi`, `tgl_kembali`, `id_pegawai_kembali`, `status`, `total_bayar`) VALUES
('O001310122', 'P0004', '2022-01-31 13:45:58', 'U001', 2, '2022-01-31 13:57:05', 'U002', 'Finished', 200000),
('O002310122', 'P0005', '2022-01-31 13:45:58', 'U002', 2, NULL, NULL, 'Ongoing', 200000),
('O003310122', 'P0006', '2022-01-30 10:45:58', 'U001', 2, '2022-01-31 14:09:41', 'U001', 'Finished', 200000),
('O004310122', 'P0007', '2022-01-30 10:45:58', 'U002', 1, '2022-01-31 14:10:40', 'U001', 'Finished', 260000),
('O005310122', 'P0008', '2022-01-31 13:45:58', 'U000', 1, NULL, NULL, 'Ongoing', 200000),
('O006310122', 'P0009', '2022-01-31 13:45:58', 'U000', 1, NULL, NULL, 'Ongoing', 200000),
('O007310122', 'P0010', '2022-01-31 13:45:58', 'U001', 1, NULL, NULL, 'Ongoing', 200000),
('O008310122', 'P0011', '2022-01-31 13:45:58', 'U002', 1, NULL, NULL, 'Ongoing', 200000),
('O009310122', 'P0003', '2022-01-31 13:45:58', 'U001', 1, '2022-01-31 13:57:00', 'U002', 'Finished', 200000),
('O010310122', 'P0004', '2022-01-31 13:45:58', 'U000', 1, NULL, NULL, 'Ongoing', 200000),
('O011310122', 'P0005', '2022-01-31 13:45:58', 'U001', 1, NULL, NULL, 'Ongoing', 200000),
('O012310122', 'P0006', '2022-01-31 13:45:58', 'U002', 1, NULL, NULL, 'Ongoing', 200000),
('O013310122', 'P0007', '2022-01-30 09:45:58', 'U001', 1, '2022-01-31 13:57:10', 'U002', 'Finished', 200000),
('O014310122', 'P0008', '2022-01-31 13:45:58', 'U000', 1, NULL, NULL, 'Ongoing', 200000),
('O015310122', 'P0009', '2022-01-31 13:45:58', 'U002', 1, NULL, NULL, 'Ongoing', 200000),
('O016310122', 'P0010', '2022-01-31 13:45:58', 'U001', 1, NULL, NULL, 'Ongoing', 200000),
('O017310122', 'P0004', '2022-01-31 13:45:58', 'U001', 1, NULL, NULL, 'Ongoing', 200000),
('O018310122', 'P0004', '2022-01-31 13:45:58', 'U002', 1, NULL, NULL, 'Ongoing', 200000),
('O019310122', 'P0012', '2022-01-31 14:06:26', 'U001', 4, '2022-01-31 14:07:19', 'U001', 'Finished', 1600000);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pegawai`
--

CREATE TABLE `tbl_pegawai` (
  `id_pegawai` varchar(4) NOT NULL,
  `nm_pegawai` varchar(32) NOT NULL,
  `jk_pegawai` enum('Laki-laki','Perempuan') NOT NULL,
  `tlp_pegawai` varchar(15) DEFAULT NULL,
  `email_pegawai` varchar(32) DEFAULT NULL,
  `alm_pegawai` text,
  `role_pegawai` enum('Admin','Kasir','Teknisi') NOT NULL,
  `pin_pegawai` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_pegawai`
--

INSERT INTO `tbl_pegawai` (`id_pegawai`, `nm_pegawai`, `jk_pegawai`, `tlp_pegawai`, `email_pegawai`, `alm_pegawai`, `role_pegawai`, `pin_pegawai`) VALUES
('U000', 'Admin', 'Laki-laki', NULL, NULL, NULL, 'Admin', '230702'),
('U001', 'Muhamad Ardalepa', 'Laki-laki', '081111111111', 'nanangardalepa1@gmail.com', 'Jl. Yang lurus', 'Admin', '123456'),
('U002', 'Nadila Putri', 'Perempuan', '081521544674', 'Nadila123@gmail.com', 'Pontianak', 'Kasir', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_pelanggan`
--

CREATE TABLE `tbl_pelanggan` (
  `id_pelanggan` varchar(5) NOT NULL,
  `nm_pelanggan` varchar(32) NOT NULL,
  `tlp_pelanggan` varchar(15) DEFAULT NULL,
  `alm_pelanggan` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_pelanggan`
--

INSERT INTO `tbl_pelanggan` (`id_pelanggan`, `nm_pelanggan`, `tlp_pelanggan`, `alm_pelanggan`) VALUES
('P0000', 'Default', NULL, NULL),
('P0001', 'Muhamad Ardalepa', '081521544674', 'Sintang'),
('P0002', 'Nadila Putri', '081246778787', 'Pontianak'),
('P0003', 'Kirito', '0899999241', 'Jepang'),
('P0004', 'Asuna', '0812467124', 'JL. Sakit'),
('P0005', 'Ayaka', '08345123', 'Komplek Apa adanya no 8'),
('P0006', 'Diluc', '084523', 'Mesir Kuno sebelah Kiri'),
('P0007', 'Mathilda', '0812434124', 'Local Host'),
('P0008', 'Martis', '0832784352', 'Gg. Fitrah no 98'),
('P0009', 'Tobiichi', '0812476812', 'Gg Hh mahmud'),
('P0010', 'Kaneki', '081263457', 'Osaka'),
('P0011', 'Limbo', '086715823', 'Hokkaido'),
('P0012', 'Pebri', '08192389', '08192389');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `FK_tbl_barang_tbl_jenis` (`id_jenis`);

--
-- Indexes for table `tbl_detail_order`
--
ALTER TABLE `tbl_detail_order`
  ADD KEY `FK_tbl_detail_order_tbl_barang` (`id_barang`),
  ADD KEY `FK_tbl_detail_order_tbl_order` (`id_order`);

--
-- Indexes for table `tbl_jenis`
--
ALTER TABLE `tbl_jenis`
  ADD PRIMARY KEY (`id_jenis`);

--
-- Indexes for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `FK_tbl_order_tbl_pelanggan` (`id_pelanggan`),
  ADD KEY `FK_tbl_order_tbl_pegawai` (`id_pegawai_order`),
  ADD KEY `FK_tbl_order_tbl_pegawai_2` (`id_pegawai_kembali`);

--
-- Indexes for table `tbl_pegawai`
--
ALTER TABLE `tbl_pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `tbl_pelanggan`
--
ALTER TABLE `tbl_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_jenis`
--
ALTER TABLE `tbl_jenis`
  MODIFY `id_jenis` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_barang`
--
ALTER TABLE `tbl_barang`
  ADD CONSTRAINT `FK_tbl_barang_tbl_jenis` FOREIGN KEY (`id_jenis`) REFERENCES `tbl_jenis` (`id_jenis`) ON UPDATE CASCADE;

--
-- Constraints for table `tbl_detail_order`
--
ALTER TABLE `tbl_detail_order`
  ADD CONSTRAINT `FK_tbl_detail_order_tbl_barang` FOREIGN KEY (`id_barang`) REFERENCES `tbl_barang` (`id_barang`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_tbl_detail_order_tbl_order` FOREIGN KEY (`id_order`) REFERENCES `tbl_order` (`id_order`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD CONSTRAINT `FK_tbl_order_tbl_pegawai` FOREIGN KEY (`id_pegawai_order`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_tbl_order_tbl_pegawai_2` FOREIGN KEY (`id_pegawai_kembali`) REFERENCES `tbl_pegawai` (`id_pegawai`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_tbl_order_tbl_pelanggan` FOREIGN KEY (`id_pelanggan`) REFERENCES `tbl_pelanggan` (`id_pelanggan`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
