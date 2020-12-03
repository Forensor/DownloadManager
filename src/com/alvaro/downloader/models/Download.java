package com.alvaro.downloader.models;
@SuppressWarnings("SpellCheckingInspection")
public class Download {
    public final String url;
    public final String fileName;
    public int downloaded;
    public final long size;
    public double bps;
    public double bpsCounter;

    /**
     * Constructor de Download. Download funciona como una BBDD. En lugar de almacenar
     * los objetos de descarga en una lista, se almacenan sus datos separados por
     * instancias de esta clase.
     *
     * @param url        La URL de donde se descarga
     * @param fileName   El nombre del fichero donde se volcará la descarga
     * @param downloaded Los bytes que lleva descargados
     * @param size       El tamaño total en bytes del archivo a descargar
     */
    public Download(String url, String fileName, int downloaded, long size) {
        this.url = url;
        this.fileName = fileName;
        this.downloaded = downloaded;
        this.size = size;
        this.bps = 0;
        this.bpsCounter = 0;
    }
}
