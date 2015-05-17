<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order no. ${customerHandler.order.id}</title>
</head>
<body>
<f:view>
    <h1>Order no. ${customerHandler.order.id}</h1>
    Opened: ${customerHandler.order.opened} Closed: ${customerHandler.order.closed}
    Processed: ${customerHandler.order.processed}
    <h:form>
        <table>
            <tr>
                <th>Product</th>
                <th>UnitPrice</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>
            <c:forEach var="ol" items="#{customerHandler.order.orderlines}">
                <tr>
                    <td>${ol.product.name}</td>
                    <td>${ol.unitPrice}</td>
                    <td>${ol.quantity}</td>
                    <td>${ol.quantity * ol.unitPrice}</td>
                </tr>
            </c:forEach>
        </table>
    </h:form>
</f:view>
</body>
</html>
