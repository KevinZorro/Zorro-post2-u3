package com.universidad.tienda.facade;

import org.springframework.stereotype.Component;

// Subsistema 3: servicio especializado de notificaciones push
@Component
public class PushService {
    public void enviar(String token, String titulo, String cuerpo) {
        System.out.println("PUSH → " + token + " | Título: " + titulo);
    }
}