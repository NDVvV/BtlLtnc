-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 13, 2022 at 03:53 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_ms`
--

-- --------------------------------------------------------

--
-- Table structure for table `book_details`
--

CREATE TABLE `book_details` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(250) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `publication_date` date DEFAULT NULL,
  `money_of_book` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book_details`
--

INSERT INTO `book_details` (`book_id`, `book_name`, `author`, `quantity`, `publisher`, `publication_date`, `money_of_book`) VALUES
(1, 'JAVA', 'Author JAVA', 13, 'java', '2015-08-11', 1),
(2, 'PYTHON', 'Author PYTHON', 8, 'python', '2018-12-13', 2),
(3, 'PHP', 'Author PHP', 19, 'php', '2015-12-16', 3),
(4, 'NodeJs', 'Author NodeJs', 15, 'nodejs', '2011-11-09', 4),
(5, 'JS', 'Author JS', 6, 'js', '2008-08-12', 5),
(6, 'C', 'Author C', 30, 'c', '2016-04-13', 6),
(7, 'C++', 'Author C++', 10, 'c++', '2005-05-10', 7),
(8, 'Ruby', 'Author Ruby', 15, 'ruby', '2010-03-03', 8),
(9, 'ReactJs', 'Author ReactJs', 20, 'reactjs', '2001-07-04', 9),
(13, 'C#', 'Arthor C#', 16, 'c#', '2022-08-15', 13),
(14, 'GoLang', 'Author GoLang', 16, 'golang', '2015-08-26', 20),
(15, 'HTML', 'Author HTML', 43, 'html', '2013-08-22', 15);

-- --------------------------------------------------------

--
-- Table structure for table `issue_book_details`
--

CREATE TABLE `issue_book_details` (
  `issue_details_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(100) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `student_name` varchar(100) DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `issue_book_details`
--

INSERT INTO `issue_book_details` (`issue_details_id`, `user_id`, `book_id`, `book_name`, `student_id`, `student_name`, `issue_date`, `due_date`, `status`) VALUES
(1, 1, 1, 'JAVA', 1, 'Viet', '2022-08-08', '2022-08-10', 'returned'),
(2, 1, 1, 'JAVA', 2, 'Linh', '2022-09-06', '2022-08-13', 'returned'),
(3, 1, 1, 'JAVA', 3, 'Son', '2022-09-08', '2022-08-10', 'pending'),
(4, 1, 3, 'PHP', 4, 'Hiep', '2022-08-01', '2022-09-15', 'pending'),
(5, 1, 5, 'JS', 1, 'Viet', '2022-07-11', '2022-08-09', 'returned'),
(6, 1, 14, 'GoLang', 1, 'Viet', '2022-08-01', '2022-08-20', 'pending'),
(7, 1, 8, 'Ruby', 2, 'Linh', '2022-08-11', '2022-08-13', 'returned'),
(8, 1, 4, 'NodeJs', 2, 'Linh', '2022-08-01', '2022-08-09', 'pending'),
(9, 1, 8, 'Ruby', 2, 'Linh', '2022-08-11', '2022-08-17', 'returned'),
(10, 1, 1, 'JAVA', 2, 'Linh', '2022-08-11', '2022-08-17', 'returned');

-- --------------------------------------------------------

--
-- Table structure for table `student_details`
--

CREATE TABLE `student_details` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `class` varchar(100) DEFAULT NULL,
  `course` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_details`
--

INSERT INTO `student_details` (`student_id`, `name`, `class`, `course`) VALUES
(1, 'Viet', 'DT11', 'K63'),
(2, 'Linh', 'DT05', 'K63'),
(3, 'Son', 'DT08', 'K63'),
(4, 'Hiep', 'DT03', 'K63');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `password`, `email`, `contact`) VALUES
(1, 'vietdeptrai', '123456', 'viet@gmail.com', '123465789'),
(3, 'Linh', '123456', 'linh@gmail.com', '147896325');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book_details`
--
ALTER TABLE `book_details`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `issue_book_details`
--
ALTER TABLE `issue_book_details`
  ADD PRIMARY KEY (`issue_details_id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `student_details`
--
ALTER TABLE `student_details`
  ADD PRIMARY KEY (`student_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book_details`
--
ALTER TABLE `book_details`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `issue_book_details`
--
ALTER TABLE `issue_book_details`
  MODIFY `issue_details_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `student_details`
--
ALTER TABLE `student_details`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `issue_book_details`
--
ALTER TABLE `issue_book_details`
  ADD CONSTRAINT `issue_book_details_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book_details` (`book_id`),
  ADD CONSTRAINT `issue_book_details_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student_details` (`student_id`),
  ADD CONSTRAINT `issue_book_details_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
