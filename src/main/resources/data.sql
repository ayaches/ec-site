-- ゲストアカウント（パスワード：guest1234をBCryptで暗号化）
INSERT INTO users (email, password, name, role)
VALUES (
           'guest@example.com',
           '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
           'ゲスト',
           'ROLE_USER'
       );

-- サンプル商品データ
INSERT INTO products (name, description, price, stock, image_url)
VALUES
    ('商品A', 'サンプル商品Aの説明です', 1000, 10, '/images/product-a.jpg'),
    ('商品B', 'サンプル商品Bの説明です', 2000, 5, '/images/product-b.jpg'),
    ('商品C', 'サンプル商品Cの説明です', 3000, 3, '/images/product-c.jpg');

-- 管理者アカウント
INSERT INTO users (email, password, name, role)
VALUES (
           'admin@example.com',
           '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi',
           '管理者',
           'ROLE_ADMIN'
       );