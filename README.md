
# WebService procesamiento de layouts  📘
  
 📌 Web service codificado en Java ☕ con el framework Spring que permite el procesamiento de layouts de campos y filtros en formato XML.
  
## Requisitos 🔧  
  
### Clonar el proyecto 📋  

Clonar el proyecto en el directorio de su elección.  
Para editarlo es necesario importar el proyecto con un IDE compatible con proyectos maven, se recomienda el uso de IntelliJ.  
  
### Persistencia de los datos  💾
  
- TODO  
  
## Ejecución 🚀  
  
Para ejecutar el proyecto es necesario agregar una configuración de ejecución en el IDE, la clase que se debe ejecutar es `AppApplication`, la cual contiene el método principal y se encuentra en el package `com.read.app`.  

## Configuración

Para poder ejecutar el proyecto antes hay que asegurarse que se cuente con los archivos necesarios de layout los cuales serán mepeados por el proceso, estos deben encontrarse en la carpeta config del mismo proyecto, los archivos necesarios son los siguientes:

- `layout.xml`

Contiene los campos que se van a procesar, se divide en dos layouts, uno de entrada y el otro de salida.
Cada campo contiene la siguiente información

- Id
- Name
- Sequence
- Length
- Type

La estructura de este archivo es la siguiente:

```xml
<layout>  
	<layout-in> 
		<fields> 
			<field id="1" name="Origen_Liver" sequence="2" length="2" type="ALPHANUMERIC" />  
		</fields> 
	</layout-in> 
	<layout-out> 
		<fields> 
			<field id="1" name="Origen_Liver" sequence="2" length="2" type="ALPHANUMERIC" />  
		</fields> 
	</layout-out>
</layout>
```

- `filters.xml`

Contiene los filtros que se van a aplicar a los campos del layout anterior.
Cada filtro contiene la siguiente información

- Id
- Layout
- Name
- Sequence
- FieldId
-  Operator
- Value
- Parenthesis
- Negated
- Connector

La estructura de este archivo es la siguiente:

```xml
<filters>  
	<filter id="1" layout="layout-in" name="Filtro comun" sequence="1" fieldId="2" operator="a" value="5" parenthesis="(" negated="false" connector="OR"/>  
	<filter id="2" layout="layout-in" name="Filtro comun 2" sequence="2" fieldId="2" operator="a" value="3" parenthesis=")" negated="false" connector=""/>  
</filters>
```

- `configuration.xml`

Contiene las configuraciones que se aplicarán para programar la tarea.

- Reload
- - Unit
- - Quantity

La estructura de este archivo es la siguiente:

```xml
<configuration>  
	<reload unit="MINUTES" quantity="1"/>  
</configuration>
```