<%-- 
    Document   : register
    Created on : Mar 15, 2023, 12:41:56 PM
    Author     : datnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRATRON</title>
    </head>
    <body>
        <div>Create new Account</div>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            <!--check empty erorr truoc-->
            <c:if test="${not empty errors.userNameLengthError}">
                <font color="red">
                    ${errors.userNameLengthError}
                    </font> </br>
            </c:if>
            Username* <input type="text" name="txtUserName" 
                             value="${param.txtUserName}" size="20" /> </br>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                    ${errors.passwordLengthError}
                    </font> </br>
            </c:if> 
            Password* <input type="password" name="txtPassword" value="" size="30" /> </br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                    ${errors.confirmNotMatched}
                    </font> </br>
            </c:if>
            Conform* <input type="password" name="txtPasswordConfirm" value="" size="30" /> </br>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                    ${errors.fullNameLengthError}
                    </font> </br>
            </c:if>
            Full Name* <input type="text" name="txtFullName" 
                              value="${param.txtFullName}" size="50" /> </br>
            <input type="submit" value="CreateNewAccount" name="btnAction" />  
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
