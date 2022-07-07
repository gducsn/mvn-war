
<%@page import="utility.Crud"%>
<%@page import="model.Ingredienti"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<link rel="icon" type="image/x-icon"
	href="https://img.icons8.com/ios/344/contacts.png">
<%
String namein = (String) session.getAttribute("userlogin");
String text = (String) request.getAttribute("text");
Crud daoCrud = new Crud();
@SuppressWarnings("unchecked")
List<Ingredienti> lista = (List<Ingredienti>) session.getAttribute("ingredienti");
%>
<title>Welcome <%=namein%></title>
</head>
<body>


	<div>
		<form action="Insert" method="post">
			<div class="panini-tipo d-flex justify-content-center  m-5 p-5">

				<div class="mr-3">
					<h5 class="lead">scegli il tipo di panino che vuoi</h5>
					<div class="form-check">
						<input name="radio" value="integrale" class="form-check-input"
							type="radio" name="flexRadioDefault" id="flexRadioDefault1">
						<label class="form-check-label" for="flexRadioDefault1">
							integrale </label>
					</div>
					<div class="form-check">
						<input name="radio" value="cerali" class="form-check-input"
							type="radio" name="flexRadioDefault" id="flexRadioDefault2"
							checked> <label class="form-check-label"
							for="flexRadioDefault2"> cereali </label>
					</div>

					<div class="form-check">
						<input name="radio" value="comune" class="form-check-input"
							type="radio" name="flexRadioDefault" id="flexRadioDefault2"
							checked> <label class="form-check-label"
							for="flexRadioDefault2"> comune </label>
					</div>
					<div class="form-check">
						<input name="radio" value="condito" class="form-check-input"
							type="radio" name="flexRadioDefault" id="flexRadioDefault2">
						<label class="form-check-label" for="flexRadioDefault2">
							condito </label>
					</div>
				</div>

				<div class="ml-3">
					<h5 class="lead">scegli gli ingredienti</h5>
					<div class="form-check">
						<input name="check" value="salame" class="form-check-input"
							type="checkbox" value="" id="flexCheckDefault"> <label
							class="form-check-label" for="flexCheckDefault"> salame </label>
					</div>
					<div class="form-check">
						<input name="check" value="mortadella" class="form-check-input"
							type="checkbox" value="" id="flexCheckChecked"> <label
							class="form-check-label" for="flexCheckChecked">
							mortadella </label>
					</div>
					<div class="form-check">
						<input name="check" value="cotto" class="form-check-input"
							type="checkbox" value="" id="flexCheckChecked"> <label
							class="form-check-label" for="flexCheckChecked"> cotto</label>
					</div>
					<div class="form-check">
						<input name="check" value="crudo" class="form-check-input"
							type="checkbox" value="" id="flexCheckChecked"> <label
							class="form-check-label" for="flexCheckChecked"> crudo</label>
					</div>
				</div>
			</div>
			<div class=" p-3 d-flex justify-content-center">
				<div class="w-50">
					<div class="form-row">
						<div class="col">
							<input name="texting" type="text" class="form-control"
								placeholder="Il nome del tuo panino">
						</div>
						<div class="col">
							<button type="submit" class="btn btn-dark w-100">Invia!</button>
						</div>
					</div>
				</div>
			</div>

			<div class="d-flex justify-content-center p-3">
				<c:if test="${ text != null}">
					<h6 class="lead">
						Il nome del tuo panino è
						<%=" <b>" + text + " </b>"%></h6>
				</c:if>
			</div>

		<div
				class=" p-3 d-flex justify-content-center flex-column align-items-center">
				<h5 class="p-2 lead">i tuoi panini</h5>
				<ul class="list-group w-50">
					<li class="list-group-item"><%=lista.get(1).getTipo()%></li>
					<li class="list-group-item"><%=lista.get(2).getTipo()%></li>
					<li class="list-group-item"><%=lista.get(3).getTipo()%></li>
				</ul>

			</div>
			
		
		</form>
	</div>



</body>
</html>