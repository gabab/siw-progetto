<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Orders</title>
</head>
<body>
<f:view>
    <h1>My Orders</h1>
    <h:form>
        <table>
            <tr>
                <th>Opened</th>
                <th>Closed</th>
                <th></th>
            </tr>
            <c:forEach var="o" items="#{customerHandler.orders}">
                <tr>
                    order
                    <td>${o.opened}</td>
                    <td>${o.closed}</td>
                    <td>${o.processed}</td>
                    <td><h:commandLink action="#{adminHandler.viewOrderDetails}" value="Details"></h:commandLink></td>
                    <td><h:commandLink action="#{adminHandler.processOrder(o.id)}" value="Process"></h:commandLink></td>
                </tr>
            </c:forEach>
        </table>
    </h:form>
</f:view>
</body>
</html>
