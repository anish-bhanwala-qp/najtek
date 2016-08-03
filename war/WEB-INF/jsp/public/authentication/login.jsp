<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Spring Security Example </title>
    </head>
    <body>
    	<core:if test="${not empty param.error}">
	        <div>
	            Invalid username and password.
	        </div>
        </core:if>
        <core:if test="${not empty param.logout}">
	        <div>
	            You have been logged out.
	        </div>
        </core:if>
        <form name="loginForm" action="<core:url value='/j_spring_security_check' />" method="post">
            <div><label> User Name : <input type="text" name="username"/> </label></div>
            <div><label> Password: <input type="password" name="password"/> </label></div>
            <div><input type="submit" value="Sign In"/></div>
            
             <input type="hidden" 
                     name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </body>
</html>