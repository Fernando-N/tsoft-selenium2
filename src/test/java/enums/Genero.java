package enums;

import java.util.stream.Stream;

public enum Genero {

    MASCULINO("Masculino"),
    FEMENINO("Femenino");

    Genero(String visibleText) {
        this.visibleText = visibleText;
    }

    private final String visibleText;

    public String get() {
        return this.visibleText;
    }

    public static Genero of(String genero) {
        return Stream.of(Genero.values())
                .filter(opt -> opt.get().equalsIgnoreCase(genero.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Genero '%s' no encontrada", genero)));
    }

}
