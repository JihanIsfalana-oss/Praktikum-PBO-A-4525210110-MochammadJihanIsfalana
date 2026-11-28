INSERT INTO products (product_type, name, price, category, status) VALUES
 ('EBOOK', 'Panduan Dasar OOP Java', 75000.00, 'Pemrograman', 'ACTIVE'),
 ('EBOOK', 'Ringkasan Arsitektur MVC', 60000.00, 'Pemrograman', 'ACTIVE'),
 ('EBOOK', 'Microservices with Java', 120000.00, 'Pemrograman', 'ACTIVE'),
 ('EBOOK', 'Mahir Pemrograman PHP', 43500.00, 'Pemrograman', 'ACTIVE'),
 ('TEMPLATE', 'Template Laporan Keuangan', 45000.00, 'Bisnis', 'ACTIVE'),
 ('TEMPLATE', 'Template Seminar Proposal', 55000.00, 'Bisnis', 'ACTIVE'),
 ('TEMPLATE', 'Template Proposal Usaha', 55000.00, 'Bisnis', 'INACTIVE'),
 ('DESIGN_ASSET', 'Paket Ikon Minimalis', 120000.00, 'Desain', 'ACTIVE'),
 ('DESIGN_ASSET', 'Paket Mockup Kemasan', 150000.00, 'Desain', 'ACTIVE');

INSERT INTO vouchers (code, type, discount_value, valid_until, quota, used_count) VALUES
 ('DISKON10', 'PERCENT', 10.00, '2030-12-31 23:59:59', 100, 0),
 ('KEDALUWARSA', 'FIXED', 5000.00, '2025-12-31 23:59:59', 10, 0);

INSERT INTO membership_plans (name, price, duration_days, discount_percent) VALUES
 ('Membership Bulanan', 50000.00, 30, 15),
 ('Membership Tahunan', 255000.00, 365, 20);