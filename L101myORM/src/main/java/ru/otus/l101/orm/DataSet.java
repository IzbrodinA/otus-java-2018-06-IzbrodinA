package ru.otus.l101.orm;
/*

CREATE USER 'izbro'@'localhost' IDENTIFIED BY 'Izbrodin123/*';
GRANT ALL PRIVILEGES ON * . * TO 'izbro'@'localhost';
create database db_homeL101;
SET GLOBAL time_zone = '+3:00';
use db_homeL101;
CREATE TABLE homeworkL10 (id bigint(20) NOT NUll auto_increment,
 name VARCHAR(255), age int(3), PRIMARY KEY (id));

*/
public abstract class DataSet {
    long id;

    public long getId() {
        return id;
    }
}
