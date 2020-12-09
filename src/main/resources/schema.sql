CREATE TABLE CAR(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(40) NOT NULL,
    model VARCHAR(40) NOT NULL,
    loan_identifier BIGINT NULL
);

CREATE TABLE CLIENT(
    client_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    last_name VARCHAR(100),
    rented_car BIGINT
);

ALTER TABLE CLIENT
    ADD CONSTRAINT clientId_loanIdentifier
    FOREIGN KEY (rented_car) REFERENCES car(loan_identifier)