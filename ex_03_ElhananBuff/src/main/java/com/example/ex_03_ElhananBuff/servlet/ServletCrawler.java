package com.example.ex_03_ElhananBuff.servlet;

import com.example.ex_03_ElhananBuff.java.CheckUrl;
import com.example.ex_03_ElhananBuff.java.CrawlerWeb;
import com.example.ex_03_ElhananBuff.java.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletContext;

@WebServlet(name = "ServletCrawler", value = "/ServletCrawler")
public class ServletCrawler extends HttpServlet {

    @Override   public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("html/CrawlerPage.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String Url = request.getParameter("url").trim();
        if(checkValidUrl(Url)) {
            request.getRequestDispatcher("CreateThread").include(request, response);

        }
        else
            response.sendRedirect("badUrl.html");
    }



    private boolean checkValidUrl(String Url){
       try  {
           URL url = new URL(Url);
           HttpURLConnection connect = (HttpURLConnection) url.openConnection();
           connect.setRequestMethod("HEAD");
           if(!connect.getContentType().contains("html"))
               return false;
       }
       catch (Exception e){
         return false;
       }
       return true;
    }
}
