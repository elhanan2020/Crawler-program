package com.example.ex_03_ElhananBuff.java;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

/**
 * this class is a class that is inherit from the class thread then it needs to implement the run function that it run
 * when the main thread do start and in the run function this class running an crawler programing
 */

public class CrawlerWeb extends Thread{
    private final String myUrl;
    /**
     * a pointer to  instance of the database
     */
    private final DataBase myDb;

    /**
     * the id number of this thread
     */
    String userId;
    /**
     * a ma value that i want to deap in crawler
     */
    private static  int MAX_DEPTH ;
    /**
     * a container that store all url that i already check
     */
    private final HashSet<String> links;

    /**
     * my constructor
     * @param userId the id of the current request
     * @param url the url to check
     * @param deap the deap until  i want to check
     * @param myDb the database where i want to update the number of found image
     */
    public CrawlerWeb(String userId ,String url ,int deap,DataBase myDb){
        this.myUrl = url;
        MAX_DEPTH = deap;
        this.myDb = myDb;
        this.userId = userId;
        links = new HashSet<>();
    }

    /**
     * a function that is running when i  do "x.start"
     */
        public void run() {
            System.out.println("Starting Thread for url <" + myUrl + ">");
            myDb.setDone(userId,true);
            getPageLinks(myUrl, 0);
            System.out.println( "End of thread for url <" + myUrl + ">");
            myDb.setDone(userId,false);
        }

    /**
     * a crawling function
     * @param Url url to check
     * @param Deap the max deap that i want to check
     */
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
