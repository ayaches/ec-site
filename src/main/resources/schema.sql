-- ===== users =====
CREATE TABLE IF NOT EXISTS users (
                       id         SERIAL PRIMARY KEY,
                       email      VARCHAR(255) NOT NULL UNIQUE,
                       password   VARCHAR(255) NOT NULL,
                       name       VARCHAR(100) NOT NULL,
                       role       VARCHAR(20)  NOT NULL DEFAULT 'ROLE_USER',
                       created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ===== products =====
CREATE TABLE IF NOT EXISTS products (
                          id          SERIAL PRIMARY KEY,
                          name        VARCHAR(255) NOT NULL,
                          description TEXT,
                          price       INTEGER      NOT NULL,
                          stock       INTEGER      NOT NULL DEFAULT 0,
                          image_url   VARCHAR(500),
                          created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ===== carts =====
CREATE TABLE IF NOT EXISTS carts (
                       id      SERIAL  PRIMARY KEY,
                       user_id INTEGER NOT NULL UNIQUE REFERENCES users(id)
);

-- ===== cart_items =====
CREATE TABLE IF NOT EXISTS cart_items (
                            id         SERIAL  PRIMARY KEY,
                            cart_id    INTEGER NOT NULL REFERENCES carts(id),
                            product_id INTEGER NOT NULL REFERENCES products(id),
                            quantity   INTEGER NOT NULL DEFAULT 1
);

-- ===== orders =====
CREATE TABLE IF NOT EXISTS orders (
                        id          SERIAL      PRIMARY KEY,
                        user_id     INTEGER     NOT NULL REFERENCES users(id),
                        total_price INTEGER     NOT NULL,
                        status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                        created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ===== order_items =====
CREATE TABLE IF NOT EXISTS order_items (
                             id         SERIAL  PRIMARY KEY,
                             order_id   INTEGER NOT NULL REFERENCES orders(id),
                             product_id INTEGER NOT NULL REFERENCES products(id),
                             quantity   INTEGER NOT NULL,
                             price      INTEGER NOT NULL
);