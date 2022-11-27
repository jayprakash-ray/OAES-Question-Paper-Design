<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<link href="welcome.css" rel="stylesheet" type="text/css">
<title>Welcome to Exam Portal</title>
</head>
<body >
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Exam Portal</a>
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
<h3>
  Enter Exam Pattern Details:
</h3>
<div>
<form  action="ExamPatternServlet" method="post">
  <div class="form-group">
    <label for="patternId">Enter exam pattern Name</label>
    <input type="text" class="form-control" id="patternId" name="patternId" placeholder="">
    </div>
  <div class="form-group">
    <label for="subject">Enter Course Name</label>
    <input type="text" class="form-control" name="subject" id="subject" placeholder="">
  </div>
    <div class="form-group">
    <label for="totalMarks">Enter total marks</label>
    <input type="number" class="form-control" name="totalMarks" id="totalMarks" placeholder="">
    </div>
    <div class="form-group">
    <label for="mcqCnt">No of mcqs</label>
    <input type="number" class="form-control" name="mcqCnt" id="mcqCnt" placeholder="">
    </div>
     <div class="form-group">
    <label for="msqCnt">No of msqs</label>
    <input type="number" class="form-control" name="msqCnt" id="msqCnt" placeholder="">
    </div>
     <div class="form-group">
    <label for="desCnt">No of Descriptive</label>
    <input type="number" class="form-control" name="desCnt" id="desCnt" placeholder="">
    </div>
    <button type="submit" id= "submitPattern"  class="btn btn-success mt-5">Submit</button>
</form>
</div>
<%--onclick="alert('pattern Created Successfully')"--%>

</body>
</html>