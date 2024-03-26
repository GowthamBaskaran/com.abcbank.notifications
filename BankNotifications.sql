select * from sys.all_tables;

create database bank_notifications;

use bank_notifications;

create user 'admin'@'localhost' identified by 'admin';

grant all privileges on bank_notifications.* to 'admin'@'localhost';

flush privileges;

show databases;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE documents (
    document_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    document_name VARCHAR(100) NOT NULL,
    expiry_time DATE NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE notification_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Insert data for customers along with documents
INSERT INTO customers (customer_id, customer_name) VALUES
(1, 'Customer A'),
(2, 'Customer B'),
(3, 'Customer C'),
(4, 'Customer D'),
(5, 'Customer E'),
(6, 'Customer F'),
(7, 'Customer G'),
(8, 'Customer H'),
(9, 'Customer I'),
(10, 'Customer J');

-- Insert data for documents with relevant customer_id
-- Insert data for T, T+30, T+60, T+90
INSERT INTO documents (document_id, customer_id, document_name, expiry_time) VALUES
(1000, 1, 'Document 0', DATE_ADD(CURDATE(), INTERVAL 0 DAY)),
(1001, 2, 'Document 1', DATE_ADD(CURDATE(), INTERVAL 30 DAY)),
(1002, 3, 'Document 2', DATE_ADD(CURDATE(), INTERVAL 60 DAY)),
(1003, 4, 'Document 3', DATE_ADD(CURDATE(), INTERVAL 90 DAY)),
-- Insert data for T-1
(1004, 5, 'Document 4', DATE_SUB(CURDATE(), INTERVAL 1 DAY)),
-- Insert data for T-30, T-60, T-90
(1005, 6, 'Document 5', DATE_SUB(CURDATE(), INTERVAL 30 DAY)),
(1006, 7, 'Document 6', DATE_SUB(CURDATE(), INTERVAL 60 DAY)),
(1007, 8, 'Document 7', DATE_SUB(CURDATE(), INTERVAL 90 DAY)),
-- Insert Multiple documents with different expiry date for single customer
(1008, 9, 'Document 8', DATE_SUB(CURDATE(), INTERVAL 90 DAY)),
(1009, 9, 'Document 9', DATE_SUB(CURDATE(), INTERVAL 30 DAY)),
(1010, 9, 'Document 10', DATE_SUB(CURDATE(), INTERVAL 0 DAY)),
(1011, 10, 'Document 11', DATE_SUB(CURDATE(), INTERVAL 0 DAY)),
(1012, 10, 'Document 12', DATE_SUB(CURDATE(), INTERVAL 0 DAY));

CREATE TABLE notification_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    notification_type VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
