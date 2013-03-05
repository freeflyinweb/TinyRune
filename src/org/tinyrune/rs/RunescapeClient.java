package org.tinyrune.rs;

import org.tinyrune.net.RunescapePageCrawler;
import org.tinyrune.util.Settings;
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
            this.crawler = new RunescapePageCrawler();
            this.crawler.crawl();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(Settings.get("url") + crawler.getGameBase())});
            Class<?> clientClass = classLoader.loadClass("client");
            this.applet = (Applet)clientClass.newInstance();
            this.applet.setStub(this);
            this.applet.setPreferredSize(new Dimension(765, 503));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
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
            return new URL(Settings.get("url"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL(Settings.get("url"));
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
