# SpringBoot-BackEnd-Vite-FrontEnd-TechnicalTest

**MiExpress** es una tienda que lleva más de 10 años en el mercado y ha venido registrando sus ventas de forma manual, lo que ha ocasionado descuadres y pérdidas de dinero. Actualmente, no existe control sobre los productos en stock ni sobre las ventas realizadas diariamente.

Con el objetivo de optimizar la gestión de ventas y productos, se desarrolló un sistema que permite:

- Registrar ventas por cliente.
- Controlar el inventario de productos.
- Visualizar productos agregados en cada venta.
- Calcular automáticamente el valor total por producto y el valor total de la venta.
- Impedir la venta de unidades no disponibles en stock.

---

## Tecnologías utilizadas

- **Back-End:** Java + Spring Boot + Hibernate (JPA)
- **Front-End:** Vite (compatible con React o Angular)
- **Base de datos:** H2 embebida (temporal)
- **Herramienta de construcción:** Maven
- **Pruebas de servicios:** Colecciones de Postman por cada tabla

---

## Requisitos

- Uso de **DTOs/VOs** para las solicitudes y respuestas de los servicios REST.
- **Hibernate** como ORM.
- Estructura de base de datos definida en `schema.sql` y datos iniciales en `data.sql`.

---

## Configuración del proyecto

### 1. Descarga de dependencias (en Eclipse)

1. Click derecho sobre el proyecto → **Maven** → **Update Project...**
2. Click derecho sobre el proyecto → **Run As** → **Maven install**

> Si todo está correcto, verás el mensaje `BUILD SUCCESS`.

---

## Estructura de la base de datos

La base de datos embebida H2 se encuentra configurada en los siguientes archivos:

```
/src/main/resources/schema.sql
/src/main/resources/data.sql
```

Estos definen las tablas y datos iniciales, los cuales se restablecen al reiniciar la aplicación.

---

## Iniciar la aplicación

### Back-End (Spring Boot)

1. Abrir el archivo:

```
com.miempresa.miexpress.MiexpressApplication
```

2. Click derecho → **Run As** → **Java Application**

> El servidor se inicia en el puerto `8080` por defecto.

Si deseas cambiar el puerto, edita el archivo:

```
/src/main/resources/application.properties
```

Agregando:

```
server.port=8090
```

---

### Front-End (Vite)

Para iniciar el proyecto Vite:

```bash
npm install
npm run dev
```

---

## Postman

Se creó una colección de Postman con endpoints para cada una de las siguientes entidades:

- Usuarios
- Clientes
- Administradores
- Categorías
- Productos
- Carrito
- Carrito Productos
- Órdenes
- Órdenes Productos

Incluyen operaciones CRUD, así como pruebas de relaciones y operaciones lógicas como agregar productos al carrito y calcular total de orden.

---

## Uso del sistema

Para acceder al backend, este correra en:

```
http://localhost:8080/
```

---
