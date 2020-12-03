package com.alvaro.downloader.views;

import com.alvaro.downloader.Main;
import com.alvaro.downloader.models.Download;

import java.text.DecimalFormat;

@SuppressWarnings("SpellCheckingInspection")
public class DownMenu extends Menu {
    public DownMenu(String headerText) {
        super(headerText);
    }

    /**
     * Muestra las descargas en curso. Si el tamaño de la descarga es indeterminado,
     * no muestra ni éste ni el tiempo estimado de descarga
     */
    private void printDownloads() {
        DecimalFormat df = new DecimalFormat("#.##");

        synchronized (Main.downloadsInProgress) {
            for (Download d : Main.downloadsInProgress) {
                System.out.print("**** " + d.url + " en " + d.fileName +
                        "\nDescargado " + d.downloaded + " Bytes");

                if (d.size != -1) {
                    System.out.print(" de " + d.size + " " +
                            percentage(d.downloaded, d.size) + " " + df.format(d.bps) + " Bps");

                    if (d.bps > 0) {
                        System.out.print("\n" + eta(d.size, d.downloaded, d.bps));
                    }
                }

                System.out.println();
            }
        }
    }

    /**
     * Muestra el porcentaje completado de descarga
     *
     * @param current   Los bytes que se llevan descargados
     * @param remaining El tamaño total en bytes del archivo a descargar
     *
     * @return El porcentaje se devuelve con este formato: [#.##%]
     */
    private String percentage(int current, long remaining) {
        double percent = (double)current * 100 / (double)remaining;
        DecimalFormat df = new DecimalFormat("#.##");

        return "[" + df.format(percent) + "%]";
    }

    /**
     * Muestra el tiempo estimado de descarga (ETA)
     *
     * @param size       El tamaño del archivo a descargar
     * @param downloaded Los bytes que se llevan descargados
     * @param speed      La velocidad de la conexión en B/s
     *
     * @return Devuelve el texto indicando cuantos segundos faltan
     * para que se complete la descarga
     */
    private String eta(long size, int downloaded, double speed) {
        DecimalFormat df = new DecimalFormat("#.##");

        return "Faltan " + df.format((((double)size - (double)downloaded) / speed))
                + " segundo/s";
    }

    /**
     * Muestra por pantalla el texto de apretar Enter
     */
    private void printPressEnterToExit() {
        System.out.println("Aprieta Intro para salir");
    }

    /**
     * Inicializa el menú, mostrando el header y las descargas en curso
     */
    @Override
    public void initialize() {
        super.initialize();
        printDownloads();
        synchronized (Main.downloadsInProgress) {
            System.out.println("Descargas en curso: " + Main.downloadsInProgress.size());
        }
        printPressEnterToExit();
    }
}
