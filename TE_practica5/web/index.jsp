<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1{
                font-family: arial;
                font-size: 40px
            }
            h2{
             font-family: arial;
                font-size: 20px   
            }
        </style>
    </head>
    <body>
        <h1 align="center">Blog de salud</h1>
        <a href="Controlador?action=add">Nueva entrada</a>    
        <br>
    <c:forEach var="item" items="${blog}">
       
        <p>
           ${item.fecha}
        </p>
        <p>
           <h2>${item.titulo}</h2> 
        </p>
        <p>
           ${item.contenido}
        </p>
        <a href="Controlador?action=edit&id=${item.id}">Editar</a>
        <a href="Controlador?action=delete&id=${item.id}">Eliminar</a>
        <hr>
    </c:forEach>
    </body>
</html>
