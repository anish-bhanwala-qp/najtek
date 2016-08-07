<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
    	<core:if test="${param.error != null}">
	        <div>
	            <spring:message code="login.invalid.credentials" />
	        </div>
        </core:if>
        <core:if test="${param.logout != null}">
	        <div>
	            You have been logged out.
	        </div>
        </core:if>
        <form name="loginForm" action="<core:url value='/appLogin' />" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
            
             <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </body>
</html>