package com.universidad.tienda;

import com.universidad.tienda.decorator.AuditoriaDecorator;
import com.universidad.tienda.decorator.LoggingDecorator;
import com.universidad.tienda.decorator.OrdenServicio;
import com.universidad.tienda.decorator.ValidacionDecorator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DecoratorConfig {

    // Cadena de decoradores: Auditoria → Validacion → Logging → Base
    // El orden garantiza: primero valida, luego loguea tiempos, luego audita el resultado
    @Bean("ordenCompleto")
    public OrdenServicio ordenServicioCompleto(
            @Qualifier("ordenBase") OrdenServicio base) {
        return new AuditoriaDecorator(
                    new ValidacionDecorator(
                        new LoggingDecorator(base)));
    }
}