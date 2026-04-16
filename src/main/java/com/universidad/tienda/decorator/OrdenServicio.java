package com.universidad.tienda.decorator;

// Component: contrato uniforme para el servicio base y todos sus decoradores
public interface OrdenServicio {
    String procesarOrden(String ordenId, double monto);
}