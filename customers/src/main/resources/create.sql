CREATE TABLE IF NOT EXISTS customer (
    id IDENTITY PRIMARY KEY,
    first_name VARCHAR(60),
    last_name VARCHAR(60),
    birth_date DATE
);