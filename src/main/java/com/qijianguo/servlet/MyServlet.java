package com.qijianguo.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Angus
 * @version 1.0
 * @Title: MyServlet
 * @Description: TODO
 * @date 2019/2/23 13:39
 */
@WebServlet(urlPatterns = "/servlet/get",
        asyncSupported = true )/* 支持异步*/
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 支持异步
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                resp.getWriter().println("Servlet");
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }
}
