DROP TABLE IF EXISTS product;
CREATE TABLE product(
                      id uuid DEFAULT random_uuid() PRIMARY KEY,
                      name VARCHAR(60) not null unique,
                      description VARCHAR(200) NOT NULL,
                      price DECIMAL(10,2) NOT NULL,
                      image VARCHAR(200) NOT NULL,
                      count_in_stock INTEGER NOT NULL,
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP
);

insert into product(name, description, price, image, count_in_stock, created_at, updated_at)
values ('Iphone 12', 'Apple Iphone 12', 1000.00, 'https://www.google.com', 10, now(), now());
insert into product(name, description, price, image, count_in_stock, created_at, updated_at)
values ('Iphone 10', 'Apple Iphone 10', 1000.00, 'https://www.google.com', 100, now(), now());
