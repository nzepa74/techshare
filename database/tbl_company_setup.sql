
DROP TABLE IF EXISTS `tbl_company_setup`;

CREATE TABLE `tbl_company_setup` (
  `companyId` INT(11) NOT NULL,
  `companyName` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `status` CHAR(1) NOT NULL,
  `createdBy` VARCHAR(45) NOT NULL,
  `createdDate` DATETIME NOT NULL,
  PRIMARY KEY (`companyId`)
)  
 
