'use strict';

angular.module('majimenatestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('entity', {
                abstract: true,
                parent: 'main'
            });
    });
