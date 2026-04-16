package com.universidad.tienda.decorator;

// Decorator abstracto: mantiene referencia al componente envuelto y delega la operación
public abstract class OrdenServicioDecorator implements OrdenServicio {

    protected final OrdenServicio wrapped;

    public OrdenServicioDecorator(OrdenServicio wrapped) {
        this.wrapped = wrapped;
    }
}