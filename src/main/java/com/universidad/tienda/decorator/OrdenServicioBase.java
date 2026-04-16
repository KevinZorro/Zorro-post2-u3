package com.universidad.tienda.decorator;

import org.springframework.stereotype.Component;

// ConcreteComponent: lógica base de procesamiento — no debe modificarse
@Component("ordenBase")
public class OrdenServicioBase implements OrdenServicio {

    @Override
    public String procesarOrden(String ordenId, double monto) {
        System.out.println("BASE: Procesando orden " + ordenId + " por $" + String.format("%,.0f", monto));
        return "PROCESADA:" + ordenId;
    }
}