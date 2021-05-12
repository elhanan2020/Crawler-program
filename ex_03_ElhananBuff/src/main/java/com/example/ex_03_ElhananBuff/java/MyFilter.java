/*
package com.example.ex_03_ElhananBuff.java;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

*/
/*
In the example below we are using the @WebFilter annotation to
redirect any unauthorized access to the login page
Notice the URL pattern: we catch any URL of the form /account/*
 *//*


@WebFilter(
        urlPatterns = "/ServletCrawler",
        //methods = "post",
        filterName = "urlGood",
        description = "Filter all account transaction URLs")
public class MyFilter implements javax.servlet.Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            URL url = new URL(req.getParameter("url"));
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("HEAD");

            int responseCode = connect.getResponseCode();

           // Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            System.out.println("LoginFilter: Inside filter! Remote IP=" + request.getRemoteAddr());
            // check if permissions.... for example some flag in session scope
            // we pass the request to next filter if any
            chain.doFilter(request, response);

            // here we can write code for handling the response




        // here we can write code for handling the response
    }

    public void destroy() {
    }

}*/
