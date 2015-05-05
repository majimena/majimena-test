'use strict';

angular.module('majimenatestApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
