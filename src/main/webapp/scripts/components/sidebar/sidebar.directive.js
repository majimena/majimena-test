"use strict";
angular.module("majimenatestApp")
    .directive("sidebar", ["$location", function () {
    return {
        templateUrl: "scripts/components/sidebar/sidebar.html",
        restrict: "E",
        replace: !0,
        scope: {},
        controller: function ($scope, $location) {
            $scope.selectedMenu = "dashboard",
            $scope.collapseVar = 0,
            $scope.multiCollapseVar = 0,
            $scope.check = function (x) {
                $scope.collapseVar = x == $scope.collapseVar ? 0 : x
            },
            $scope.multiCheck = function (y) {
                $scope.multiCollapseVar = y == $scope.multiCollapseVar ? 0 : y
            },
            $scope.currentUrl = function () {
                return $location.path()
            }
        }
    }
}]);