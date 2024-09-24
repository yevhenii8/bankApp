CREATE TABLE IF NOT EXISTS accounts (
    id VARCHAR(36) PRIMARY KEY,
    client_id VARCHAR(36),
    username VARCHAR(255),
    password VARCHAR(255),
    status VARCHAR(255),
    `type` VARCHAR(255),
    balance DECIMAL(15,2),
    currency varchar(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS agreements (
    id VARCHAR(36) PRIMARY KEY,
    account_id VARCHAR(36),
    product_id VARCHAR(36),
    manager_id VARCHAR(36),
    interest_rate decimal(15, 2),
    status varchar(255),
    `sum` decimal(15, 2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS products (
    id varchar(36) PRIMARY KEY,
    manager_id varchar(36),
    product_name varchar(255),
    status varchar(255),
    interest_rate decimal(15, 2),
    product_limit INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS managers (
    id varchar(36) PRIMARY KEY,
    ssn varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    status varchar(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS clients (
    id varchar(36) PRIMARY KEY,
    ssn varchar(255),
    manager_id varchar(36),
    status varchar(255),
    tax_code varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    address varchar(255),
    phone varchar(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS transactions (
    id varchar(36) PRIMARY KEY,
    account_id varchar(36),
    transaction_type varchar(255),
    amount decimal(15, 2),
    description varchar(255),
    created_at TIMESTAMP
    );