'use strict';

angular.module('majimenatestApp')
    .factory('Share', function () {
        var service = {};
        var storage = [];

        service.put = function(key, value) {
            storage.push({key:value})
            console.log(value);
        };

        service.get = function(key) {
            var value = storage[key];
            console.log(value);
            return value;
        };

        return service;
    });
