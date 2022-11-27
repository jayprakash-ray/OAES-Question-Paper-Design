package oaes.software.architecture.Business.Servlets;

import oaes.software.architecture.Business.ExamPattern;
import oaes.software.architecture.Business.QuestionPaper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "QuestionPaperServlet", value = "/QuestionPaperServlet")
public class QuestionPaperServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selectedPattern = Integer.parseInt(request.getParameter("patternSelect"));
        PrintWriter out = response.getWriter();
        System.out.println(selectedPattern);
        HttpSession session = request.getSession();
        ArrayList<ExamPattern> examPatterns = (ArrayList<ExamPattern>) session.getAttribute("patterns");
        ExamPattern examPattern = examPatterns.get(selectedPattern-1);
        session.setAttribute("selectedPattern",examPattern);
        out.println("<script type=\"text/javascript\">");
        out.println("location='questionPaper.jsp';");
        out.println("</script>");
//        QuestionPaper qp = new QuestionPaper();
//        System.out.println("selected" + examPattern.getPatternName());
//        try {
//            qp.generateQuestionPaper(examPattern);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        qp.displayQuestionPaper();



    }
}
