package com.universidad.tienda.facade;

import org.springframework.stereotype.Component;

// Subsistema 2: servicio especializado de SMS
@Component
public class SMSService {
    public void enviar(String telefono, String mensaje) {
        System.out.println("SMS → " + telefono + ": " + mensaje);
    }
}