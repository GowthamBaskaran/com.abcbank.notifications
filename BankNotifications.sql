select * from sys.all_tables;

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

-- Inserting customers
INSERT INTO customers (customer_id, customer_name, created_at, updated_at) VALUES
(1001, 'John Doe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1002, 'Jane Smith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1003, 'Alice Johnson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1004, 'Bob Williams', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1005, 'Emily Brown', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1006, 'Michael Davis', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1007, 'Sarah Martinez', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1008, 'David Anderson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1009, 'Laura Garcia', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1010, 'William Rodriguez', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Inserting documents
INSERT INTO documents (customer_id, document_name, expiry_time, created_at, updated_at) VALUES
-- John Doe's documents
(1001, 'Passport', '2024-12-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1001, 'Driver''s License', '2024-10-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Jane Smith's documents
(1002, 'Passport', '2025-06-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1002, 'Social Security Card', '2024-09-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Alice Johnson's documents
(1003, 'Passport', '2025-03-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1003, 'Birth Certificate', '2024-01-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Bob Williams's documents
(1004, 'Passport', '2024-08-10', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1004, 'Work Permit', '2025-05-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Emily Brown's documents
(1005, 'Passport', '2025-04-25', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1005, 'Health Insurance Card', '2024-11-12', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Michael Davis's documents
(1006, 'Passport', '2024-09-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1006, 'Voter ID Card', '2025-02-28', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Sarah Martinez's documents
(1007, 'Passport', '2025-10-20', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1007, 'Student ID Card', '2024-06-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- David Anderson's documents
(1008, 'Passport', '2024-07-22', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1008, 'Bank Statement', '2025-01-31', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Laura Garcia's documents
(1009, 'Passport', '2024-11-30', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1009, 'Credit Card', '2025-08-15', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- William Rodriguez's documents
(1010, 'Passport', '2025-02-18', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1010, 'Utility Bill', '2024-12-05', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

select * from customers;

select * from documents;
