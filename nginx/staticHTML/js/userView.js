var viewUserId;
var viewUser;
var currentUserID;

var url = window.location.href;
var viewUserId = url.substring(url.lastIndexOf('/') + 1).match(/[0-9]*$/)[0];

app.controller('viewUserInfo', function($scope, userViewService, favurlService) {

    $scope.userviewservice = new userViewService(viewUserId);
    $scope.userviewservice.getUserViewInfo();
    if (viewUserId != TsahayluTeamId) {
        $scope.userviewservice.isMyfriend();

        $scope.favurlService = new favurlService($scope);
        $scope.userNextPage = function() {
            $scope.favurlService.userNextPage(viewUserId);
        }
    }

});

app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();

    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

});

app.factory('userViewService', function($http) {

    var userViewService = function(id) {
        this.viewUser = [];
        this.Contacts = [];
        this.viewUserId = id;
        this.showInviteBtn = false;
    };

    userViewService.prototype.getUserViewInfo = function() {

        $http.get("/api/user/page?id=" + this.viewUserId).then(function(response) {
            var data = response.data;

            this.viewUser = data.user;

            if (this.viewUserId != TsahayluTeamId) {
                var contacts = data.Contacts;
                if (contacts) {
                    for (var i = 0; i < contacts.length; i++) {
                        if (!contacts[i].avatarURL)
                            contacts[i].avatarURL = mysteryManPhotoList[i % 12];
                    }
                    this.Contacts = contacts;
                }
            }
        }.bind(this), null);
    };

    userViewService.prototype.inviteUser = function() {
        $http({
            method: "POST",
            url: "/api/friend/invite?toid=" + this.viewUserId
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {
                tip('You have invite ' + this.viewUser.nickname + ' to be your friend!');
            }
        }.bind(this), null);
    };

    userViewService.prototype.isMyfriend = function() {
        $http({
            method: "POST",
            url: "/api/friend/ismyfriend?toid=" + this.viewUserId
        }).then(function(response) {
            var data = response.data;
            if (data == "false") {
                this.showInviteBtn = true;
            }
        }.bind(this), null);
    };

    return userViewService;

});


$(document).ready(function() {
    currentUserID = isLogin();

    $('#user_zone').hover(
        function() {
            $('#user_menu').stop().slideDown(300);
        },
        function() {
            $('#user_menu').slideUp(300);
        }
    )


    $('#tabs li a.friend_list_node').click(function() {
        $('#tabs li').removeClass();
        $(this).parent('li').addClass('active');
        $('#friends_list').show();
        $('#link_box').hide();
        $(window).unbind("scroll");
    })


    $('#tabs li a.link_list_node').click(function() {
        $('#tabs li').removeClass();
        $(this).parent('li').addClass('active');
        $('#friends_list').hide();
        $('#link_box').show();
        // bindMyScroll();
    })


})