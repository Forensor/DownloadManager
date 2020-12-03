package com.alvaro.downloader.views;

@SuppressWarnings("SpellCheckingInspection")
public class TxtDlMenu extends Menu {

    public TxtDlMenu(String headerText) {
        super(headerText);
    }

    /**
     * Muestra el texto de ayuda para el usuario
     */
    private void printHintText() {
        System.out.println("Introduce el nombre del archivo donde deseas descargar el\n" +
                "recurso de Internet. Aprieta Intro para que el sistema elija uno\n" +
                "automáticamente:");
    }

    /**
     * Muestra el texto de introducir URL
     */
    public void printEnterURLText() {
        System.out.println("Introduce la URL");
    }

    /**
     * Inicializa el menú, mostrando los principales elementos
     */
    @Override
    public void initialize() {
        super.initialize();
        printHintText();
    }
}
