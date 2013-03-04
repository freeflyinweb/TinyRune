package org.tinyrune.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RunescapePageCrawler {

    private final URL url;
    private String gameBase;
    private final HashMap<String, String> parameters;

    public RunescapePageCrawler(URL url) {
        this.url = url;
        this.parameters = new HashMap<String, String>();
    }

    public void crawl() {
        String lines = "";
        try {
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while((line = reader.readLine()) != null) {
                lines += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Pattern regex = Pattern.compile("<param name=([^\\s]+)\\s+value=([^>]*)>", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher matcher = regex.matcher(lines);
            while (matcher.find())
                if (!this.parameters.containsKey(matcher.group(1).replaceAll("\"", "")))
                    this.parameters.put(matcher.group(1).replaceAll("\"", ""), matcher.group(2).replaceAll("\"", ""));
            matcher = Pattern.compile("archive=(.*?\\s)").matcher(lines);
            if(matcher.find())
                this.gameBase = matcher.group(1);
        } catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }
    }

    public HashMap<String, String> getParameters() {
        return this.parameters;
    }

    public String getGameBase() {
        return this.gameBase;
    }
}
