package com.alvaro.downloader.controllers;

import com.alvaro.downloader.views.DownMenu;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("SpellCheckingInspection")
public class DownController {
    private final MainController PARENT;
    public final DownMenu MENU;
    private Timer timer;

    public DownController(String headerText, MainController parent) {
        MENU = new DownMenu(headerText);
        PARENT = parent;
    }

    /**
     * Espera hasta que el usuario presione Enter para abrir el manú principal. Detiene
     * el Timer asociado
     */
    private void waitForEnterPress() {
        try {
            //noinspection ResultOfMethodCallIgnored
            System.in.read();
            timer.cancel();
        } catch (IOException e) {
            MENU.printError("La operación de escritura falló, intente de nuevo");
            waitForEnterPress();
        }
    }

    /**
     * Inicializa el controlador. Un TimerTask se encarga de imprimir las descargas en curso
     * cada 3 segundos, que es detenido tras pulsar Enter
     */
    public void start() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
                MENU.initialize();
            }
        }, 0, 3000);

        waitForEnterPress();
        PARENT.start();
    }
}
