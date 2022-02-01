var app = angular.module('myApp', []);

app.directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if (event.which === 13) {
                scope.$apply(function() {
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
});

app.controller('login_box', function($scope, $http) {

    $scope.responseShow = false;
    $scope.emailInputValid = true;
    $scope.processing = false;
    $scope.emailChecking = false;

    $scope.emailCheck = function() {

        var email = $scope.email;

        if (email && email != "") {
            if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {

                if ($scope.emailChecking == false) {
                    $scope.emailChecking = true;
                    $http({
                        method: "POST",
                        url: "/api/user/emailcheck?email=" + email,
                    }).then(function(response) {
                        data = response.data;
                        if (data.pass == "false" && data.msg == "The email have been regeistered already!") {
                            $scope.emailInputValid = true;
                            $scope.responseShow = false;
                        } else {
                            $scope.responseContent = "The email have not been regeistered!";
                            $scope.responseShow = true;
                            $scope.emailInputValid = false;
                        }
                        $scope.emailChecking = false;
                    }, function() {
                        $scope.emailChecking = false;
                    });
                }
            } else {
                $scope.responseContent = "Invalid email format!";
                $scope.responseShow = true;
                $scope.emailInputValid = false;
            }
        }

    }

    $scope.signIn = function() {

        if ($scope.emailInputValid == false) {
            $scope.emailCheck();
        }

        if ($scope.emailInputValid) {
            var email = $scope.email;
            var password = $scope.password;
            if (password != '') {
                $scope.responseShow = false;
                if ($scope.processing == false) {
                    $scope.processing = true;
                    $http({
                        method: "POST",
                        url: "/api/user/check?email=" + email + "&password=" + password,
                    }).then(function(response) {
                        data = response.data;
                        if (data.pass == "true") {
                            window.localStorage.setItem('access_token', data.token);
                            window.location.href = "/new.html";
                        } else {
                            $scope.responseContent = data.msg;
                            $scope.responseShow = true;
                        }
                        $scope.processing = false;
                    }, function() {
                        $scope.processing = false;
                    });
                }
            } else {
                $scope.responseContent = "Password is empty!";
                $scope.responseShow = true;
            }
        }
    }

});



/*$(document).ready(function() {

    $('input[name="email"]').blur(function() {
        var myEmail = $(this).val();
        if (myEmail != '') {
            $.ajax({
                type: "POST",
                url: host + "/user/emailcheck",
                data: "email=" + myEmail,
                success: function(msg) {}
            })
        }
    })


    $('.submit').click(function() {
        var email = $('input[name="email"]').val();
        var password = $('input[name="password"]').val();

        $.ajax({
            type: "POST",
            url: host + '/user/check',
            data: { "email": email, "password": password },
            dataType: "JSON",
            success: function(data) {
                if (data.pass == "true") {
                    window.location.href = "/new.html";
                } else {
                    $('p.wrong_info').remove();
                    $('.input_box:eq(1)').after('<p class="wrong_info">' + data.msg + '</p>')
                }
            }
        });
    });

    $('input[name="email"]').keydown(function() {
        if (event.keyCode == 13) {
            $('.submit').trigger("click");
        }
    })
    $('input[name="password"]').keydown(function() {
        if (event.keyCode == 13) {
            $('.submit').trigger("click");
        }
    })


})*/