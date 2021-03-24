package enums;

import java.util.stream.Stream;

public enum Region {

    Metropolitana("Regi\u00F3n Metropolitana"),
    XV_AricaYParinacota("XV Arica & Parinacota"),
    I_Tarapaca("I Tarapac\u00E1"),
    II_Antofagasta("II Antofagasta"),
    III_Atacama("III Atacama"),
    IV_Coquimbo("IV Coquimbo"),
    V_Valparaiso("V Valpara\u00EDso"),
    VI_OHiggins("VI O'Higgins"),
    VII_Maule("VII Maule"),
    XVI_Nuble("XVI \u00D1uble"),
    VIII_Biobio("VIII Biob\u00EDo"),
    IX_Araucania("IX Araucan\u00EDa"),
    XIV_LosRios("XIV Los R\u00EDos"),
    X_LosLagos("X Los Lagos"),
    XI_Aisen("XI Ais\u00E9n"),
    XII_MagallanesYAntartica("XII Magallanes & Ant\u00E1rtica");

    Region(String visibleText) {
        this.visibleText = visibleText;
    }

    private final String visibleText;

    public String get() {
        return this.visibleText;
    }

    public static Region of(String ordinal) {
        return Stream.of(Region.values())
                .filter(opt -> opt.get().toUpperCase().contains(ordinal.toUpperCase().trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Region '%s' no encontrada", ordinal)));
    }

}
