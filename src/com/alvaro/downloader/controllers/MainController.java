package com.alvaro.downloader.controllers;

import com.alvaro.downloader.Main;
import com.alvaro.downloader.views.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("SpellCheckingInspection")
public class MainController {
    public final MainMenu MENU;

    public MainController(String headerText) {
        MENU = new MainMenu(headerText);
    }

    /**
     * Función que comprueba si lo que se ha introducido es un número
     *
     * @param value El String que va a ser parseado
     *
     * @return Verdadero si es un número, falso si no lo es
     */
    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Funcion que recoge el número de menú seleccionado para cargarlo
     *
     * @param choice La elección del usuario (Las correctas abarcan 0-3)
     */
    private void loadMenu(int choice) {
        switch (choice) {
            case 1 -> {
                TxtController txtController =
                        new TxtController("Menú de descarga en modo texto", this);
                txtController.start();
            }
            case 2 -> {
                BinController binController =
                        new BinController("Menú de descarga en modo binario", this);
                binController.start();
            }
            case 3 -> {
                DownController downController =
                        new DownController("Menú de visualización de descargas", this);
                downController.start();
            }
            case 0 -> {
                MENU.sayGoodbye();
                System.exit(0);
            }
            default -> {
                MENU.printMessage("Por favor, seleccione uno de los valores superiores");
                waitForChoice();
            }
        }
    }

    /**
     * Función que espera la elección del usuario. Volverá a llamarse hasta que lo intro-
     * ducido sea un valor correcto
     */
    private void waitForChoice() {
        MENU.printMessage("Escriba un número: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";

        try {
            choice = reader.readLine();
        } catch (IOException e) {
            MENU.printError("La operación de escritura falló, intente de nuevo.");
            waitForChoice();
        }

        if (tryParseInt(choice)) {
            loadMenu(Integer.parseInt(choice));
        } else {
            MENU.printError("Por favor, escriba un valor numérico");
            waitForChoice();
        }
    }

    /**
     * Inicializa el menú principal y espera su elección
     */
    public void start() {
        MENU.initialize();
        Main.calculateBps();
        waitForChoice();
    }
}
