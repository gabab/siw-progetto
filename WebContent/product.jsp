<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<f:view>
    <h1>${userController.product.name}</h1><br>
    Price: ${userController.product.price}<br>
    Code: ${userController.product.code}<br>
    Description: ${userController.product.description}<br>
</f:view>
</body>
</html>
