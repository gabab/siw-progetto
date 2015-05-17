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
        <h:commandLink action="#{userController.listProducts}" value="List Products"/>
    </h:form>
</f:view>
</body>
</html>
