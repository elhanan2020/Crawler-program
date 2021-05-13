/*
package com.example.ex_03_ElhananBuff.java;*/
/*

package com.example.ex_03_ElhananBuff.java;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


*//*

*/
/*
In the example below we are using the @WebFilter annotation to
redirect any unauthorized access to the login page
Notice the URL pattern: we catch any URL of the form /account/*
 *//*
*/
/*



@WebFilter(
        urlPatterns = "/ServletCrawler/*",
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
            if(req.getMethod().equals("POST")) {
                String url = req.getParameter("url");
                if (checkValidUrl(url))
                System.out.println("LoginFilter: Inside filter! Remote IP=" + request.getRemoteAddr());
            else
                res.sendRedirect("badUrl.html");
    }



        // here we can write code for handling the response
    }

    public void destroy() {
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
*//*


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
        urlPatterns = "/ServletCrawler/*",
        filterName = "Account Filter",
        description = "Filter all account transaction URLs")
public class AccountFilter implements javax.servlet.Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            if(req.getMethod().equals("POST")) {
                String url = req.getParameter("url");
                if (checkValidUrl(url))
                    System.out.println("LoginFilter: Inside filter! Remote IP=" + request.getRemoteAddr());
                else
                    request.getRequestDispatcher("badUrl.html");
            }
         chain.doFilter(request, response);
    }

    public void destroy() {
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
}*/
