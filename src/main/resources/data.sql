MERGE INTO users (email, password, name, role)
    KEY(email)
    VALUES (
    'guest@example.com',
    '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
    'ゲスト',
    'ROLE_USER'
    );

MERGE INTO users (email, password, name, role)
    KEY(email)
    VALUES (
    'admin@example.com',
    '$2a$10$FBSd704t1KhZOAPm5lBi/.1AA6eeKoqZLwTBGGM.fbHEWXGzUu5N2',
    '管理者',
    'ROLE_ADMIN'
    );

MERGE INTO products (id, name, description, price, stock)
    KEY(id)
    VALUES
    (1, '商品A', 'サンプル商品Aの説明です', 1000, 10),
    (2, '商品B', 'サンプル商品Bの説明です', 2000, 5),
    (3, '商品C', 'サンプル商品Cの説明です', 3000, 3);