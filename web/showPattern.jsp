<%@ page import="java.util.ArrayList" %>
<%@ page import="oaes.software.architecture.Business.ExamPattern" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<link href="welcome.css" rel="stylesheet" type="text/css">
<title>Exam Patterns</title>
</head>
<body >
<div >
  <form  action="QuestionPaperServlet" method="post">
      <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Pattern name</th>
            <th scope="col">Subject</th>
            <th scope="col">Total marks</th>
          </tr>
        </thead>
        <tbody>
<%--        <%--%>
<%--          List<ExamPattern> examPatterns = (List<ExamPattern>) session.getAttribute("patterns");--%>
<%--        %>--%>

        <%
            ExamPattern pattern = new ExamPattern();
            ArrayList<ExamPattern> examPatterns = pattern.displayPatterns();
            System.out.println("SIze"+examPatterns.size());
            session = request.getSession();
            session.setAttribute("patterns",examPatterns);
//          ArrayList<ExamPattern> examPatterns = (ArrayList<ExamPattern>) session.getAttribute("patterns");
            System.out.println(examPatterns);
          int row = 1;
          for(ExamPattern ep: examPatterns){
          %>
        <tr>
          <th scope="row"><%= row %></th>
          <td><%= ep.getPatternName() %></td>
          <td><%= ep.getSubject() %></td>
          <td><%= ep.getTotalMarks()  %></td>
        </tr>
<% row = row+1 ; }
        %>
        </tbody>
      </table>
      <div>  <label for="patternSelect">Enter Serial Number of Required ExamPattern: </label>
          <input type="text" id=patternSelect name="patternSelect" style="width: 10%;" />
          <input type="submit"value="Generate Question Paper"  name="generate_button" class="btn btn-success mt-5" />
      </div>

  </form>
 </div>
</body>
</html>