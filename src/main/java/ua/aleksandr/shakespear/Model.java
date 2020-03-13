package ua.aleksandr.shakespear;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


public class Model {
    private static final String HTTP = "https://stihi-rus.ru/World/Shekspir/";
    private static final String HTM = ".htm";

    private Map<String, HashMap<String, Integer>> hashMap = new HashMap<>();

    private int sonnetFrom;
    private int sonnetUntil;

    int getSonnetFrom() {
        return sonnetFrom;
    }
    int getSonnetUntil() {
        return sonnetUntil;
    }
    void setSonnetsInRangeFrom(int from) {
        this.sonnetFrom = from;

    }
    void setSonnetsInRangeUntil(int until) {
        this.sonnetUntil = until;
    }

    String createURL(int i) {
        return HTTP + i + HTM;
    }
    String[] parseText(String html) {
        return Jsoup.parse(html).text().split(" ");
    }

    String getTextFromURL(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        try (BufferedReader webReader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "windows-1251"))) {

            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = webReader.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }

     void buildMap(String[] text, String url) {
        for (String key : text) {
            if (hashMap.get(key) == null) {
                hashMap.put(key, new HashMap<>());
                hashMap.get(key).put(url, 1);
            } else {
                Integer frequency = hashMap.get(key).get(url);
                hashMap.get(key).put(url, (frequency == null) ? 1 : ++frequency);
            }
        }
    }

     Map<String, Integer> searchWord(String word) {
        word = word.toLowerCase();
        if (hashMap.get(word) == null) {
            return Collections.emptyMap();
        }
        return hashMap.get(word);
    }
}
