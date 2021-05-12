package com.example.ex_03_ElhananBuff.java;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;


public class CrawlerWeb extends Thread{
    private final String myUrl;
    private final DataBase myDb;

    String userId;
    private static  int MAX_DEPTH ;
    private final HashSet<String> links;
    public CrawlerWeb(String userId ,String url ,int deap,DataBase myDb){

        this.myUrl = url;
        MAX_DEPTH = deap;
        this.myDb = myDb;
        this.userId = userId;
        links = new HashSet<>();

    }
        public void run() {
            System.out.println("Starting Thread for url <" + myUrl + ">");
            myDb.setDone(userId,true);
            getPageLinks(myUrl, 0);
            System.out.println( "End of thread for url <" + myUrl + ">");
            myDb.setDone(userId,false);
        }
        public void getPageLinks(String Url,int Deap) {
            if ((!links.contains(Url) && (Deap < MAX_DEPTH))) {
                System.out.println("Begin crawling <"+ Url + "> at depth <" + Deap + ">");
                try {
                    links.add(Url);
                    Document document = Jsoup.connect(Url).get();
                    Elements linksOnPage = document.select("a[href]");
                    Elements imageOnPage = document.select("img[src]");
                    myDb.setNumOfImg(userId,imageOnPage.size());
                    System.out.println( "Number of images found for < "+ Url +" > is  "+ imageOnPage.size() );
                    Deap++;
                    for (Element page : linksOnPage) {
                        getPageLinks(page.attr("abs:href"), Deap);
                    }
                    } catch (IOException | IllegalArgumentException e)
                    {
                        System.err.println("For '" + Url + "': " + e.getMessage());
                    }

            }
            else
             System.out.println( "End crawling < "+ Url +" > at depth < " + Deap + ">");

        }


}
