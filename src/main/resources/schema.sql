CREATE TABLE PRICES (
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,   -- Clave primaria auto-generada
    BRAND_ID BIGINT NOT NULL,               -- Identificador de la marca (por ejemplo, 1 = ZARA)
    START_DATE TIMESTAMP NOT NULL,          -- Fecha y hora de inicio del rango en el que aplica el precio
    END_DATE TIMESTAMP NOT NULL,            -- Fecha y hora de fin del rango en el que aplica el precio
    PRICE_LIST BIGINT NOT NULL,             -- Identificador de la tarifa de precios aplicable
    PRODUCT_ID BIGINT NOT NULL,             -- Identificador del producto
    PRIORITY INT NOT NULL,                  -- Prioridad para la aplicación del precio (mayor valor = mayor prioridad)
    PRICE DECIMAL(10, 2) NOT NULL,          -- Precio final de venta
    CURRENCY VARCHAR(3) NOT NULL            -- ISO de la moneda (por ejemplo, 'EUR' para euros)
);


INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURRENCY) VALUES
    (1, '2023-08-14 00:00:00', '2023-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
    (1, '2023-08-14 10:00:00', '2023-08-14 14:00:00', 2, 35455, 1, 25.45, 'EUR'),
    (1, '2023-08-14 16:00:00', '2023-08-14 19:00:00', 3, 35455, 1, 30.50, 'EUR'),
    (1, '2023-08-15 00:00:00', '2023-08-15 23:59:59', 4, 35455, 1, 38.95, 'EUR');

commit ;
