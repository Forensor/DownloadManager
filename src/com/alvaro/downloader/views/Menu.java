package com.alvaro.downloader.views;

public class Menu {
    private final String headerText;

    public Menu(String headerText) {
        this.headerText = " " + headerText;
    }

    /**
     * Genera las barras de decoración en base a la longitud del header
     *
     * @param length Longitud del header
     *
     * @return Barra de decoración
     */
    private String generateDecorationBar(int length) {
        final int ADDITIONAL_HYPHENS = 1;
        StringBuilder decorationBar = new StringBuilder();

        for (int i = 0; i < length + ADDITIONAL_HYPHENS; i++) decorationBar.append("-");

        return decorationBar.toString();
    }

    /**
     * Muestra por pantalla el header del menú junto con sus barras decorativas
     */
    private void printHeader() {
        String decorationBar = generateDecorationBar(headerText.length());

        System.out.println(decorationBar);
        System.out.println(headerText);
        System.out.println(decorationBar);
    }

    /**
     * Muestra por pantalla el error especificado
     *
     * @param err Error a mostrar en pantalla
     */
    public void printError(String err) {
        System.out.println("Error: " + err);
    }

    /**
     * Muestra por pantalla un mensaje
     *
     * @param msg El mensaje a mostrar en pantalla
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Inicializa el menú, mostrando el header
     */
    public void initialize() {
        printHeader();
    }
}
