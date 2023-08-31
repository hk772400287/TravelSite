CREATE TABLE t_user(
   id INT(6) PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(60),
   PASSWORD VARCHAR(60),
   email VARCHAR(60)
)

CREATE TABLE t_province(
   id INT(6) PRIMARY KEY AUTO_INCREMENT,
   provName VARCHAR(60),
   tags VARCHAR(60),
   spotsCount INT(4)
);

CREATE TABLE t_spot(
    id INT(6) PRIMARY KEY AUTO_INCREMENT,
    spotName VARCHAR(60),
    picpath VARCHAR(100),
    peakSeason TIMESTAMP,
    peakTicket DOUBLE(7,2),
    lowTicket DOUBLE(7,2),
    spotDesc VARCHAR(300),
    provinceId INT(6) REFERENCES t_province(id)
  )