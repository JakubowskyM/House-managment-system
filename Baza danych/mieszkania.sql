-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 27, 2024 at 02:57 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mieszkania`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `flats`
--

CREATE TABLE `flats` (
  `Id_f` int(11) NOT NULL,
  `Id_resident` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `flats`
--

INSERT INTO `flats` (`Id_f`, `Id_resident`) VALUES
(1, 1),
(1, 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `keepers`
--

CREATE TABLE `keepers` (
  `Id` int(11) NOT NULL,
  `Name` varchar(11) NOT NULL,
  `Surname` varchar(20) NOT NULL,
  `completed_tasks` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `keepers`
--

INSERT INTO `keepers` (`Id`, `Name`, `Surname`, `completed_tasks`) VALUES
(3, 'Milosz', 'Jakubowski', 4),
(5, 'Jan', 'Konserwatorski', 0),
(6, 'Adam', 'Jaskowski', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `messages`
--

CREATE TABLE `messages` (
  `Id` int(11) NOT NULL,
  `Title` varchar(20) NOT NULL,
  `Text` text NOT NULL,
  `Data` date NOT NULL,
  `Id_a_to` int(11) NOT NULL,
  `Id_a_from` int(11) NOT NULL,
  `Id_f` int(11) NOT NULL,
  `Stan` int(11) NOT NULL,
  `Date_take` date DEFAULT NULL,
  `keeper_id` int(11) DEFAULT NULL,
  `Date_end` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`Id`, `Title`, `Text`, `Data`, `Id_a_to`, `Id_a_from`, `Id_f`, `Stan`, `Date_take`, `keeper_id`, `Date_end`) VALUES
(1, 'klima', 'klima nie dziala, wez pomoz\nMieszkanie nr:  1', '2024-06-27', 2, 1, 1, 3, '2024-06-27', 3, '2024-06-27'),
(2, 'Kuchnia', 'Zlew zatkany\nMieszkanie nr:  1', '2024-06-27', 2, 1, 1, 3, '2024-06-27', 3, '2024-06-27'),
(3, 'Klima', 'klima w kuchni nie dziala\nMieszkanie nr:  1', '2024-06-27', 2, 1, 1, 0, NULL, NULL, NULL),
(4, 'Salon', 'Przecieka sufit\nMieszkanie nr:  1', '2024-06-27', 2, 1, 1, 3, '2024-06-27', 3, '2024-06-27');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `residents`
--

CREATE TABLE `residents` (
  `Id` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Surname` varchar(20) NOT NULL,
  `Id_f` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `residents`
--

INSERT INTO `residents` (`Id`, `Name`, `Surname`, `Id_f`) VALUES
(1, 'Milosz', 'Milosz', 1),
(4, 'Bartek', 'Bartek', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `Id` int(4) NOT NULL,
  `Login` varchar(4) NOT NULL,
  `Password` varchar(4) NOT NULL,
  `Id_f` int(11) DEFAULT NULL,
  `Access_lvl` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Id`, `Login`, `Password`, `Id_f`, `Access_lvl`) VALUES
(1, '0001', '1', 1, 1),
(2, '0002', '3333', 0, 3),
(3, '0003', '1234', NULL, 2),
(4, '0004', '3321', 1, 1),
(5, '0005', '1234', NULL, 2),
(6, '0006', '4532', NULL, 2);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `keepers`
--
ALTER TABLE `keepers`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `residents`
--
ALTER TABLE `residents`
  ADD PRIMARY KEY (`Id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `residents`
--
ALTER TABLE `residents`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `Id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
