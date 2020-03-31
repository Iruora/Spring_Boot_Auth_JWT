-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 31, 2020 at 06:06 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `usersdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` bigint(20) NOT NULL,
  `avatarurl` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gitlab_private_token` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`id`, `avatarurl`, `email`, `gitlab_private_token`, `password`, `username`) VALUES
(1, NULL, NULL, NULL, '$2a$10$r9K.k4OFHAciwYlTRPQ.RebqtvpJZB3SSfEsymbHDgv7VJRUYQiWe', 'test'),
(2, NULL, NULL, NULL, '$2a$10$FDgPxFGLYpccxAohpdytpOHf9tOZ4BDzLNpH4QuDwUNnctWw296Cq', 'test1'),
(3, NULL, NULL, NULL, '$2a$10$lNldvNSfHasiQZbqN.MdbeGAOOw.9IkxxlzBWbd/Otmmg8jIxCWdK', 'test2'),
(4, NULL, NULL, NULL, '$2a$10$0zglN..vt5J6KgcmONxFqeGJJ.zbXofggT99ZxVG1TyjVKDNhFyAK', 'test3'),
(5, NULL, NULL, NULL, '$2a$10$fxRUnW0ySwpueOFCKMuh2OyFnHq0O3bCeOMnp1K53.duO/raHMhAu', 'test4');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
