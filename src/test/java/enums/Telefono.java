package enums;

import java.util.stream.Stream;

public enum Telefono {

    MOVIL("M\u00F3vil"),
    FIJO("Fijo");

    Telefono(String visibleText) {
        this.visibleText = visibleText;
    }

    private final String visibleText;

    public String get() {
        return this.visibleText;
    }

    public static Telefono of(String telefono) {
        return Stream.of(Telefono.values())
                .filter(opt -> opt.get().equalsIgnoreCase(telefono.trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Telefono '%s' no encontrada", telefono)));
    }

}
