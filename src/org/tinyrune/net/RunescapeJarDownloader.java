package org.tinyrune.net;

import org.tinyrune.Client;
import org.tinyrune.canvas.LoaderCanvas;
import org.tinyrune.util.Settings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;

public class RunescapeJarDownloader extends SwingWorker<Void,Void> {

    private Client client;

    public RunescapeJarDownloader(Client client) {
        this.client = client;
    }

    @Override
    protected Void doInBackground() {
        try {
            RunescapePageCrawler crawler = new RunescapePageCrawler();
            crawler.crawl();
            URL url = new URL(Settings.get("url") + crawler.getGameBase());
            URLConnection connection = url.openConnection();
            int total = connection.getContentLength();
            ProgressMonitor progressMonitor = new ProgressMonitor(this.client, "Runescape Jar Download Progess", "", 0, total);
            InputStream reader = url.openStream();
            FileOutputStream writer = new FileOutputStream("runescape.jar");
            byte[] buffer = new byte[2048];
            int bytesRead = 0;
            int totalBytesRead = 0;
            double progess = 0.0;
            while((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0 , bytesRead);
                totalBytesRead += bytesRead;
                progressMonitor.setProgress(totalBytesRead);
            }

            JOptionPane.showMessageDialog(client, "Runescape Jar done downloading!");
            client.runRunescape();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
