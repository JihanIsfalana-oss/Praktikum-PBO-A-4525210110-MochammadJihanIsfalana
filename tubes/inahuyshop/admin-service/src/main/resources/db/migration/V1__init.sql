CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_type VARCHAR(31) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE vouchers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    type VARCHAR(20) NOT NULL,
    discount_value DECIMAL(12,2) NOT NULL,
    valid_until DATETIME(6) NOT NULL,
    quota INT NOT NULL,
    used_count INT NOT NULL DEFAULT 0
);

CREATE TABLE membership_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    duration_days INT NOT NULL,
    discount_percent INT NOT NULL
);