<%-- 
    Document   : search
    Created on : Feb 18, 2023, 1:16:17 PM
    Author     : datnt
--%>

<%--<%@page import="java.util.List"%>
<%@page import="datnt.registration.RegistrationDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--add libra nhung qune import taglid-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!--quen add libra nhugn import taglid-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="userSession" value="${sessionScope.USERNAME}"/>
        <c:if test="${empty userSession}">
            <jsp:forward page="login.html"></jsp:forward>
        </c:if>
        <font color="red">
        Welcome, ${sessionScope.USERNAME.lastName}
        </font>
        <h1> Search Page</h1>

        <form action="DispatchServlet">
            Search Value <input type ="text" name="txtSearchValue"
                                value="${param.txtSearchValue}"/><br/>
            <input type="submit" value ="Search" name="btnAction"/>
        </form> <br/>
        <!--loi syntax tag-->
        <c:set var="searchValue" value="${param.txtSearchValue}" />

        <c:if test="${not empty searchValue}">
            <c:set var="searchResult" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty searchResult}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!--dinh nghia trong iteam theu EL items="searchReuslt"--> 
                        <c:forEach var="dto" items="${searchResult}" varStatus="counter">
                        <form action="DispatchServlet" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.userName}
                                    <input type="hidden" name="txtUserName" value="${dto.userName}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.lastName}
                                </td>
                                <td>
                                    <input type="checkbox" name="checkAdmin" value="ON"
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>

                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btnAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.userName}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}"/>
                                    <input type="submit" value="Update" name="btnAction" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty searchResult}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
    </c:if>

    <form action="DispatchServlet" method="POST">
        <input type="submit" value="Logout" name="btnAction" />
    </form>




</body>
</html>

<%--        <%
            String userName = null;
            //session has exsited.
            // If browser doesnot able Cookie we need use UrlEncode.
            if (session.getAttribute("USERNAME") == null) {
                response.sendRedirect("login.html");

            } else {
//          userName = (String) request.getAttribute("USERNAME");
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    Cookie lastCookie = cookies[cookies.length - 1];
                    userName = lastCookie.getName();
                    for (Cookie cookie : cookies) {
        %> 
        <h2>Name: <%= cookie.getName()%></h2>                    
        <h2>Value <%= cookie.getValue()%></h2>
        <%
            }
        %> 
       
        <font color="red" >
        Welcome <%= userName%>     
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="Logout" name="btnAction" />
        </form>
        <%
                } //end of Cookie has exited.
            }
        %>
        </font>
 <h2><%= session.getAttribute("USERNAME") %></h2>

        <h1> Search </h1>
        <form action = "DispatchServlet">
            <input class="inputSearch" type="text" name="txtSearchValue" 
                   placeholder="Nhập giá trị muốn search"
                   value="<%
                       if (request.getAttribute("txtLastSearchValue") != null) {
                           String txtSearchValue = (String) request.getAttribute("txtLastSearchValue");
                   %><%=txtSearchValue%><%
                               } // check the searchValue is Existed.
                   %>" /> </br>
            <input type="submit" value="Search" name="btnAction" />
        </form>
        <%
            String searchValue = request.getParameter("txtSearchValue");

            //Trong servlet kbh check null
            // Chi check null khi ben ngoai form ngoai view
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>Last name</th>
                    <th>Rule</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
//                                    check ? va dau & neu co %20 thi check co khoang trang
                        String urlRewriting = "DispatchServlet"
                                + "?btnAction=Delete"
                                + "&pk=" + dto.getUserName()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchServlet" method="POST">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUserName()%>
                        <input type="hidden" name="txtUserName"
                               value="<%= dto.getUserName()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword"
                               value="<%= dto.getPassword()%>" />
                    </td>
                    <td> 
                        <%= dto.getLastName()%>
                    </td>
                    <td>
                        <input type="checkbox" name="checkAdmin" value="ON" 
                               <%
                                   if (dto.isRole()) {
                               %>
                               checked="checked"
                               <%
                                   }//end role is admin
%>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting%>">Detele</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btnAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>

                </tr>     
            </form>    
            <%

                }//end traverse DTO
            %>
        </tbody>
    </table>


    <%
    } else {
        //Tach code java va code html 
    %> 

    <h2> No record is matched </h2>

    <%
            }// end no record is existed
        } // end search Value must have valid value.

    %>
--%>

