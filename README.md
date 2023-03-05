## Especificaciones Técnicas:

- Java 11
- Interfaz gráfica creada en swing y awt.
- Conexiones a bases de datos con Hibernate y JPA.
- Configurado inicialmente para trabajar con Bases de datos en MySQL y SQLite, pero puede funcionar con cualquier otra.
- Informe generado en PDF con JasperReport.

## Descripción General:

El informe de calidad de datos analiza principalmente la información en las tablas de CRR_RECLAMOS y CRR_RECLAMANTES. Como se puede apreciar en el ejemplo a continuación, el informe muestra la cantidad de datos recibidos y el porcentaje que representa sobre la cantidad total de registros.

Este programa opcionalmente puede quedar ejecutando en segundo plano, para que generen y se envíen automáticamente los informes según las fechas y los periodos establecidos a las direcciones de correo electrónico indicadas en la ventana “Input”.

## Parámetro de informe 
El informe está basado en el rango de fechas a analizar mediante la colocación de campo de ‘fecha de inicio’ y campo de ‘fecha final’ según el campo CRR_RECLAMOS.fecharegistro (TABLA.campo). Por defecto ‘fecha inicial’ toma el valor del primer día del mes anterior, y puede ser modificado. Por defecto ‘fecha final’ toma el valor del último día del mes anterior, y puede ser modificado.


## Salida del informe
 El informe se genera en .pdf, en dos páginas, aseguradoras en orden ascendente por la columna ASEGURADORAS.aseguradora.id, y se despliega en el informe el texto que aparece en la columna ASEGURADORAS.nombre_corto, como título de las columnas en el informe.
 
  ![Aquí la descripción de la imagen por si no carga](https://raw.githubusercontent.com/arochaj2/ciberneticaReport/main/assets/imagen_1.jpg)
 
## Paso a Paso para generar el Informe.
1.	Presionar el botón Input para establecer los parámetros de entrada.

  ![Aquí la descripción de la imagen por si no carga](https://raw.githubusercontent.com/arochaj2/ciberneticaReport/main/assets/imagen_2.jpg)


2.	Seleccionar el tipo de conexión a la base de datos que se desea realizar (MySQL o SQLite) y establecer los datos de la conexión, el proyecto trae una base de datos por defecto ubicada en la carpeta resources para realizar pruebas.

3.	Presionar el botón conectar para establecer la conexión con la base de datos.

  ![Aquí la descripción de la imagen por si no carga](https://raw.githubusercontent.com/arochaj2/ciberneticaReport/main/assets/imagen_3.jpg)
  
  4.	Luego de establecer la conexión se mostrarán las aseguradoras, deberá agregar a la lista “Reporte a Generar” a aquellas aseguradoras de las cuales desea visualizar en el informe, presionar “Output”.

  ![Aquí la descripción de la imagen por si no carga](https://raw.githubusercontent.com/arochaj2/ciberneticaReport/main/assets/imagen_4.jpg)
  
  5.	En la ventana “Output” presionar generar reporte y seleccionar la ubicación donde se guardará el informe. Una vez seleccione la carpeta el informe comenzara a generarse.

 ![Aquí la descripción de la imagen por si no carga](https://raw.githubusercontent.com/arochaj2/ciberneticaReport/main/assets/imagen_5.jpg)




