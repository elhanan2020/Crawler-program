package com.example.ex_03_ElhananBuff.servlet;

import com.example.ex_03_ElhananBuff.java.DataBase;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ShowResultServlet", value = "/ShowResultServlet")
public class ShowResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session=request.getSession();
        if((session.getAttribute("id"))==null)
            request.getRequestDispatcher("html/CrawlerPage.html").forward(request, response);
        int id = (int)session.getAttribute("id");
        ServletContext context = getServletContext();
        DataBase db =  (DataBase) context.getAttribute("DataBase");
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "    <h2> Crawling  "+ db.url(Integer.toString(id))+"</h2>\n" +
                "<p>Found "+  db.getNumOfImg(Integer.toString(id)) +"   images !</p>\n" +
                db.isDone(Integer.toString(id)) +
                "    <a href=\"html/CrawlerPage.html\">back to the </a>\n" +
                "</body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
