'use strict';

angular.module('majimenatestApp')
    .directive('sidebar',['$cookieStore',function($cookieStore) {
        return {
            templateUrl:'scripts/components/navbar/sidebar/sidebar.html',
            restrict: 'E',
            replace: true,
            scope: {
            },
            controller:function($scope) {
//                $scope.minimum = false;
                $scope.selectedMenu = 'dashboard';
                $scope.collapseVar = 0;
                $scope.multiCollapseVar = 0;

                $scope.toggleSideMenu = function() {
                    // FIXME このへんはローカルストレージに設定を保存したほうが良さそう
                    $scope.minimum = !$cookieStore.get('minify');
                    $cookieStore.put('minify', $scope.minimum);
                    if ($scope.minimum) {
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
            }
        };
    }]);
