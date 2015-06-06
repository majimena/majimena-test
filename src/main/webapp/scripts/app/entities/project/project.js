'use strict';

angular.module('majimenatestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('project', {
                parent: 'entity',
                url: '/project',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'majimenatestApp.project.home.title'
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/entities/project/projects.html',
                        controller: 'ProjectController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('project');
                        return $translate.refresh();
                    }]
                }
            })
            .state('projectDetail', {
                parent: 'entity',
                url: '/project/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'majimenatestApp.project.detail.title'
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/entities/project/project-detail.html',
                        controller: 'ProjectDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('project');
                        return $translate.refresh();
                    }]
                }
            })
            .state('projectForm', {
                parent: 'entity',
                url: '/project/:id/form',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'majimenatestApp.project.form.title'
                },
                views: {
                    'content@main': {
                        templateUrl: 'scripts/app/entities/project/project-form.html',
                        controller: 'ProjectFormController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('project');
                        return $translate.refresh();
                    }]
                }
            });
    });
