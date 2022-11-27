package oaes.software.architecture.Business.Servlets;

import oaes.software.architecture.Business.ExamPattern;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowPatternsServlet", value = "/ShowPatternsServlet")
public class ShowPatternsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        ExamPattern pattern = new ExamPattern();
        ArrayList<ExamPattern> examPatterns;
        examPatterns = pattern.displayPatterns();
        System.out.println("SIze"+examPatterns.size());
        HttpSession session = request.getSession();
        session.setAttribute("patterns",examPatterns);
        System.out.println(examPatterns.size()+ " SIZE");
        if(examPatterns.size() == 0) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('No Exam Pattern Available !');");
            out.println("location='welcome.jsp';");
            out.println("</script>");
        }
        response.sendRedirect("showPatterns.jsp");
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
}
