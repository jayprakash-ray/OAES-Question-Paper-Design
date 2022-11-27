<%@ page import="oaes.software.architecture.Business.ExamPattern" %>
<%@ page import="oaes.software.architecture.Business.Questions" %>
<%@ page import="oaes.software.architecture.Business.QuestionPaper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="oaes.software.architecture.Business.QuestionIterator" %>
<%@ page import="oaes.software.architecture.Business.QuestionTypes.Mcq" %>
<%@ page import="oaes.software.architecture.Business.QuestionTypes.Msq" %>
<%@ page import="oaes.software.architecture.Business.QuestionTypes.Desc" %><%--
  Created by IntelliJ IDEA.
  User: rjay3
  Date: 27-11-2022
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link href="QuestionPaper.css" rel="stylesheet" type="text/css">
<head>
    <title>Question Paper</title>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse navbar-nav ml-auto" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="welcome.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="welcome.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>
</head>
<body>


<div>
    <%
        ExamPattern examPattern = (ExamPattern) session.getAttribute("selectedPattern");
        QuestionPaper qp = new QuestionPaper();
        qp.generateQuestionPaper(examPattern);
        int index = 1;
        int qindex = 0; %>
         <hr>
    <div>
        <h2><%=examPattern.getPatternName()%></h2>
        <h3>Course Name : <%=examPattern.getSubject()%> &nbsp&nbsp&nbsp        Set : A         &nbsp&nbsp&nbsp             Total Marks : <%=examPattern.getTotalMarks()%> </h3>
    </div>
        <hr>
    <%
        if(examPattern.getMcqCount()>0)
        {
    %>
           <h3> Section : Multiple Choice Questions [2 Marks Each] </h3>
            <%
                ArrayList<Questions> mcqs = qp.getQuestions().get(qindex++);

                //Iterator Design Pattern
                QuestionIterator mcqIterator = Mcq.createIterator(mcqs);
                while(mcqIterator.hasNext()){
                    Mcq mcq = (Mcq) mcqIterator.next();
            %>
        <div style = "margin-left: 15px; border:1px solid black; ">
            <h4> <%=String.valueOf(index)%>. <%=mcq.getQuestionText()%></h4>
            <h4> <%=mcq.getOptions()%> </h4>
        </div>


<%--                System.out.println(mcq.getOptions());--%>
<%--                System.out.println("");--%>
              <%
                index++;
            }

} %>

<%--    <hr>--%>
    <%
        if(examPattern.getMsqCount()>0)
        {
    %>
    <h3> Section : Multiple Select Questions  [3 Marks Each] </h3>
    <%
        ArrayList<Questions> msqs = qp.getQuestions().get(qindex++);

        //Iterator Design Pattern
        QuestionIterator msqIterator = Msq.createIterator(msqs);
        while(msqIterator.hasNext()){
            Msq msq = (Msq) msqIterator.next();
    %>
    <div style = "margin-left: 15px; border:1px solid black; ">
        <h4> <%=String.valueOf(index)%>. <%=msq.getQuestionText()%></h4>
        <h4> <%=msq.getOptions()%> </h4>
    </div>


    <%--                System.out.println(mcq.getOptions());--%>
    <%--                System.out.println("");--%>
    <%
                index++;
            }

        } %>

    <%
        if(examPattern.getDescCount()>0)
        {
    %>
    <h3> Section : Descriptive Questions [5 Marks Each] </h3>
    <%
        ArrayList<Questions> desc = qp.getQuestions().get(qindex++);

        //Iterator Design Pattern
        QuestionIterator descIterator = Msq.createIterator(desc);
        while(descIterator.hasNext()){
            Desc desc1 = (Desc) descIterator.next();
    %>
    <div style = "margin-left: 15px; border:1px solid black; ">
        <h4> <%=String.valueOf(index)%>. <%=desc1.getQuestionText()%></h4>
    </div>


    <%--                System.out.println(mcq.getOptions());--%>
    <%--                System.out.println("");--%>
    <%
                index++;
            }

        } %>
<%--        System.out.println("-------------------------------------------------------------------");--%>
<%--        if(this.examPattern.getMsqCount()>0)--%>
<%--        {--%>
<%--            System.out.println("Section : Multiple Select Questions  [3 Marks Each]");--%>
<%--            ArrayList<Questions> msqs = this.questions.get(qindex++);--%>
<%--            QuestionIterator msqIterator = Msq.createIterator(msqs);--%>
<%--            while(msqIterator.hasNext()){--%>
<%--                Msq msq = (Msq) msqIterator.next();--%>
<%--                System.out.println(String.valueOf(index)+"."+msq.getQuestionText());--%>
<%--                System.out.println(msq.getOptions());--%>
<%--                System.out.println("");--%>
<%--                index++;--%>
<%--            }--%>

<%--        }--%>

<%--        System.out.println("-------------------------------------------------------------------");--%>
<%--        if(this.examPattern.getDescCount()>0)--%>
<%--        {--%>
<%--            System.out.println("Section : Descriptive Questions [5 Marks Each]");--%>
<%--            ArrayList<Questions> desc = this.questions.get(qindex++);--%>
<%--            QuestionIterator descIterator = Desc.createIterator(desc);--%>
<%--            while(descIterator.hasNext()){--%>
<%--                Desc des = (Desc) descIterator.next();--%>
<%--                System.out.println(String.valueOf(index)+"."+des.getQuestionText());--%>
<%--                System.out.println("");--%>
<%--                index++;--%>
<%--            }--%>

<%--        }--%>
<%--        System.out.println("--------------------End Of Question Paper----------------------------");--%>
<%--        }--%>

<%--    %>--%>
</div>
</body>
</html>
