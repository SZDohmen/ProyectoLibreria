/* Clientes */

INSERT INTO clientes (nombre, apellido, telefono) VALUES('Patricia', 'González', 4455690);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Carlos', 'Yacomo', 4230098);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Marta', 'Minova', 4404040);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Víctor', 'Jara', 26189706);
INSERT INTO clientes (nombre, apellido, telefono) VALUES('Antonia', 'Pérez', 26156658);


/* Proveedores */

INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Graciela', 'Alfano', 4200089);
INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Pepe', 'Honguito', 4402230);
INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Armando', 'Paredes', 4666677);
INSERT INTO proveedores (nombre, apellido, telefono) VALUES('Lyana', 'Mormont', 4600666);


/* Precios */

INSERT INTO precios (precio_compra, precio_venta) VALUES(150.0, 300.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(423.5, 847.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(903.9, 1807.8);
INSERT INTO precios (precio_compra, precio_venta) VALUES(245.6, 491.2);
INSERT INTO precios (precio_compra, precio_venta) VALUES(165.0, 330.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(6.0, 12.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(15.0, 30.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(15.0, 30.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(12.5, 25.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(150.0, 300.0);
INSERT INTO precios (precio_compra, precio_venta) VALUES(35.0, 70.0);


/* Stock */

INSERT INTO stock (cantidad) VALUES(20);
INSERT INTO stock (cantidad) VALUES(12);
INSERT INTO stock (cantidad) VALUES(5);
INSERT INTO stock (cantidad) VALUES(2);
INSERT INTO stock (cantidad) VALUES(10);
INSERT INTO stock (cantidad) VALUES(1);
INSERT INTO stock (cantidad) VALUES(40);
INSERT INTO stock (cantidad) VALUES(40);
INSERT INTO stock (cantidad) VALUES(50);
INSERT INTO stock (cantidad) VALUES(20);
INSERT INTO stock (cantidad) VALUES(15);


/* Productos */

INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPICES FABER COLOR X 12', 1, 1);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPICES FABER COLOR X 24+1 SACAPUNTAS ', 2, 2);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPICES FABER COLOR X 48+1 SACAPUNTAS', 3, 3);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('CUADERNO A4 ARTE BLACK&WHITE 80H RAYAS', 4, 4);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('CUADERNO A4 ARTE ESCOCES 80H CUADROS', 5, 5);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPIZ FABER CASTEL HB', 6, 6);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPICERA BIC NEGRA', 7, 7);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('LAPICERA BIC AZUL', 8, 8);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('GOMA MAPED BLANCA', 9, 9);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('MARCADOR PERM.EDDING', 10, 10);
INSERT INTO productos (nombre, precio_id, stock_id) VALUES('MARCADOR PIZARRA EDDING', 11, 11);


/* Facturas de venta */

INSERT INTO facturas_venta (cliente_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 5);
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas_venta (cliente_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_venta (cantidad, factura_venta_id, producto_id) VALUES(3, 2, 6);


/* Facturas de compra */

INSERT INTO facturas_compra (proveedor_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_compra (cantidad, factura_compra_id, producto_id) VALUES(1, 1, 1);
INSERT INTO items_factura_compra (cantidad, factura_compra_id, producto_id) VALUES(2, 1, 4);
INSERT INTO items_factura_compra (cantidad, factura_compra_id, producto_id) VALUES(1, 1, 5);
INSERT INTO items_factura_compra (cantidad, factura_compra_id, producto_id) VALUES(3, 1, 7);

INSERT INTO facturas_compra (proveedor_id, fecha) VALUES(1, NOW());
INSERT INTO items_factura_compra (cantidad, factura_compra_id, producto_id) VALUES(3, 2, 6);
