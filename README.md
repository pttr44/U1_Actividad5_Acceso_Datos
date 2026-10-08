# U1_Actividad5_Acceso_Datos - PromeHub Data Exchange 🎮

Proyecto práctico para la asignatura de **Acceso a Datos**. Esta aplicación actúa como un intermediario/conversor de datos entre dos aplicaciones de la empresa PromeHub (**PromeHub Manager**, que maneja ficheros CSV, y **PromeHub Store**, que trabaja con documentos XML). Permite la conversión y manipulación bidireccional en flujos `CSV ↔ Java ↔ XML`.

---

## 📋 Pre-requisitos

Para ejecutar este proyecto necesitarás contar con las siguientes herramientas instaladas en tu sistema:

* **JDK 25.0.1 (Java Development Kit)**:
  * [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
  * [Eclipse Temurin (Adoptium)](https://adoptium.net/)
* **Apache Maven**:
  * Descargar desde la página oficial de [Apache Maven Download](https://maven.apache.org/download.cgi)
* **Entorno de desarrollo (IDE)**:
  * [Visual Studio Code](https://code.visualstudio.com/) (con la extensión *Extension Pack for Java*)

Puedes comprobar las versiones instaladas en tu terminal ejecutando:

```bash
java -version
mvn -version
```

---

## 🛠️ Construido con

* [Java](https://www.oracle.com/java/) - Lenguaje de programación principal
* [Maven](https://maven.apache.org/) - Gestor de dependencias y construcción del proyecto
* **JAXB (Java Architecture for XML Binding)** - Procesamiento y mapeo de objetos Java a XML y viceversa

---

## 🏗️ Modelo de Datos y Estructura XML

Cada videojuego en el sistema cuenta con los siguientes atributos:
* `id` (Identificador único)
* `titulo`
* `plataforma`
* `genero`
* `precio`
* `stock`
* `codigoProveedor` (Información interna de PHManager que se excluye del XML)

### Anotaciones JAXB Utilizadas 🏷️

Para conseguir la transformación a XML mediante JAXB, se han empleado las siguientes anotaciones:
* `@XmlRootElement`: Define el elemento raíz del documento XML (`<catalogo>`).
* `@XmlAccessorType`: Especifica cómo se mapean los campos o propiedades de la clase a XML.
* `@XmlAttribute`: Mapea el atributo `id` como un atributo XML (`<videojuego id="...">`).
* `@XmlElement`: Mapea las propiedades principales como elementos/etiquetas secundarias.
* `@XmlTransient`: Excluye el campo `codigoProveedor` de la serialización XML para proteger información interna.

**Estructura del XML generado:**
```xml
<catalogo>
  <videojuego id="7">
    <titulo>Cyberpunk 2077</titulo>
    <plataforma>PC</plataforma>
    <genero>RPG</genero>
    <precio>39.99</precio>
    <stock>12</stock>
  </videojuego>
</catalogo>
```
*(Nota: El atributo `codigoProveedor` queda excluido automáticamente en el XML).*

---

## 🔄 Flujo de Trabajo y Funcionalidades

La aplicación ofrece un menú interactivo por consola con las siguientes funciones:

1. **Cargar catálogo desde CSV**: Lee el fichero `videojuegos.csv` de forma secuencial, omite la cabecera, valida los campos y carga los registros válidos en una colección.
2. **Mostrar catálogo**: Imprime por consola la información resumida de los videojuegos cargados.
3. **Exportar catálogo a XML**: Genera el documento XML (`catalogo.xml`) utilizando JAXB a partir de la colección activa.
4. **Cargar catálogo desde XML**: Lee `catalogo.xml` reconstruyendo la colección de objetos en memoria.
5. **Exportar catálogo a CSV**: Genera nuevamente un archivo CSV a partir del catálogo importado.
6. **Buscar videojuego**: Permite buscar elementos por su `id` o por su `título`.
7. **Información de ficheros**: Muestra metadatos de los ficheros utilizados (existencia, tamaño en bytes y ruta absoluta).
0. **Salir**: Finaliza la ejecución del programa.

---

## ⚠️ Gestión de Excepciones

El sistema incluye un control de errores mediante mensajes personalizados en español:
* **Ficheros inexistentes / Errores de E/S**: Comprobación previa de existencia antes de realizar lecturas o escrituras.
* **Registros CSV incorrectos o datos corruptos**: Detección de líneas incompletas o mal formadas.
* **Conversión numérica**: Manejo de `NumberFormatException` al parsear números, precios o stock invalidados.
* **Errores de procesamiento XML**: Captura de excepciones JAXB al parsear/generar el XML.
* **Entrada de menú inválida**: Validación de opciones numéricas fuera del rango permitido.

---

## 🧪 Catálogo de Pruebas (QA)

Se han diseñado y validado los siguientes casos de prueba:

| Caso de Prueba | Descripción / Procedimiento | Resultado Esperado |
| :--- | :--- | :--- |
| **CP01** | Carga inicial de datos desde CSV (`videojuegos.csv`). | Carga correcta de registros en memoria ignorando la cabecera. |
| **CP02** | Exportación a XML mediante JAXB. | Creación de `catalogo.xml` respetando la estructura con el atributo `id`. |
| **CP03** | Verificación de confidencialidad en XML. | Confirmar que `codigoProveedor` NO aparece dentro de `catalogo.xml`. |
| **CP04** | Importación desde XML a objetos Java. | Reconstrucción correcta del catálogo desde `catalogo.xml`. |
| **CP05** | Conversión XML → Java → CSV. | Generación de un fichero CSV válido a partir de los datos importados del XML. |
| **CP06** | Control de fichero inexistente. | Captura de excepción y muestra de mensaje en español indicando la ruta no encontrada. |
| **CP07** | Registro CSV corrupto / mal formateado. | Notificación del registro incorrecto y prosecución con los demás registros válidos. |

---

## ✒️ Autores y Roles

* **Peter Montero Núñez** - *Team Leader* - [pttr44](https://github.com/pttr44)
* **Sergio Díaz de Rivera Anta** - *Programador Experto* - [botombadebumbano](https://github.com/botombadebumbano)
* **Joel Gutiérrez Corrales** - *QA (Responsable de Pruebas)* - [joel-gc98](https://github.com/joel-gc98)
* **Arturo Martín de la Sierra García** - *Responsable de Documentación* - [Artyx3147](https://github.com/Artyx3147)

---

## 🎁 Expresiones de Gratitud

* Comenta a otros sobre este proyecto 📢
* Apoya el repositorio dándole una estrella ⭐ en GitHub.
* ¡Muchas gracias por revisar este proyecto! 🤓
