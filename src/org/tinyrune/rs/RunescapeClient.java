package org.tinyrune.rs;

import org.tinyrune.io.RunescapePageCrawler;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class RunescapeClient implements AppletStub {

    private Applet applet = null;
    private RunescapePageCrawler crawler;

    public RunescapeClient() {
        try {
            this.crawler = new RunescapePageCrawler(new URL("http://oldschool45.runescape.com"));
            this.crawler.crawl();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("http://oldschool45.runescape.com/" + this.crawler.getGameBase())});
            Class<?> clientClass = classLoader.loadClass("client");
            this.applet = (Applet)clientClass.newInstance();
            this.applet.setStub(this);
            this.applet.setPreferredSize(new Dimension(765, 503));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Applet getApplet() {
        return this.applet;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public URL getDocumentBase() {
        try {
            return new URL("http://oldschool45.runescape.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL("http://oldschool45.runescape.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getParameter(String name) {
        return this.crawler.getParameters().get(name);
    }

    @Override
    public AppletContext getAppletContext() {
        return null;
    }

    @Override
    public void appletResize(int width, int height) {

    }
}
