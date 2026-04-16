package com.universidad.tienda.decorator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ConcreteDecorator 3: registra auditoría con timestamp después de procesar
public class AuditoriaDecorator extends OrdenServicioDecorator {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AuditoriaDecorator(OrdenServicio wrapped) {
        super(wrapped);
    }

    @Override
    public String procesarOrden(String ordenId, double monto) {
        String resultado = wrapped.procesarOrden(ordenId, monto);
        System.out.println("[AUDITORIA] " + LocalDateTime.now().format(FMT)
                + " — Orden: " + ordenId
                + " — Resultado: " + resultado);
        return resultado;
    }
}