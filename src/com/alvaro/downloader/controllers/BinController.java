package com.alvaro.downloader.controllers;

import com.alvaro.downloader.Main;
import com.alvaro.downloader.models.BinaryDownload;
import com.alvaro.downloader.views.BinDlMenu;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("SpellCheckingInspection")
public class BinController {
    private final MainController PARENT;
    public final BinDlMenu MENU;

    public BinController(String headerText, MainController parent) {
        MENU = new BinDlMenu(headerText);
        PARENT = parent;
    }

    /**
     * Función que recoge el nombre del fichero introducido por el teclado.
     * No sale de la función hasta que el nombre sea válido
     *
     * @return Retorna el nombre que se le pondrá al archivo
     */
    private String getFileName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";

        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            MENU.printError("La operación de escritura falló, intente de nuevo");
        }

        return fileName;
    }

    /**
     * Recoge la url deseada. Si no es correcta, la volverá a pedir hasta que lo sea.
     *
     * @return Retorna la URL
     */
    private URL createURL() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = "";
        URL url = null;

        while (url == null) {
            try {
                path = reader.readLine();
            } catch (IOException e) {
                MENU.printError("La operación de escritura falló, intente de nuevo");
            }

            try {
                url = new URL(path);
            } catch (MalformedURLException e) {
                MENU.printError("La URL no es correcta, intente de nuevo");
            }
        }

        return url;
    }

    /**
     * Espera hasta que el usuario presione Enter para abrir el manú principal
     */
    private void waitForEnterPress() {
        MENU.printMessage("Aprieta Intro para salir");

        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
        } catch (IOException e) {
            MENU.printError("La operación de escritura falló, intente de nuevo");
            waitForEnterPress();
        }
    }

    /**
     * Inicializa el controlador, que renderiza y maneja el submenú de descarga
     * binaria
     */
    public void start() {
        MENU.initialize();

        String fileName = getFileName();

        MENU.printEnterURLText();

        URL url = createURL();

        Main.downloadService.execute(() -> {
            var download = new BinaryDownload(fileName, url);
            download.startDownload();
        });

        waitForEnterPress();
        PARENT.start();
    }
}
