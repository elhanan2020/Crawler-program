package com.example.ex_03_ElhananBuff.servlet;

import com.example.ex_03_ElhananBuff.java.CheckUrl;
import com.example.ex_03_ElhananBuff.java.CrawlerWeb;
import com.example.ex_03_ElhananBuff.java.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateThread", value = "/CreateThread")
public class CreateThread extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Url = request.getParameter("url").trim();
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
}
