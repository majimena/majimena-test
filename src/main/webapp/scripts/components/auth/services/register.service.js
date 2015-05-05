'use strict';

angular.module('majimenatestApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


