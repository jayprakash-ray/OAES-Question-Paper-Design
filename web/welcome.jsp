<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
<link href="welcome.css" rel="stylesheet" type="text/css">
<title>OAES</title>
</head>
<body style = "text-align:center">

<div class="jumbotron">
  <h1 class="display-6">Online Assesment and Evaluation System</h1>
  <p class="lead">.</p>
  <hr class="my-4">
</div>
<form>
  <div class="list-group">
    <a href="generatePattern.jsp" class="list-group-item list-group-item-action ">1. Generate Question Paper Pattern</a>
    <a href="showPattern.jsp" class="list-group-item list-group-item-action">2. Show Existing Patterns</a>
    <a href="#" class="list-group-item list-group-item-action">3. Generate Question Paper</a>
    <a href="#" class="list-group-item list-group-item-action">4. Deliver Question Paper to Assessment Centers</a>
    <a href="#" class="list-group-item list-group-item-action">5. Show Question Paper Delivery Status</a>
  </div>
</form>

</body>
</html>