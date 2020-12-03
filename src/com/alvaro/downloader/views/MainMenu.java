package com.alvaro.downloader.views;

@SuppressWarnings("SpellCheckingInspection")
public class MainMenu extends Menu {

    public MainMenu(String headerText) {
        super(headerText);
    }

    /**
     * Muestra por pantalla las opciones del menú principal
     */
    private void printOptions() {
        System.out.println("    1) Descargar una URL en formato texto");
        System.out.println("    2) Descargar una URL en formato binario");
        System.out.println("    3) Mostrar descargas en curso");
        System.out.println("    0) Salir del programa");
    }

    /**
     * Muestra por pantalla un mensaje de despedida
     */
    public void sayGoodbye() {
        System.out.println("¡Hasta otra!");
    }

    /**
     * Inicializa el menú principal
     */
    @Override
    public void initialize() {
        super.initialize();
        printOptions();
    }
}
