package com.universidad.tienda;

import com.universidad.tienda.decorator.AuditoriaDecorator;
import com.universidad.tienda.decorator.LoggingDecorator;
import com.universidad.tienda.decorator.OrdenServicio;
import com.universidad.tienda.decorator.OrdenServicioBase;
import com.universidad.tienda.decorator.ValidacionDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    private OrdenServicio ordenCompleto;

    // Construye la misma cadena que DecoratorConfig sin levantar el contexto Spring
    @BeforeEach
    void setUp() {
        OrdenServicio base = new OrdenServicioBase();
        ordenCompleto = new AuditoriaDecorator(
                            new ValidacionDecorator(
                                new LoggingDecorator(base)));
    }

    @Test
    @DisplayName("Cadena completa procesa correctamente una orden válida")
    void testOrdenValida() {
        String result = ordenCompleto.procesarOrden("ORD-001", 50_000.0);
        assertTrue(result.startsWith("PROCESADA:"),
                "El resultado debe comenzar con 'PROCESADA:'");
    }

    @Test
    @DisplayName("ValidacionDecorator rechaza monto igual a cero")
    void testOrdenMontoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> ordenCompleto.procesarOrden("ORD-002", 0.0),
                "Monto 0 debe lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("ValidacionDecorator rechaza ID de orden vacío")
    void testOrdenIdVacio() {
        assertThrows(IllegalArgumentException.class,
                () -> ordenCompleto.procesarOrden("", 10_000.0),
                "ID vacío debe lanzar IllegalArgumentException");
    }

    @Test
    @DisplayName("LoggingDecorator solo no valida — permite monto 0")
    void testDecoradorIndividualLogging() {
        OrdenServicio base   = new OrdenServicioBase();
        OrdenServicio conLog = new LoggingDecorator(base);
        // Sin ValidacionDecorator, el monto 0 no debe lanzar excepción
        assertDoesNotThrow(() -> conLog.procesarOrden("ORD-003", 0.0),
                "LoggingDecorator solo no debe validar montos");
    }
}