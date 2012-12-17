<%-- 
    Document   : index
    Created on : 09.12.2012, 23:21:23
    Author     : Tobias
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>Shopselection</h1>
        <form action="FruitShopServlet" method="post">
            <div id="Shopselection">
                <p>Enter the name of the shop you want to choose
                    <input name="selected_shop"></p>
                <p><input type="submit" name="button" value="Send"</p>
        </form>
    </div>
    <form action="FruitShopServlet" method="post">
        <div id="AvailableShops">
            <p>List of available Shops</p>
            <%
                if (request != null) {
                    try{
                        Enumeration<String> shopNames = request.getAttributeNames();
                        
                        int i = 0;
                        Object shop = request.getAttribute(i + "shop");
                        
                        while (shop != null){
                            out.println("<p>" + shop.toString() + "</p>");
                            i++;
                            shop = request.getAttribute(i + "shop");
                        }
                        
                    }catch(Exception e){
                        out.println("<p>No shops, try refreshing</p>");
                    }
                }
            %>
            <p><input type="submit" name="button" value="Refresh shoplist"</p>
        </div>
    </form>
</body>
</html>
