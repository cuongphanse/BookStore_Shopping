<%-- 
    Document   : viewCart
    Created on : Mar 1, 2023, 1:48:50 PM
    Author     : datnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cuongpv.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="cuongpv.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BOOK STORE</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <form action="DispatchServlet">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--//check eps kieu cho nay lai-->
                        <c:forEach var="dto" items="${cart.items}" varStatus="counter">
                            <c:if test="${not empty dto}">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.value.itemName}</td>
                                    <td>${dto.value.quantity}</td>
                                    <td>${dto.value.itemPrice}</td>
                                    <td>
                                        <input type="checkbox" name="checkItem" value="${dto.value.itemName}" />
                                    </td>
                                </tr>
                            </c:if> 
                        </c:forEach>
                        <tr>
                            <td colspan ="4">
                                <a href="DispatchServlet?btnAction=viewBookList">Add more books to your cart</a>
                            </td>
                            <td> <input type="submit" value="RemoveSelectedItems" name="btnAction" /> </td>
                            <input type="submit" value="CheckOut" name="btnAction" />
                            </tr>
                    </tbody>
                </table>
            </form>
        </c:if>
          <h2>No items in your cart</h2>
    </body>
    <!--    <body>
            <h1>Book Store Cart</h1>
    <%
        //1. Customer goes to cart place
        if (session != null) {
            //2. Customer takes his/her shopping cart.
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                //3. Custer gets all items.
                Map<String, ProductDTO> items = cart.getItems();
                if (items != null) {
    %>
    <form action="DispatchServlet">
    <table border="1">
        <thead>
            <tr>
                <th>No.</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
    <%
        int count = 0;
        for (String key : items.keySet()) {
    %> 
    <tr>
        <td><%= ++count%></td>
        <td><%= key%></td>
        <td><%= items.get(key).getQuantity()%></td>
        <td><%= items.get(key).getItemPrice()%></td>
        <td>
            <input type="checkbox" name="checkItem" 
                   value="<%= key%>" />

        </td>
    </tr>

    <%
        } // end traverse Map base on Key
    %>
    <tr>
        <td colspan ="4">
            <a href="DispatchServlet?btnAction=viewBookList">Add more books to your cart</a>
        </td>
        <td> <input type="submit" value="RemoveSelectedItems" name="btnAction" /> </td>
        <input type="submit" value="CheckOut" name="btnAction" />
    </tr>
</tbody>
</table>
</form>     
    <form action="DispatchServlet">
        
        <input type="submit" value="CheckOutServlet" name="btnAction" />
    </form>
    <%
                    //4. Customer review items
                    return;
                }//items has exsited
            }// cart has exsited
        }// session has exsited.
    %>
                    <h2>No items in your cart</h2>
                    <a href="DispatchServlet?btnAction=viewBookList">Back to BookStore</a>
                </body>-->
</html>
