package com.alvaro.downloader;

import com.alvaro.downloader.controllers.MainController;
import com.alvaro.downloader.models.BinaryDownload;
import com.alvaro.downloader.models.Download;
import com.alvaro.downloader.models.TextDownload;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("SpellCheckingInspection")
public class Main {
    public static ExecutorService downloadService;
    public static List<Download> downloadsInProgress;

    /**
     * La función principal. Se encarga de inicializar el ExecutorService,
     * la lista de descargas y el menú principal.
     *
     * @param args Argumentos de la función
     */
    public static void main(String[] args) {
        final int CORE_COUNT = Runtime.getRuntime().availableProcessors();
        downloadService = Executors.newFixedThreadPool(CORE_COUNT * 2);

        downloadsInProgress = new ArrayList<>();

        URL url;
        try {
            url = new URL("https://images2.alphacoders.com/686/686510.jpg");
            URL finalUrl = url;
            downloadService.execute(() -> {
                var download = new TextDownload("a.txt", finalUrl);
                download.startDownload();
            });
            downloadService.execute(() -> {
                var download1 = new TextDownload("b.txt", finalUrl);
                download1.startDownload();
            });
            downloadService.execute(() -> {
                var download2 = new TextDownload("c.txt", finalUrl);
                download2.startDownload();
            });
            downloadService.execute(() -> {
                var download3 = new TextDownload("d.txt", finalUrl);
                download3.startDownload();
            });
            downloadService.execute(() -> {
                var download4 = new BinaryDownload("e.png", finalUrl);
                download4.startDownload();
            });
            downloadService.execute(() -> {
                var download5 = new BinaryDownload("f.png", finalUrl);
                download5.startDownload();
            });
            downloadService.execute(() -> {
                var download6 = new BinaryDownload("g.png", finalUrl);
                download6.startDownload();
            });
            downloadService.execute(() -> {
                var download7 = new BinaryDownload("h.png", finalUrl);
                download7.startDownload();
            });
            downloadService.execute(() -> {
                var download8 = new BinaryDownload("i.png", finalUrl);
                download8.startDownload();
            });
            downloadService.execute(() -> {
                var download9 = new BinaryDownload("j.png", finalUrl);
                download9.startDownload();
            });
            downloadService.execute(() -> {
                var download10 = new BinaryDownload("k.png", finalUrl);
                download10.startDownload();
            });
            downloadService.execute(() -> {
                var download11 = new BinaryDownload("l.png", finalUrl);
                download11.startDownload();
            });
            downloadService.execute(() -> {
                var download12 = new BinaryDownload("m.png", finalUrl);
                download12.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("n.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("o.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("p.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("q.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("r.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("s.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("t.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("u.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("v.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("w.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("x.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("y.png", finalUrl);
                download13.startDownload();
            });
            downloadService.execute(() -> {
                var download13 = new BinaryDownload("z.png", finalUrl);
                download13.startDownload();
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        MainController mainController = new MainController("Menú principal");
        mainController.start();
    }

    /**
     * Calcula la velocidad de descarga en Bps. Cada segundo recoge lo acumulado
     * en el contador de Download.bpsCounter, que mide los bytes descargados y lo asigna al
     * campo de Download.bps. Después lo sitúa a cero para que vuelva a empezar la cuenta.
     */
    public static void calculateBps() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (downloadsInProgress) {
                    for (Download d : downloadsInProgress) {
                        d.bps = d.bpsCounter;
                        d.bpsCounter = 0;
                    }
                }
            }
        }, 0, 1000);
    }
}
