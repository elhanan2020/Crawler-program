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

        /*ServletContext context = getServletContext();*/

       /* context.setAttribute("DataBase", new DataBase());*/

        /*context.setAttribute("Counter", new int[]{0});*/
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("html/CrawlerPage.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String Url = request.getParameter("url");
        if(checkValidUrl(Url)) {
            int deap = Integer.parseInt(this.getServletContext().getInitParameter("MaxDepth"));
            ServletContext context = getServletContext();
            DataBase db = (DataBase) context.getAttribute("DataBase");

            HttpSession session = request.getSession();
            int[] id = (int[]) context.getAttribute("Counter");
            synchronized (this) {
                session.setAttribute("id", id[0]);
                db.setNewThread(Integer.toString(id[0]), new CheckUrl(Url));
            }
            CrawlerWeb myCraw = new CrawlerWeb(Integer.toString(id[0]), Url, deap, db);

            myCraw.start();
            id[0]++;
            request.getRequestDispatcher("html/CrawlingIsRun.html").include(request, response);
        }
        else
            response.sendRedirect("badUrl.html");
    }



    private boolean checkValidUrl(String Url){
       try  {
           URL url = new URL(Url);
           HttpURLConnection connect = (HttpURLConnection) url.openConnection();
           connect.setRequestMethod("HEAD");
       }
       catch (Exception e){
         return false;
       }
       return true;
    }
}
