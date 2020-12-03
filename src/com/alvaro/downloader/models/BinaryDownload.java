package com.alvaro.downloader.models;

import com.alvaro.downloader.Main;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("SpellCheckingInspection")
public class BinaryDownload {
    public String fileName;
    private final java.net.URL url;
    private final FileOutputStream file;

    public BinaryDownload(String fileName, URL url) {
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
    private FileOutputStream createFile(String fileName) {
        FileOutputStream file = null;

        try {
            if (fileName.length() > 0) {
                file = new FileOutputStream(fileName);
            } else {
                String[] fields = url.toString().split("/");
                this.fileName = fields[fields.length - 1];
                file = new FileOutputStream(this.fileName);
            }
        } catch (IOException e) {
            System.out.println("Hubo un problema al crear el fichero");
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
            InputStream is = url.openStream();
            int bc;
            byte[] buffer = new byte[1024];

            while ((bc = is.read(buffer)) != -1) {
                file.write(buffer, 0, bc);

                synchronized (Main.downloadsInProgress) {
                    for (Download d : Main.downloadsInProgress) {
                        if (this.fileName.equals(d.fileName)) {
                            d.downloaded += bc;
                            d.bpsCounter += d.downloaded;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero");
        } catch (IOException e) {
            System.out.println("Hubo un problema de escritura");
        }

        removeFromQueue();
    }
}
