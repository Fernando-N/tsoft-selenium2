package enums;

import java.util.stream.Stream;

public enum TipoCuenta {

    PERSONA("Persona"),
    PROFESIONAL("Profesional");

    TipoCuenta(String visibleText) {
        this.visibleText = visibleText;
    }

    private final String visibleText;

    public String get() {
        return this.visibleText;
    }

    public static TipoCuenta of(String tipoCuenta) {
        return Stream.of(TipoCuenta.values())
                .filter(opt -> opt.get().equalsIgnoreCase(tipoCuenta.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("TipoCuenta '%s' no encontrada", tipoCuenta)));
    }

}
