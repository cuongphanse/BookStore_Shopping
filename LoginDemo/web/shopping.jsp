<%-- 
    Document   : shopping
    Created on : Mar 4, 2023, 10:43:35 PM
    Author     : datnt
--%>

<%@page import="cuongpv.product.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="cuongpv.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>
        <form action="DispatchServlet">
            Choose book 
            <select  name="ddlBook">
                <c:set var="result" value="${requestScope.PRODUCT_LIST}"/>
                <c:if test="${not empty result}">
                    <c:forEach var="dto" items="${result}" varStatus="counter" >
                        <option>
                            ${dto.itemName}
                        </option>
                    </c:forEach>     
                </c:if>
                <input type="text" name="numberItem" value="" placeholder="Nhập số lượng bạn muốn mua" />
                <input type="submit" value="AddToCart" name="btnAction" />          
                <input type="submit" value="ViewCart" name="btnAction" />
            </select>
        </form>
    </body>
</html>
