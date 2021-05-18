package com.example.ex_03_ElhananBuff.servlet;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletContext;

/**
 * this servlet on "get" show to the user   an static html that ask an url from the user
 */

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

    /**
     *
     * this function get a request from the user and run on the url a function to check if is ok an do forward to another
     * servlet and if is not ok is do redirection to a static html page
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * this function check if the url that the sever get is a good url or not
     * @param Url the url to check
     * @return a boolean value if the url is ok
     */

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
