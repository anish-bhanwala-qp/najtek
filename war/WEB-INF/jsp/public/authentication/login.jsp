<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>

<!doctype html>
<!--[if lt IE 7]>      <html ng-app="NAJTek" class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html ng-app="NAJTek" class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html ng-app="NAJTek" class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html ng-app="NAJTek" class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>NAJTek</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/n/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/n/resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/n/resources/css/signin.css">
    <link rel="stylesheet" href="/n/resources/css/main.css">

    <script src="/n/resources/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>
<body>
    <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->

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
    <div class="container">
        <form class="form-signin" action="<core:url value='/login' />" method="POST">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input name="username" type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">

            <label for="inputPassword" class="sr-only">Password</label>
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
    </div>


<script src="/n/resources/js/vendor/jquery-1.11.2.min.js"></script>
<script src="/n/resources/js/vendor/bootstrap.min.js"></script>

<script>
    (function () {
      'use strict';

      if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
        var msViewportStyle = document.createElement('style');
        msViewportStyle.appendChild(document.createTextNode('@-ms-viewport{width:auto!important}'));
        document.querySelector('head').appendChild(msViewportStyle);
      }
    })();
</script>

</body>
</html>