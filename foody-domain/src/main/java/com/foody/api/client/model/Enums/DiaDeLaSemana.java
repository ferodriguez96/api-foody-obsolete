package com.foody.api.client.model.Enums;

import java.time.DateTimeException;

public enum DiaDeLaSemana {
    MONDAY("Lunes"),TUESDAY("Martes"),WEDNESDAY("Miercoles"),THURSDAY("Jueves"),FRIDAY("Viernes"),SATURDAY("Sabado"),SUNDAY("Domingo");

    private static final DiaDeLaSemana[] ENUMS = DiaDeLaSemana.values();
    private final String inSpanish;


    DiaDeLaSemana(String inSpanish) {
        this.inSpanish = inSpanish;
    }

    public static DiaDeLaSemana of(int dia) {
        if (dia < 1 || dia > 7) {
            throw new DateTimeException("Invalid value for dia: " + dia);
        }
        return ENUMS[dia - 1];
    }

    public int getValue() {
        return ordinal() + 1;
    }

    public String getInSpanish(){return inSpanish;};
}
