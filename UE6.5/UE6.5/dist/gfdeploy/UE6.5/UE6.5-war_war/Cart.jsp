<%-- 
    Document   : Cart
    Created on : 11.12.2012, 21:08:19
    Author     : Tobias
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart</h1>
        <form action="FruitShopServlet" method="get"
              <%
                out.println("<p><strong>Available Fruits: </strong></p>");
                out.println("<p>" + request.getAttribute("availableFruits") + "</p>");
                
                out.println("<p><strong>Basket content: </strong></p>");
                out.println("<p>" + request.getAttribute("basketContent") + "</p>");
                
                out.println("<p><strong>Total cost: </strong></p>");
                out.println("<p>" + request.getAttribute("totalCost") + "</p>");
              %>
    </form>
    <form action="FruitShopServlet" method="get"
          <p>Enter name of fruit to add/remove
            <input name="selected_fruit"></p>
            <p><input type="submit" name="button" value="Add fruit"</p>
            <p><input type="submit" name="button" value="Remove fruit"</p>
            <p><input type="submit" name="button" value="Buy all fruits"</p>
            <p><input type="submit" name="button" value="Return to shopselection"</p>
    </form>
    </body>
</html>
