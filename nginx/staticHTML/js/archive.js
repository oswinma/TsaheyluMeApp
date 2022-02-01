app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();

    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

});


app.controller('link_box', function($scope, $http, favurlService) {

    var searching = false;
    var searchWord = '';
    $scope.searchClearShow = false;

    $scope.favurlService = new favurlService($scope);

    $scope.searchFavURL = function() {
        searchWord = $scope.searchWord;

        if (searchWord != '') {
            $scope.favurlService.FavURLShows = [];
            $scope.favurlService.searchstop = false;
            $scope.favurlService.noSearchResult = false;
            $scope.favurlService.searchsc = '';
            searching = true;
            $scope.favurlService.search(searchWord);
        }

    }

    $scope.searchWordChange = function() {
        searchWord = $scope.searchWord;

        if (searchWord != '') {
            $scope.searchClearShow = true;
        }

    }

    $scope.clearSearchResult = function() {
        $scope.favurlService.FavURLShows = [];
        $scope.favurlService.sc = '';
        $scope.favurlService.searchsc = '';
        $scope.favurlService.stop = false;
        $scope.favurlService.searchstop = false;
        $scope.favurlService.noSearchResult = false;
        searching = false;
        searchWord = '';
        $scope.favurlService.NextPage();
        $scope.searchWord = '';
        $('#search_clear').fadeOut(100);
    }

    $scope.infiniteScroll = function(type) {

        if (searching) {
            $scope.favurlService.search(searchWord);
        } else
            $scope.favurlService.NextPage(type);

    }

});


$(document).ready(function() {

    isLogin();

    $('#user_zone').hover(
        function() {
            $('#user_menu').stop().slideDown(300);
        },
        function() {
            $('#user_menu').slideUp(300);
        }
    )

    $('#link_box').on(
        'mouseleave', 'li',
        function() {
            $(this).find('.control_box').find('.del,.unfav,.unread').fadeOut(100);
        }
    )

    $('#link_box').on(
        'mouseenter', 'li',
        function() {
            $(this).find('.control_box').find('.del,.unfav,.unread').fadeIn(100);
        }
    )


});