SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `language_center`
-- 

-- --------------------------------------------------------
CREATE DATABASE `language_center`;

USE `language_center`;

CREATE TABLE `Admin`
(
  `UserID` INT AUTO_INCREMENT,
  `Username` VARCHAR(30) NOT NULl,
  `PWD` VARCHAR(30) NOT NULL,
  UNIQUE(`Username`),
  PRIMARY KEY (`UserID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Student`
(
  `StudentID` INT AUTO_INCREMENT,
  `Username` VARCHAR(30) NOT NULL,
  `PWD` VARCHAR(30) NOT NULL,
  `Gmail` VARCHAR(30),
  `OTP` INT DEFAULT NULL,
  UNIQUE(`Username`, `Gmail`),
  PRIMARY KEY (`StudentID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Lang_Center`
(
  `LangID` INT AUTO_INCREMENT,
  `Username` VARCHAR(30) NOT NULL,
  `PWD` VARCHAR(30) NOT NULL,
  `Gmail` VARCHAR(30),
  `OTP` INT DEFAULT NULL,
  UNIQUE(`Username`, `Gmail`),
  PRIMARY KEY (`LangID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `DayStudy`
(
  `DayID` INT AUTO_INCREMENT,
  `Day` VARCHAR(10) NOT NULL,
  `Time` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`DayID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Certificate`
(
  `CertID` INT AUTO_INCREMENT,
  `Name` NVARCHAR(30) NOT NULL,
  PRIMARY KEY(`CertID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Post`
(
  `PostID` INT AUTO_INCREMENT,
  `Name` NVARCHAR(50) NOT NULL,
  `Image` VARCHAR(1000) NOT NULL,
  `Price` FLOAT NOT NULL,
  `Descr` NVARCHAR(200) NOT NULL,
  `Qty` INT NOT NULL,
  `Phone` VARCHAR(10) NOT NULL,
  `Address` NVARCHAR(100) NOT NULL,
  `Fb` VARCHAR(100) NOT NULL,
  `TimeUpload` VARCHAR(20) NOT NULL,
  `LangID` INT NOT NULL,
  `DayID` INT NOT NULL,
  `CertID` INT NOT NULL,
  `Access` TINYINT DEFAULT 0,
  PRIMARY KEY (`PostID`),
  FOREIGN KEY (`LangID`) REFERENCES `Lang_Center`(`LangID`),
  FOREIGN KEY (`DayID`) REFERENCES `DayStudy`(`DayID`),
  FOREIGN KEY (`CertID`) REFERENCES `Certificate`(`CertID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Post_Day`
(
  `DayID` INT NOT NULL,
  `PostID` INT NOT NULL,
  PRIMARY KEY (`DayID`, `PostID`),
  FOREIGN KEY (`DayID`) REFERENCES `DayStudy`(`DayID`),
  FOREIGN KEY (`PostID`) REFERENCES `Post`(`PostID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `Bookmark`
(
  `StudentID` INT NOT NULL,
  `PostID` INT NOT NULL,
  PRIMARY KEY (`StudentID`, `PostID`),
  FOREIGN KEY (`StudentID`) REFERENCES `Student`(`StudentID`),
  FOREIGN KEY (`PostID`) REFERENCES `Post`(`PostID`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `Admin` (`Username`, `PWD`) VALUES
('admin', '123456');

INSERT INTO `Certificate` (`Name`) VALUES
('PET'),
('TOEIC'),
('IELTS'),
('TOEFL'),
('ESOL'),
('CEFR');

INSERT INTO `DayStudy` (`Day`, `Time`) VALUES
('2-4-6', '7:00-9:00'),
('2-4-6', '9:00-11:00'),
('2-4-6', '13:00-15:00'),
('2-4-6', '17:00-19:00'),
('2-4-6', '19:00-21:00'),
('3-5-7', '7:00-9:00'),
('3-5-7', '9:00-11:00'),
('3-5-7', '13:00-15:00'),
('3-5-7', '17:00-19:00'),
('3-5-7', '19:00-21:00');

INSERT INTO `Student` (`StudentID`, `Username`, `PWD`, `Gmail`, `OTP`) VALUES
(NULL, 'BaoBao', '123123123', 'bb@gmail.com', NULL),
(NULL, 'VanHung', '234234234', 'vh@gmail.com', NULL),
(NULL, 'VanHieu', '234234234', 'vhieu@gmail.com', NULL),
(NULL, 'ThiDao', '245245245', 'td@gmail.com', NULL);

INSERT INTO `Lang_Center` (`LangID`, `Username`, `PWD`, `Gmail`, `OTP`) VALUES
(NULL, 'NgoiSao', '123123123', 'NS@gmail.com', NULL),
(NULL, 'England', '234234234', 'EL@gmail.com', NULL),
(NULL, 'Star', '2342567234', 'S@gmail.com', NULL),
(NULL, 'E-Center', '249056745', 'EC@gmail.com', NULL);

INSERT INTO `Post` (`PostID`, `Image`, `Price`, `Descr`, `Qty`, `Phone`, `Address`, `Fb`, `TimeUpload`, `LangID`, `DayID`, `CertID`, `Access`) VALUES
(NULL, '../images/img.png', '1231123', 'NS@gmail.com',12,'23461324','q7','facebook.com','12h',1,4,5,NULL),
(NULL, '../images/img1.jpg', '2342344', 'EL@gmail.com',13,'4563234','q4','facebook.com','13h',2,1,4,NULL),
(NULL, '../images/img2.png', '2567234', 'S@gmail.com',14,'234523','q5','facebook.com','12h',3,5,1,NULL),
(NULL, '../images/img3.jpg', '2056745', 'EC@gmail.com',12,'2135134','q7','facebook.com','14h',4,2,3,NULL);