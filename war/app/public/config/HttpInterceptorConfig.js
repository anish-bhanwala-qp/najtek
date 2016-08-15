angular.module('NAJTek').config([
    '$provide',
    '$httpProvider',
    '$injector',
    function($provide, $httpProvider, $injector) {
        function isAuthenticationDeniedForTemplate(response) {
            return response.status === 401 && /\.html$/.test(response.config.url);
        }

        $provide.factory('myHttpInterceptor', [ '$q', '$rootScope', '$injector',
            function($q, $rootScope, $injector) {

                function showSpinner(config) {
                    if (isHtmlTemplateRequest(config)) {
                        return;
                    }
                    if (typeof $rootScope.deferred != 'undefined'
                                && $rootScope.deferred != null) {
                        $rootScope.deferred = $q.defer();
                        $rootScope.currentPromise = $rootScope.deferred.promise;
                        console.log('Starting Spinner');
                    }
                }

                function hideSpinner(reject) {
                    if (typeof $rootScope.deferred != 'undefined'
                            && $rootScope.deferred != null) {
                        if (reject) {
                            console.log('Rejecting Spinner');
                            $rootScope.deferred.reject();
                        } else {
                            console.log('Resolving Spinner');
                            $rootScope.deferred.resolve();
                        }

                        $rootScope.deferred = null;
                    }
                }

                function isHtmlTemplateRequest(config) {
                    return (endsWith(config.url, '.html'));
                }

                function showAlertMessageIfPresent(response) {
                    if (isHtmlTemplateRequest(response.config)) {
                        return;
                    }
                    var AppConstant = $injector.get('AppConstant');
                    var Notification = $injector.get('Notification');
                    if (response.headers(AppConstant.SUCCESS_HEADER)) {
                        Notification.success(
                        response.headers(AppConstant.SUCCESS_HEADER));
                    } else if (response.headers(AppConstant.ERROR_HEADER)) {
                        Notification.error(
                        response.headers(AppConstant.ERROR_HEADER));
                    }
                }

                function endsWith(str, suffix) {
                    return str.indexOf(suffix, str.length - suffix.length) !== -1;
                }

                return {
                    // optional method
                    'request' : function(config) {
                        // do something on success
                        showSpinner(config);
                        return config;
                    },

                    // optional method
                    'requestError' : function(rejection) {
                        // do something on error
                        return $q.reject(rejection);
                    },

                    // optional method
                    'response' : function(response) {
                        // do something on success
                        hideSpinner(false);
                        showAlertMessageIfPresent(response);
                        return response;
                    },

                    // optional method
                    'responseError' : function(rejection) {
                        // do something on error
                        hideSpinner(true);

                        showAlertMessageIfPresent(rejection);

                        if (isAuthenticationDeniedForTemplate(rejection)) {
                            //injected manually to get around circular dependency problem.
                            var AuthService = $injector.get('Authentication');
                            window.location = '/n/app/public/index.html#/login';
                        }
                        return $q.reject(rejection);
                    }
                };
            } ]);

        $httpProvider.interceptors.push('myHttpInterceptor');

    } ]);