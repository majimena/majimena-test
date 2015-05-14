'use strict';

angular.module('majimenatestApp')
  .directive('sidebar',['$location',function() {
    return {
      templateUrl:'scripts/components/sidebar/sidebar.html',
      restrict: 'E',
      replace: true,
      scope: {
      },
      controller:function($scope){
        $scope.minimum = false;
        $scope.selectedMenu = 'dashboard';
        $scope.collapseVar = 0;
        $scope.multiCollapseVar = 0;

        $scope.toggleSideMenu = function() {
          // FIXME このへんはローカルストレージに設定を保存したほうが良さそう
          $scope.minimum = !$scope.minimum;
          if ($scope.minimum) {
            $('#page-wrapper').addClass('sidebar-collapse');
          } else {
            $('#page-wrapper').removeClass('sidebar-collapse');
          }
        };

        $scope.check = function(x){
          
          if(x==$scope.collapseVar)
            $scope.collapseVar = 0;
          else
            $scope.collapseVar = x;
        };
        
        $scope.multiCheck = function(y){
          
          if(y==$scope.multiCollapseVar)
            $scope.multiCollapseVar = 0;
          else
            $scope.multiCollapseVar = y;
        };
      }
    }
  }]);
