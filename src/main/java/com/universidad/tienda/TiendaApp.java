package com.universidad.tienda;

import com.universidad.tienda.composite.ItemCatalogo;
import com.universidad.tienda.servicio.TiendaServicio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class TiendaApp implements CommandLineRunner {
 private final TiendaServicio servicio;
 public TiendaApp(TiendaServicio servicio) { this.servicio = servicio; }
 public static void main(String[] args) {
 SpringApplication.run(TiendaApp.class, args);
 }
 @Override
 public void run(String... args) {
 // Mostrar catálogo
 ItemCatalogo catalogo = servicio.construirCatalogo();
 catalogo.mostrar(0);
 System.out.println();
 // Procesar pago
 boolean ok = servicio.realizarCompra(4500000, "tok_test_123");
 System.out.println("Pago exitoso: " + ok);
 }
}