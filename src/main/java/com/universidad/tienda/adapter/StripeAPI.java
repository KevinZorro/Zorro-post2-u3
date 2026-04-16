package com.universidad.tienda.adapter;

// Simula la API externa de Stripe — interfaz diferente a PayPal
public class StripeAPI {
 public boolean charge(String token, long amountCents, String
currencyCode) {
 System.out.println("Stripe: cobro de " + amountCents + " centavos ["
+ currencyCode + "]");
 return token != null && !token.isBlank();
 }
}
