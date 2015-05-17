<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head>
    <title>List of all products</title>
</head>
<body>
<f:view>
    <h1>Products</h1>
    <h:form>
        <table>

            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
            </tr>
            <c:forEach var="p" items="#{controller.products}">
                <tr>
                    <td><h:commandLink action="#{controller.findProduct(p.id)}" value="#{p.name}">
                    </h:commandLink></td>
                    <td>${p.price}</td>
                    <td>${p.description}</td>
                </tr>
            </c:forEach>
        </table>
    </h:form>
</f:view>
</body>
</html>
