# Prueba NEXOS


## Descripción

La aplicación posibilita la realización de la autorización de transacciones mediante el 
diligenciamiento de un formulario. En dicho formulario, se tiene la flexibilidad de ajustar el monto 
total de la transacción. La aplicación utiliza el servicio de autorización de pagos para enviar la 
solicitud y, basándose en la respuesta obtenida, almacena la información de manera local para 
garantizar su persistencia. Este enfoque permite la visualización de cada transacción aprobada.

La aplicación también ofrece la funcionalidad de búsqueda de transacciones aprobadas a través del 
número de recibo auto-generado por el servicio de autorización de pagos. Además, se brinda la 
capacidad de anular transacciones previamente aprobadas utilizando el servicio de anulación de 
pagos.

En otra sección de la aplicación, se presenta una vista que muestra la lista completa de todas las 
transacciones, tanto aprobadas como anuladas. Se incorporan checkboxes para facilitar el filtrado de 
transacciones según los criterios deseados.


## Instalación

Descarga e instalación del APK


## Tecnologías utilizadas

Retrofit: Librería para el consumo de servicios.
Room: Librería para persistencia de la información.
Binding: Librería para acceder a las vistas.


## servicios consumidos

Servicio de autorización de pagos: http://localhost:8080/api/payments/authorization
Servicio de anulación de pagos: http://localhost:8080/api/payments/annulment