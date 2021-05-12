package com.example.ex_03_ElhananBuff.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class WebCrawlerWithDepth {
    private static final int MAX_DEPTH = 2;
    private final HashSet<String> links;
    private int num =0;
    public WebCrawlerWithDepth() {
        links = new HashSet<>();
    }

    public void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                Elements imageOnPage = document.select("img[src]");
                num += imageOnPage.size();
               // Elements imageOnPages = document.select("img[src$=.jpg]");
                System.out.println(num);
                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        WebCrawlerWithDepth myCraw = new WebCrawlerWithDepth();

                myCraw.getPageLinks("https://www.ynet.co.il/news", 0);

    }
}