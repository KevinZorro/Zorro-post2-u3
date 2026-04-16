package com.universidad.tienda;

import com.universidad.tienda.adapter.*;
import com.universidad.tienda.composite.*;
import com.universidad.tienda.servicio.TiendaServicio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TiendaServicioTest {
 @Test
 void testAdapterPayPalProcesaPago() {
 PasarelaPago pp = new PayPalAdapter();
 assertTrue(pp.procesarPago("USD", 100.0, "tok_abc"));
 }
 @Test
 void testAdapterStripeRechazaTokenVacio() {
 PasarelaPago stripe = new StripeAdapter();
 assertFalse(stripe.procesarPago("COP", 500000, ""));
 }
 @Test
 void testCompositeCalculaPrecioTotal() {
 Categoria cat = new Categoria("Test");
 cat.agregar(new Producto("A", 100));
 cat.agregar(new Producto("B", 200));
 assertEquals(300.0, cat.getPrecioTotal());
 }
 @Test
 void testCompositeAnidado() {
 Categoria raiz = new Categoria("Raiz");
 Categoria sub = new Categoria("Sub");
 sub.agregar(new Producto("X", 500));
 raiz.agregar(sub);
 raiz.agregar(new Producto("Y", 200));
 assertEquals(700.0, raiz.getPrecioTotal());
 }
}