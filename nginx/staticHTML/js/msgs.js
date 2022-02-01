app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();
    // $scope.userservice.getUserInfo();

    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

});


app.controller('msg_list_controller', function($scope, $http, messageService) {

    $scope.messageService = new messageService();

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