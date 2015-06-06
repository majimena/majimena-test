'use strict';

angular.module('majimenatestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('configuration', {
                parent: 'admin',
                url: '/configuration',
                data: {
                    roles: ['ROLE_ADMIN'],
                    pageTitle: 'configuration.title'
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/admin/configuration/configuration.html',
                        controller: 'ConfigurationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('configuration');
                        return $translate.refresh();
                    }]
                }
            });
    });
