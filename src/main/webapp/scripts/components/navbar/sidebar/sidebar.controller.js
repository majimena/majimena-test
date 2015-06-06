'use strict';

angular.module('majimenatestApp')
    .controller('SidebarController', function ($scope, $cookieStore, $location, $state, Auth, Principal) {
//                $scope.minimum = false;
        $scope.loadSideMenu = function() {
        	var toggle = $cookieStore.get('minify');
        	$scope.toggleSideMenu(toggle);
        	$scope.minimum = toggle;
        };
                $scope.selectedMenu = 'dashboard';
                $scope.collapseVar = 0;
                $scope.multiCollapseVar = 0;

                $scope.toggleSideMenu = function(toggle) {
                	console.log(toggle);
                    // FIXME このへんはローカルストレージに設定を保存したほうが良さそう
                    // var requestMinifySideMenu = !$cookieStore.get('minify');
                    // $scope.minimum = requestMinifySideMenu;
                    $cookieStore.put('minify', toggle);
                    $scope.minimum = toggle;
                    if (toggle) {
                        $('#page-wrapper').addClass('sidebar-collapse');
                        $('#side-menu li ul').removeClass('nav-second-level');
                    } else {
                        $('#page-wrapper').removeClass('sidebar-collapse');
                        $('#side-menu li ul').addClass('nav-second-level');
                    }
                };

                $scope.check = function(x) {
                    if (x === $scope.collapseVar) {
                        $scope.collapseVar = 0;
                    } else {
                        $scope.collapseVar = x;
                    }
                };

                $scope.multiCheck = function(y) {
                    if (y === $scope.multiCollapseVar) {
                        $scope.multiCollapseVar = 0;
                    } else {
                        $scope.multiCollapseVar = y;
                    }
                };
    });
