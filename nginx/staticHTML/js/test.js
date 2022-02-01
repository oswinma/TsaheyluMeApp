var app = angular.module('myApp', ['infinite-scroll']);

app.controller('link_box', function($scope, tsahaylu) {
  $scope.tsahaylu = new tsahaylu();
});

app.factory('tsahaylu', function($http) {
  var tsahaylu = function() {
    this.FavURLShows = [];
    this.busy = false;
    this.sc = '';
  };

  tsahaylu.prototype.nextPage = function() {
    if (this.busy) return;
    this.busy = true;

	  $http.get("/favurl/new?startCursor="+this.sc).success(function(data) {
	    var FavURLShows = data.FavURLShows;
	    for (var i = 0; i < FavURLShows.length; i++) {
	      this.FavURLShows.push(FavURLShows[i]);
	    }
	    this.sc = data.startCursor;
	    this.busy = false;
	  }.bind(this));
  }; 
  
  return tsahaylu;
});

app.directive('errSrc', function() {
	  return {
	    link: function(scope, element, attrs) {

	      scope.$watch(element, function () {
	        if (!element.attr('src')) {
	          element.attr('src', attrs.errSrc);    
	        }
	      });

	      element.bind('error', function() {
	        element.attr('src', attrs.errSrc);
	      });
	    }
	  }
	});
