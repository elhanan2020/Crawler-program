package com.example.ex_03_ElhananBuff.servlet;

import com.example.ex_03_ElhananBuff.java.CheckUrl;
import com.example.ex_03_ElhananBuff.java.CrawlerWeb;
import com.example.ex_03_ElhananBuff.java.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * this servlet get request from the first servlet "servletCrawler"  to caring to create for any request an thread
 *
 */
@WebServlet(name = "CreateThread", value = "/CreateThread")
public class CreateThread extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.sendRedirect("html/CrawlerPage.html");
    }

    /**
     * this function get an request an create thread and run it to crawler
     * @throws ServletException an ServletException
     * @throws IOException an IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Url = request.getParameter("url").trim();
        int deap = Integer.parseInt(this.getServletContext().getInitParameter("MaxDepth"));
        ServletContext context = getServletContext();
        DataBase db = (DataBase) context.getAttribute("DataBase");
        HttpSession session = request.getSession();
        int[] id = getId(context);
        synchronized (this) {
            session.setAttribute("id", id[0]);
            db.setNewThread(Integer.toString(id[0]), new CheckUrl(Url));
        }
        CrawlerWeb myCraw = new CrawlerWeb(Integer.toString(id[0]), Url, deap, db);
        myCraw.start();
        increaseId(context);
        request.getRequestDispatcher("html/CrawlingIsRun.html").include(request, response);
    }

    /**
     * this function get the value synchronized so that two browse dont can to work on the same variable
     * @param context servlet context
     * @return the  curent value of the counter
     */
    private synchronized  int[] getId(ServletContext context){ return  (int[]) context.getAttribute("Counter");}

    /**
     * this function increase the counter and is securised it with synchronized attribute
     * @param context servlet context
     */
    private synchronized  void increaseId(ServletContext context){ int[] id=  (int[]) context.getAttribute("Counter");
    id[0]++;
    }
}
