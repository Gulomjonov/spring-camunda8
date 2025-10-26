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
SELECT 'CLIENT001', 'Alisher', 'Ismoilov', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT001')
UNION ALL
SELECT 'CLIENT002', 'Dilnoza', 'Xolmirzayeva', 'INACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT002')
UNION ALL
SELECT 'CLIENT003', 'Javohir', 'Rahimov', 'PENDING', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT003')
UNION ALL
SELECT 'CLIENT004', 'Sevara', 'Toshpulatova', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT004')
UNION ALL
SELECT 'CLIENT005', 'Farxod', 'Karimov', 'BLOCKED', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT005')
UNION ALL
SELECT 'CLIENT006', 'Madina', 'Yusupova', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT006')
UNION ALL
SELECT 'CLIENT007', 'Bekzod', 'Ismoilov', 'INACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT007')
UNION ALL
SELECT 'CLIENT008', 'Zarina', 'Sobirova', 'PENDING', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT008')
UNION ALL
SELECT 'CLIENT009', 'Shoxrux', 'Hakimov', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT009')
UNION ALL
SELECT 'CLIENT010', 'Gulnora', 'Qodirova', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT010')
UNION ALL
SELECT 'CLIENT011', 'Rustam', 'Tursunov', 'BLOCKED', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT011')
UNION ALL
SELECT 'CLIENT012', 'Dilrabo', 'Omonova', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT012')
UNION ALL
SELECT 'CLIENT013', 'Sherzod', 'Abdurahmonov', 'PENDING', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT013')
UNION ALL
SELECT 'CLIENT014', 'Fotima', 'Rasulova', 'INACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT014')
UNION ALL
SELECT 'CLIENT015', 'Aziz', 'Nazarov', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT015')
UNION ALL
SELECT 'CLIENT016', 'Laylo', 'Hamidova', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT016')
UNION ALL
SELECT 'CLIENT017', 'Jamshid', 'Saidov', 'BLOCKED', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT017')
UNION ALL
SELECT 'CLIENT018', 'Saida', 'Muxtorova', 'PENDING', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT018')
UNION ALL
SELECT 'CLIENT019', 'Bahrom', 'Joʻrayev', 'ACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT019')
UNION ALL
SELECT 'CLIENT020', 'Nilufar', 'Xasanova', 'INACTIVE', 'system', 'system'
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE client_id = 'CLIENT020');