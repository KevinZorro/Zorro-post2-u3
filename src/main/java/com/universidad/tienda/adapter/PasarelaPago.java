package com.universidad.tienda.adapter;

// Interfaz que el sistema espera — contrato interno
public interface PasarelaPago {
 boolean procesarPago(String moneda, double monto, String tokenCliente);
 String obtenerNombre();
}
