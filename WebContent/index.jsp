<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<f:view>
    <h:form>
        <h:inputText value="#{userController.searchterm}" autocomplete="Search for a product"/>
        <h:commandButton action="#{userController.searchProducts}"/>
        <br><h:commandLink action="#{userController.listProducts}" value="List all products"/>
        <br><h:commandLink action="/insert.jsp" value="Insert a product"/>
    </h:form>
</f:view>
</body>
</html>
