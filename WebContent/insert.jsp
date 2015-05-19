<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<html>
<head>
    <title>Insert new product</title>
</head>
<body>
<f:view>
    <h:form>
        <label>Name:
            <h:inputText value="#{userController.name}"
                         required="true"
                         requiredMessage="Name is mandatory" id="name"/></label><h:message for="name"/>
        <br><br>
        <label>Price:
            <h:inputText value="#{userController.price}"
                         required="true"
                         requiredMessage="Price is mandatory"
                         converterMessage="Price must be a number" id="price">
                <f:validateDoubleRange minimum="0.01"/></h:inputText>
            <h:message for="price"/>
        </label>
        <br><br>
        <label>Code:
            <h:inputText value="#{userController.code}"
                         required="true"
                         requiredMessage="Code is mandatory"
                         validatorMessage="Code: min 6 max 10 characters" id="code">
                <f:validateLength minimum="6" maximum="10"/>
            </h:inputText></label>
        <h:message for="code" styleClass="error"/><br><br>
        <label>Description:
            <h:inputTextarea rows="6" cols="30" value="#{userController.description}" required="false"/>
        </label><br><br>
        <h:commandButton value="Submit" action="#{userController.createProduct}"/>
    </h:form>
</f:view>
</body>
</html>
