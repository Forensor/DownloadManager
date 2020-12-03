package com.alvaro.downloader.models;

import com.alvaro.downloader.Main;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("SpellCheckingInspection")
public class TextDownload {
    private String fileName;
    private final URL url;
    private final FileWriter file;

    public TextDownload(String fileName, URL url) {
        this.fileName = fileName;
        this.url = url;
        this.file = createFile(fileName);
        addToQueue();
    }

    /**
     * Crea el fichero en el que se volcará la descarga
     *
     * @param fileName Nombre del fichero local
     *
     * @return Fichero en el que se volcará la descarga
     */
    private FileWriter createFile(String fileName) {
        FileWriter file = null;

        try {
            if (fileName.length() > 0) {
                file = new FileWriter(fileName);
            } else {
                String[] fields = url.toString().split("/");
                this.fileName = fields[fields.length - 1];
                file = new FileWriter(this.fileName);
            }
        } catch (IOException e) {
            System.out.println("Hubo un problema al crear el fichero, intente de nuevo");
        }

        return file;
    }

    /**
     * Añade la descarga a la cola, situada en la lista sincronizada de
     * Main.downloadsInProgress
     */
    private void addToQueue() {
        URLConnection conn = null;

        try {
            conn = url.openConnection();
        } catch (IOException e) {
            System.out.println("Hubo un problema con la conexión");
        }

        assert conn != null;
        long size = conn.getContentLengthLong();
        synchronized (Main.downloadsInProgress) {
            Main.downloadsInProgress.add(
                    new Download(url.toString(), fileName, 0, size));
        }
    }

    /**
     * Quita la descarga deseada de la cola, buscando por nombre de fichero
     */
    private void removeFromQueue() {
        synchronized (Main.downloadsInProgress) {
            for (int i = 0; i < Main.downloadsInProgress.size(); i++) {
                if (this.fileName.equals(Main.downloadsInProgress.get(i).fileName)) {
                    Main.downloadsInProgress.remove(i);
                }
            }
        }
    }

    /**
     * Inicia la descarga y la añade a la cola Main.downloadsInProgress. Al acabar
     * la retira.
     */
    public void startDownload() {
        try {
            InputStream stream = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String txt;

            while((txt = br.readLine()) != null) {
                file.write(txt);

                synchronized (Main.downloadsInProgress) {
                    for (Download d : Main.downloadsInProgress) {
                        if (this.fileName.equals(d.fileName)) {
                            d.downloaded += txt.length();
                            d.bpsCounter += d.downloaded;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("La descarga falló");
        }

        removeFromQueue();
    }
}
