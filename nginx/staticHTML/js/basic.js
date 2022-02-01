var app = angular.module('myApp', ['ngFileUpload']);

app.directive('errSrc', function() {
    return {
        link: function(scope, element, attrs) {

            scope.$watch(element, function() {
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

app.controller("basicInfo", function($scope, $http, Upload, userService, messageService) {

    $scope.uploadbtn = false;
    $scope.userService = new userService();
    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

    $http.get("/api/user").then(function(response) {
        var data = response.data;
        var user = data.user;
        $scope.user_nickname = user.nickname;
        $scope.user_email = user.email;
        $scope.user_country = user.country;
        $scope.user_avatarURL = user.avatarURL;
        $scope.emailSubscription = user.emailSubscription;
        $scope.favurlSubscription = user.favurlSubscription;
    });

    $scope.changePass = function() {

        user_pass1 = $scope.user_pass1;
        user_pass2 = $scope.user_pass2;

        $scope.userService.changePass(user_pass1, user_pass2);

    };

    $scope.updateEmail = function() {
        email = $scope.email;
        if (email && email != "")
            $scope.userService.checkEmail(email);

    };

    $scope.updateNickname = function() {

        user_nickname = $scope.user_nickname;

        if (user_nickname && user_nickname != "")
            $scope.userService.updateNickname(user_nickname);

    };

    $scope.updateCountry = function() {

        country = $scope.user_country;

        if (country && country != "")
            $scope.userService.updateCountry(country);

    };

    $scope.updateFavurlSubscription = function() {

        favurlSubscription = $scope.favurlSubscription;
        $scope.userService.updateFavurlSubscription(favurlSubscription);

    };

    $scope.updateEmailSubscription = function() {

        emailSubscription = $scope.emailSubscription;
        $scope.userService.updateEmailSubscription(emailSubscription);

    };


    $scope.upload = function(file) {

        $scope.userService.fileUpdate(file);
    };


    $scope.upload = function(file) {

        Upload.upload({
                url: '/api/user/avatar/update',
                file: file
            })
            .success(function(data, status, headers, config) {
                $scope.uploadbtn = false;
                $scope.user_avatarURL = data.avatarURL;
                tip("Picture update succesfully!");
            })
            .error(function(data, status, headers, config) {
                $scope.uploadbtn = false;
                tip("Picture update failed!");
                console.log('error status: ' + status);
            })
    };

    $scope.updateAvatar = function() {

        $scope.uploadbtn = true;

    };


});


app.factory('userService', function($http) {

    var userService = function() {
        this.password_box = false;
        this.showOverlay = false;
        this.user = {};

    };

    userService.prototype.getUserInfo = function() {

        $http.get("/api/user").then(function(response) {
            var data = response.data;
            if (data != null) {
                this.user = data.user;
            }
        }.bind(this), null);
    };

    userService.prototype.showPasswordBox = function() {
        this.password_box = true;
        this.showOverlay = true;

    };
    userService.prototype.hidePasswordBox = function() {
        this.password_box = false;
        this.showOverlay = false;

    };

    userService.prototype.overlayClick = function() {

        this.password_box = false;
        this.showOverlay = false;

    };

    userService.prototype.updateEmail = function(email) {

        $http({
            method: "POST",
            url: "/api/user/update?email=" + email,
            data: json,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {

                if (email != null)
                    tip("Email changed! Please revalidate your email address!");
            }
        }, null);

    };

    userService.prototype.updateNickname = function(nickname) {
        $http({
            method: "POST",
            url: "/api/user/update?nickname=" + nickname,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {

                if (nickname != null)
                    tip("Nickname changed!");
            }
        }, null);

    };

    userService.prototype.updateCountry = function(country) {
        $http({
            method: "POST",
            url: "/api/user/update?country=" + country,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {

                if (country != null)
                    tip("Country changed!");
            }
        }, null);

    };

    userService.prototype.updateEmailSubscription = function(subscribe) {
        $http({
            method: "POST",
            url: "/api/user/update?emailsubscription=" + subscribe,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {

                if (nickname != null)
                    tip("Email subscription changed!");
            }
        }, null);

    };

    userService.prototype.updateFavurlSubscription = function(subscribe) {
        $http({
            method: "POST",
            url: "/api/user/update?favurlsubscription=" + subscribe,
        }).then(function(response) {
            var data = response.data;
            if (data == "true") {

                if (nickname != null)
                    tip("Link subscription changed!");
            }
        }, null);

    };

    userService.prototype.checkEmail = function(email) {

        if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {
            $http({
                method: "POST",
                url: "/api/user/emailcheck?email=" + email,
                data: json,
            }).then(function(response) {
                if (response.pass == "false") {
                    tip('The email address "' + email + '" has regesitered already!');
                } else {
                    this.updateEmail();
                }

            }.bind(this));
        } else {
            tip("Invalid email format!");
        }
    };

    userService.prototype.changePass = function(new_pass1, new_pass2) {

        if (new_pass1 != '' && new_pass2 != '' && new_pass1 == new_pass2)
            $http({
                method: "POST",
                url: "/api/user/pass?password=" + new_pass1
            }).then(function(response) {
                if (response.data == "true") {
                    this.password_box = false;
                    this.showOverlay = false;
                    tip("Password changed!");
                }

            }.bind(this));
        else {
            tip("Invalid password");
        }

    };

    return userService;

});


app.factory('messageService', function($http) {

    var messageService = function() {
        this.msgNum = 0;
    };

    messageService.prototype.getMsgNum = function() {

        $http.get("/api/message/unreadnum").then(function(response) {
            if (response.data != null) {
                this.msgNum = parseInt(response.data);
            }
        }.bind(this), null);
    };

    return messageService;

});

function tip(msg) {
    clearTimeout(timer);
    $('#globe_tip').html(msg).fadeIn(300)
    var timer = setTimeout(function() {

        $('#globe_tip').fadeOut(300)
    }, 5000)
}

function isLogin() {
    // curemail = getCookie("current_USERID");

    // localStorage.setItem('access_token', response.token);
        token = window.localStorage.getItem('access_token') 
    if (token ==  null)
        window.location.href = "/login.html";
    return token;
}

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



function updateAvatar() {
    $("#user-avatar-form").css("display", "inline");
}

function changeAvatar() {
    $("#user-avatar-display-image img").attr("src", "/images/mystery-man.jpg");
}