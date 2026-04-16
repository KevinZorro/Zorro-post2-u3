# tienda-patrones-estructurales

Proyecto Spring Boot que implementa cuatro patrones estructurales GoF en el
contexto de una tienda virtual: **Adapter**, **Composite**, **Decorator** y **Facade**.

## Patrones Aplicados

### Decorator — Servicio de Órdenes
Agrega capas opcionales de logging, validación y auditoría sin modificar
`OrdenServicioBase`. La cadena se construye en `DecoratorConfig`:
AuditoriaDecorator
└── ValidacionDecorator
└── LoggingDecorator
└── OrdenServicioBase ← nunca se modifica


| Participante         | Clase                     |
|----------------------|---------------------------|
| Component            | `OrdenServicio` (interfaz)|
| ConcreteComponent    | `OrdenServicioBase`       |
| Decorator abstracto  | `OrdenServicioDecorator`  |
| ConcreteDecorator 1  | `LoggingDecorator`        |
| ConcreteDecorator 2  | `ValidacionDecorator`     |
| ConcreteDecorator 3  | `AuditoriaDecorator`      |

### Facade — Notificaciones Multicanal
`NotificacionFacade` oculta la complejidad de tres servicios independientes
(Email, SMS, Push). El cliente llama un único método en lugar de coordinar
tres servicios con firmas distintas.

| Participante     | Clase                  |
|------------------|------------------------|
| Facade           | `NotificacionFacade`   |
| Subsistema 1     | `EmailService`         |
| Subsistema 2     | `SMSService`           |
| Subsistema 3     | `PushService`          |

### Adapter y Composite
Ver [Post-Contenido 1](../apellido-post1-u3/README.md).

## Requisitos
- Java 17+
- Maven 3.8+

## Ejecución

```bash
git clone https://github.com/<usuario>/apellido-post2-u3.git
cd apellido-post2-u3
mvn clean package
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

Salida esperada: `BUILD SUCCESS` con todos los tests pasando.

## Salida de Consola Esperada (Demo Decorator)
══════════════════════════════════════
PROCESAMIENTO DE ORDEN (Decorator)
══════════════════════════════════════
[VALIDACION] Orden ORD-2025-001 validada correctamente.
[LOG] Iniciando procesamiento: ORD-2025-001
BASE: Procesando orden ORD-2025-001 por $4,500,000
[LOG] Completado en 1ms. Resultado: PROCESADA:ORD-2025-001
[AUDITORIA] 2026-04-16 17:00:00 — Orden: ORD-2025-001 — Resultado: PROCESADA:ORD-2025-001
Resultado final: PROCESADA:ORD-2025-001

### Adapter
Normaliza las APIs incompatibles de PayPal y Stripe hacia la interfaz interna
`PasarelaPago`. El sistema nunca depende de las clases externas directamente.

| Participante       | Clase                    |
|--------------------|--------------------------|
| Target             | `PasarelaPago` (interfaz)|
| Adaptee 1          | `PayPalAPI`              |
| Adaptee 2          | `StripeAPI`              |
| Adapter 1          | `PayPalAdapter`          |
| Adapter 2          | `StripeAdapter`          |

### Composite
Modela el catálogo de productos con categorías anidadas
(Catálogo → Electrónica → Computadores → Laptops). La interfaz `ItemCatalogo`
permite tratar hojas (`Producto`) y nodos (`Categoria`) de forma uniforme.

| Participante | Clase           |
|--------------|-----------------|
| Component    | `ItemCatalogo`  |
| Leaf         | `Producto`      |
| Composite    | `Categoria`     |

## Requisitos

- Java 17+
- Maven 3.8+

## Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/<usuario>/apellido-post1-u3.git
cd apellido-post1-u3

# Compilar y empaquetar
mvn clean package

# Ejecutar
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

Salida esperada: `BUILD SUCCESS` con 4 tests pasando.

## Salida de Consola Esperada

==============================
CATÁLOGO DE PRODUCTOS
==============================
[Catálogo General] → Total: $12,115,000
[Electrónica] → Total: $11,900,000
[Computadores] → Total: $10,700,000
- Laptop Dell XPS 15 — $4,500,000
- MacBook Air M3 — $6,200,000
- Audífonos Sony WH-1000XM5 — $1,200,000
[Libros Técnicos] → Total: $215,000
- Clean Code — Robert Martin — $95,000
- Design Patterns — GoF — $120,000

==============================
PROCESANDO COMPRA
==============================
Procesando pago con: Stripe
Stripe: cobro de 450000000 centavos [COP]
Estado del pago: APROBADO ✓
Pago exitoso: true


## Captura de Pantalla
Ejecucion
<img width="925" height="505" alt="image" src="https://github.com/user-attachments/assets/8950294a-71f6-4755-8ad9-75a7470f5a70" />
