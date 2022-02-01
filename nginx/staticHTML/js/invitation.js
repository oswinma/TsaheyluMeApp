app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();
    // $scope.userservice.getUserInfo();

    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

});



app.controller("invitation_box", function($scope, $http, invitationService) {

    $scope.invitationService = new invitationService();

});

app.factory('invitationService', function($http) {

    var invitationService = function() {
        this.Invitations = [];
        this.Init();
    };

    invitationService.prototype.Init = function() {

        $http.get("/api/friend/invitation").then(function(response) {
            var data = response.data;
            var Invitations = data.Invitations;
            for (var i = 0; i < Invitations.length; i++) {
                Invitations[i].show = true;
                Invitations[i].deleteConfirmBox = false;
                var sendt = getLocalSendTime(Invitations[i].sendtime);
                Invitations[i].sendtime = jQuery.timeago(sendt);
                this.Invitations.push(Invitations[i]);
            }

        }.bind(this));
    };

    invitationService.prototype.markAccept = function(Invitation) {
        $http({
            method: "POST",
            url: "/api/friend/accept?id=" + Invitation.id,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {
                Invitation.show = false;
            }
        }.bind(this));
    };

    invitationService.prototype.markReject = function(Invitation) {

        $http({
            method: "POST",
            url: "/api/friend/reject?id=" + Invitation.id,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {
                Invitation.show = false;
            }

        }.bind(this));
    };

    return invitationService;
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
});