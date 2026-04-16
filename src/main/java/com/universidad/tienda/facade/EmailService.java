package com.universidad.tienda.facade;

import org.springframework.stereotype.Component;

// Subsistema 1: servicio especializado de email
@Component
public class EmailService {
    public void enviar(String destinatario, String asunto, String cuerpo) {
        System.out.println("EMAIL → " + destinatario + " | Asunto: " + asunto);
        System.out.println("       Cuerpo: " + cuerpo);
    }
}