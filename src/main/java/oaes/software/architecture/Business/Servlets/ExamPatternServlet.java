package oaes.software.architecture.Business.Servlets;

import oaes.software.architecture.Business.ExamPattern;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExamPatternServlet", value = "/ExamPatternServlet")
public class ExamPatternServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            PrintWriter out = response.getWriter();
        response.setContentType("text/html");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                String name = request.getParameter("patternId");
                String subject = request.getParameter("subject");
            int totalMarks = Integer.parseInt(request.getParameter("totalMarks"));
            int mcqCnt = Integer.parseInt(request.getParameter("mcqCnt"));
            int msqCnt = Integer.parseInt(request.getParameter("msqCnt"));
            int desCnt = Integer.parseInt(request.getParameter("desCnt"));
                PrintWriter out = response.getWriter();
                ExamPattern pattern = new ExamPattern();
                pattern.setPatternID(name);
                pattern.setPatternName(name);
                pattern.setSubject(subject);
                pattern.setTotalMarks(totalMarks);
                pattern.setMcqCount(mcqCnt);
                pattern.setMsqCount(msqCnt);
                pattern.setDescCount(desCnt);

                if(pattern.getMcqCount()*2+ pattern.getMsqCount()*3+ pattern.getDescCount()*5 != pattern.getTotalMarks()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Total Marks is not correct ! Please check');");
                    out.println("location='generatePattern.jsp';");
                    out.println("</script>");
                }
                else
                {
                    int row = pattern.submitExamPattern(pattern);
                    if(row == 0 ){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Something Wrong! Please Try Again');");
                        out.println("location='generatePattern.jsp';");
                        out.println("</script>");
                    }
//                    System.out.println("Exam Pattern Created with ID " +pattern.getPatternName());
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Exam Pattern Generated Successfully!')");
                    out.println("location='welcome.jsp';");
                    out.println("</script>");
                }

    }
}
