package com.universidad.tienda;

import com.universidad.tienda.composite.ItemCatalogo;
import com.universidad.tienda.decorator.OrdenServicio;
import com.universidad.tienda.facade.NotificacionFacade;
import com.universidad.tienda.servicio.TiendaServicio;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaApp implements CommandLineRunner {

    private final TiendaServicio      servicio;
    private final OrdenServicio       ordenCompleto;
    private final NotificacionFacade  notificaciones;

    public TiendaApp(TiendaServicio servicio,
                     @Qualifier("ordenCompleto") OrdenServicio ordenCompleto,
                     NotificacionFacade notificaciones) {
        this.servicio       = servicio;
        this.ordenCompleto  = ordenCompleto;
        this.notificaciones = notificaciones;
    }

    public static void main(String[] args) {
        SpringApplication.run(TiendaApp.class, args);
    }

    @Override
    public void run(String... args) {

        // ── Demo Post-Contenido 1: Composite + Adapter ──────────────────────
        System.out.println("══════════════════════════════════════");
        System.out.println("   CATÁLOGO (Composite)");
        System.out.println("══════════════════════════════════════");
        ItemCatalogo catalogo = servicio.construirCatalogo();
        catalogo.mostrar(0);

        System.out.println("\n══════════════════════════════════════");
        System.out.println("   PAGO (Adapter)");
        System.out.println("══════════════════════════════════════");
        boolean pago = servicio.realizarCompra(4_500_000, "tok_test_123");
        System.out.println("Pago exitoso: " + pago);

        // ── Demo Post-Contenido 2: Decorator ─────────────────────────────────
        System.out.println("\n══════════════════════════════════════");
        System.out.println("   PROCESAMIENTO DE ORDEN (Decorator)");
        System.out.println("══════════════════════════════════════");
        try {
            String resultado = ordenCompleto.procesarOrden("ORD-2025-001", 4_500_000.0);
            System.out.println("Resultado final: " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        }

        // ── Demo Post-Contenido 2: Facade ─────────────────────────────────────
        System.out.println("\n══════════════════════════════════════");
        System.out.println("   NOTIFICACIONES (Facade)");
        System.out.println("══════════════════════════════════════");
        notificaciones.notificarCompraExitosa(
            "kevin@ejemplo.com",
            "+573001234567",
            "push_token_abc123",
            "ORD-2025-001"
        );
    }
}