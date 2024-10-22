Drop table if exists product;

Create table Product(
id int(20) AUTO_INCREMENT PRIMARY_KEY,
name varchar(50) not null,
price int(10) not null
)

insert into product values(1,"SmartPhone",20000);
insert into product values(1,"Washing Machine",10000);
insert into product values(1,"Dryer",20000);
