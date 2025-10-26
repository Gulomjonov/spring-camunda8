CREATE TABLE If NOT EXISTS customers
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    client_id          VARCHAR(255) NOT NULL,
    first_name         VARCHAR(100),
    last_name          VARCHAR(100),
    status             VARCHAR(50),
    created_by         VARCHAR(255),
    created_date       TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    last_modified_by   VARCHAR(255),
    last_modified_date TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

-- Create index for better performance
CREATE INDEX IF NOT EXISTS idx_customers_client_id ON customers (client_id);
CREATE INDEX IF NOT EXISTS idx_customers_status ON customers (status);

-- Insert 20 sample customers with Uzbek names and different statuses
INSERT INTO customers (client_id, first_name, last_name, status, created_by, last_modified_by)
VALUES ('CLIENT001', 'Alisher', 'Ismoilov', 'ACTIVE', 'system', 'system'),
       ('CLIENT002', 'Dilnoza', 'Xolmirzayeva', 'INACTIVE', 'system', 'system'),
       ('CLIENT003', 'Javohir', 'Rahimov', 'PENDING', 'system', 'system'),
       ('CLIENT004', 'Sevara', 'Toshpulatova', 'ACTIVE', 'system', 'system'),
       ('CLIENT005', 'Farxod', 'Karimov', 'BLOCKED', 'system', 'system'),
       ('CLIENT006', 'Madina', 'Yusupova', 'ACTIVE', 'system', 'system'),
       ('CLIENT007', 'Bekzod', 'Ismoilov', 'INACTIVE', 'system', 'system'),
       ('CLIENT008', 'Zarina', 'Sobirova', 'PENDING', 'system', 'system'),
       ('CLIENT009', 'Shoxrux', 'Hakimov', 'ACTIVE', 'system', 'system'),
       ('CLIENT010', 'Gulnora', 'Qodirova', 'ACTIVE', 'system', 'system'),
       ('CLIENT011', 'Rustam', 'Tursunov', 'BLOCKED', 'system', 'system'),
       ('CLIENT012', 'Dilrabo', 'Omonova', 'ACTIVE', 'system', 'system'),
       ('CLIENT013', 'Sherzod', 'Abdurahmonov', 'PENDING', 'system', 'system'),
       ('CLIENT014', 'Fotima', 'Rasulova', 'INACTIVE', 'system', 'system'),
       ('CLIENT015', 'Aziz', 'Nazarov', 'ACTIVE', 'system', 'system'),
       ('CLIENT016', 'Laylo', 'Hamidova', 'ACTIVE', 'system', 'system'),
       ('CLIENT017', 'Jamshid', 'Saidov', 'BLOCKED', 'system', 'system'),
       ('CLIENT018', 'Saida', 'Muxtorova', 'PENDING', 'system', 'system'),
       ('CLIENT019', 'Bahrom', 'Joʻrayev', 'ACTIVE', 'system', 'system'),
       ('CLIENT020', 'Nilufar', 'Xasanova', 'INACTIVE', 'system', 'system');