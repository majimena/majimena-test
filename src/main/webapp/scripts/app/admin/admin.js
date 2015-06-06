'use strict';

angular.module('majimenatestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('admin', {
                abstract: true,
                parent: 'main'
            });
    });
