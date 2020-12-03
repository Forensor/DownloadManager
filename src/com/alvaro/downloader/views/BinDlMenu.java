package com.alvaro.downloader.views;

@SuppressWarnings("SpellCheckingInspection")
public class BinDlMenu extends Menu {

    public BinDlMenu(String headerText) {
        super(headerText);
    }

    /**
     * Muestra por pantalla el texto de ayuda
     */
    public void printHintText() {
        System.out.println("Introduce el nombre del archivo donde deseas descargar el\n" +
                "recurso de Internet. Aprieta Intro para que el sistema elija uno\n" +
                "automáticamente:");
    }

    /**
     * Muestra por pantalla el texto que pide la URL
     */
    public void printEnterURLText() {
        System.out.println("Introduce la URL");
    }

    /**
     * Inicializa el menú, que muestra el header y texto principal
     */
    @Override
    public void initialize() {
        super.initialize();
        printHintText();
    }
}
