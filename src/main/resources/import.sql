/* Clientes */

INSERT INTO clientes (nombre, apellido, telefono) VALUES('Sofi', 'Lara', 4230789);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Sabri', 'Dohmen', 4455690);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Ovo', 'Grafsd', 4444444);


/* Precios */

INSERT INTO precios (precio_compra, precio_venta) VALUES(20500.5, 30500.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(10250.3, 15000.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(30500.0, 31500.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(55000.0, 75000.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(38, 120);
INSERT INTO precios (precio_compra, precio_venta) VALUES(17000, 18000);
INSERT INTO precios (precio_compra, precio_venta) VALUES(2500, 4500);


/* Stock */

INSERT INTO stock (cantidad) VALUES(20);
INSERT INTO stock (cantidad) VALUES(12);
INSERT INTO stock (cantidad) VALUES(5);
INSERT INTO stock (cantidad) VALUES(2);
INSERT INTO stock (cantidad) VALUES(10);
INSERT INTO stock (cantidad) VALUES(1);
INSERT INTO stock (cantidad) VALUES(40);


/* Productos */

INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Panasonic Pantalla LCD', 1, 1);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Sony Camara digital DSC-W320B', 2, 2);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Apple iPod shuffle', 3, 3);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Sony Notebook Z110', 4, 4);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Hewlett Packard Multifuncional F2280', 5, 5);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Bianchi Bicicleta Aro 26', 6, 6);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('Mica Comoda 5 Cajones', 7, 7);


/* Facturas de venta */

INSERT INTO facturas_venta (cliente_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 5);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas_venta (cliente_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(3, 2, 6);


/* Proveedores */

INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Graciela', 'Alfano', 4200089);
INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Pepe', 'Honguito', 4303030);
INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Ovo', 'Orozco', 4666666);