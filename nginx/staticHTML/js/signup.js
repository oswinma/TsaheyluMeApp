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

app.controller('signUpInfo', function($scope, $http) {

    $scope.responseShow = false;
    $scope.emailInputFocus = false;
    $scope.emailInputValid = false;
    $scope.nicknameInputValid = false;
    $scope.countryInputValid = false;
    $scope.passwordInputValid = false;
    $scope.processing = false;

    $scope.initGet = function(email, nickname, country, openid_provider, provider_id, token, avatarURL, refresh_token, expires_in, fromid) {

        $scope.email = email;
        $scope.emailInputValid = true;

        if (nickname != "") {
            $scope.nickname = nickname;
            $scope.nicknameInputValid = true;
        }

        if (country != "") {
            $scope.country = country;
            $scope.countryInputValid = true;
        }

        if (fromid != "") {
            $scope.fromid = fromid;
        }

        if (openid_provider != "")
            $scope.openid_provider = openid_provider;

        if (provider_id != "")
            $scope.provider_id = provider_id;

        if (token != "")
            $scope.token = token;

        if (avatarURL != "")
            $scope.avatarURL = avatarURL;

        if (refresh_token != "")
            $scope.refresh_token = refresh_token;

        if (expires_in != "")
            $scope.expires_in = expires_in;
    }

    $scope.emailCheck = function() {

        var email = $scope.email;

        if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {
            $http({
                method: "POST",
                url: "/api/user/emailcheck?email=" + email,
            }).then(function(response) {
                data = response.data;
                if (data.pass == "false" && data.msg == "The email have been regeistered already!") {
                    $scope.emailInputValid = false;
                    $scope.responseShow = true;
                    $scope.responseContent = data.msg;
                } else {
                    $scope.responseShow = false;
                    $scope.emailInputValid = true;
                }

            }, null);
        } else {
            $scope.responseContent = "Invalid email format!";
            $scope.responseShow = true;
            $scope.emailInputValid = false;
        }

    }


    $scope.nicknameCheck = function() {
        var nickname = $scope.nickname;

        if (nickname != '') {
            $scope.nicknameInputValid = true;
            $scope.responseShow = false;
        } else {
            $scope.responseContent = 'You must input your nickname!';
            $scope.responseShow = true;
        }
    }

    $scope.countryCheck = function() {

        var country = $scope.country;

        if (country != '') {
            $scope.countryInputValid = true;
            $scope.responseShow = false;
        } else {
            $scope.responseContent = 'You must input your country!';
            $scope.responseShow = true;
        }
    }

    $scope.passwordCheck = function() {
        var password = $scope.password;
        if (password != '') {
            if (password.length < 8) {
                $scope.responseContent = 'Password length less than 8!';
                $scope.responseShow = true;
            } else {
                $scope.responseShow = false;
            }
        } else {
            $scope.responseContent = 'You must input your password!';
            $scope.responseShow = true;
        }
    }

    $scope.confirmPasswordCheck = function() {
        var password = $scope.password;
        var confirm_password = $scope.confirm_password;

        if (confirm_password == '') {
            $scope.responseContent = 'You must input your password!';
            $scope.responseShow = true;
        } else {
            if (confirm_password != password) {
                $scope.responseContent = 'Your password does not match!';
                $scope.responseShow = true;
            } else {
                $scope.passwordInputValid = true;
                $scope.responseShow = false;
            }
        }
    }

    $scope.inputCheckAll = function() {

        if ($scope.emailInputValid == false) {
            $scope.emailCheck();
        }

        if ($scope.passwordInputValid == false) {
            $scope.passwordCheck();
            $scope.confirmPasswordCheck();
        }

        if ($scope.nicknameInputValid == false) {
            $scope.nicknameCheck();
        }

        if ($scope.countryInputValid == false) {
            $scope.countryCheck();
        }
    }

    $scope.signUp = function() {

        $scope.inputCheckAll();

        if ($scope.emailInputValid & $scope.nicknameInputValid & $scope.countryInputValid & $scope.passwordInputValid) {
            $scope.responseShow = false;

            if ($scope.processing == false) {
                $scope.processing = true;
                $http({
                    method: "POST",
                    url: "/api/user/create",
                    params: {
                        "email": $scope.email,
                        "nickname": $scope.nickname,
                        "country": $scope.country,
                        "password": $scope.password
                    }
                }).then(function(response) {
                    if (response.data == "true") {
                        $scope.responseContent = 'Sign up successfully, Please Verify Your Email!';
                        $scope.responseShow = true;
                        window.location.href = "/login.html";
                        // ga('send', 'event', 'user', 'signUp', 'signUp', 1);
                    }
                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        } else {
            $scope.responseContent = 'Please complete your information!';
            $scope.responseShow = true;
        }

    }




    $scope.inviteSignUp = function() {

        $scope.inputCheckAll();

        if ($scope.emailInputValid & $scope.nicknameInputValid & $scope.countryInputValid & $scope.passwordInputValid) {
            $scope.responseShow = false;

            if ($scope.processing == false) {
                $scope.processing = true;
                $http({
                    method: "POST",
                    url: "/api/user/invitesignup",
                    params: {
                        "email": $scope.email,
                        "nickname": $scope.nickname,
                        "country": $scope.country,
                        "password": $scope.password,
                        "fromid": $scope.fromid
                    }
                }).then(function(response) {
                    if (response.data == "true") {
                        $scope.responseContent = 'Sign up successfully, Please Verify Your Email!';
                        $scope.responseShow = true;
                        window.location.href = "/login.html";
                    }
                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        } else {
            $scope.responseContent = 'Please complete your information!';
            $scope.responseShow = true;
        }

    }

    $scope.openidSignUp = function() {

        $scope.inputCheckAll();

        if ($scope.emailInputValid & $scope.nicknameInputValid & $scope.countryInputValid & $scope.passwordInputValid) {
            $scope.responseShow = false;


            if ($scope.processing == false) {
                $scope.processing = true;
                $http({
                    method: "POST",
                    url: "/api/service/OpenIDReturn",
                    params: {
                        "email": $scope.email,
                        "nickname": $scope.nickname,
                        "country": $scope.country,
                        "password": $scope.password,
                        "openid_provider": $scope.openid_provider,
                        "provider_id": $scope.provider_id,
                        "token": $scope.token,
                        "avatarURL": $scope.avatarURL,
                        "refresh_token": $scope.refresh_token,
                        "expires_in": $scope.expires_in
                    }
                }).then(function(response) {
                    if (response.data == "true") {
                        $scope.responseContent = 'Sign up successfully, Please Verify Your Email!';
                        $scope.responseShow = true;
                        window.location.href = "/new.html";
                    }
                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        } else {
            $scope.responseContent = 'Please complete your information!';
            $scope.responseShow = true;
        }

    }

    $scope.resetPassword = function() {

        if ($scope.passwordInputValid == false) {
            $scope.confirmPasswordCheck();
        }

        if ($scope.passwordInputValid) {
            $scope.responseShow = false;

            var password = $scope.password;
            if ($scope.processing == false) {
                $scope.processing = true;
                $http({
                    method: "POST",
                    url: "/api/user/pass?email=" + email,
                }).then(function(response) {
                    if (response.data == "true") {
                        $scope.responseContent = 'Password changed successfully! Please relogin!';
                        $scope.responseShow = true;
                        window.location.href = "/login.html";
                    }
                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        } else {
            $scope.responseContent = 'Please complete your information!';
            $scope.responseShow = true;
        }

    }

});

/*  $('input[name="email"]').blur(function(){
        emailCheck(this)
    })
    
    $('input[name="nickname"]').blur(function(){
        nicknameCheck(this)
    })
    
    $('input[name="country"]').blur(function(){
        countryCheck(this);
    })*/

/*    $('input[name="password"]').blur(function() {
        var myPwd = $(this).val();
        if (myPwd != '') {
            if (myPwd.length < 8) {
                $(this).next('p').remove();
                $(this).parent('.input_box').removeClass('true')
                $(this).after('<p>Password length less than 8</p>')
            } else {
                $(this).next('p').remove();
                $(this).parent('.input_box').addClass('true')
            }
        } else {
            $(this).next('p').remove();
            $(this).parent('.input_box').removeClass('true')
            $(this).after('<p>you must input your password</p>')
        }
    })*/
/*    $('input[name="confirm_password"]').blur(function() {
        var ConPwd = $(this).val();
        if ($('input[name="password"]').val() == '') {
            $('input[name="password"]').next('p').remove();
            $('input[name="password"]').parent('.input_box').removeClass('true')
            $('input[name="password"]').after('<p>you must input your password</p>')
            $(this).val('');
            $(this).parent('.input_box').removeClass('true')
        } else {
            if (ConPwd != $('input[name="password"]').val()) {
                $(this).next('p').remove();
                $(this).parent('.input_box').removeClass('true')
                $(this).after('<p>your password does not match</p>')
            } else {
                $(this).next('p').remove();
                $(this).parent('.input_box').addClass('true')
            }
        }
    })
*/
/*    $('#signupbutton').click(function() {
        if ($('.input_box span:visible').length != 5) {
            $('.input_box:eq(4)').next('strong').remove();
            $('.input_box:eq(4)').after("<strong>please complete your information</strong>")
            return false
        } else {
            $('form strong').hide();

            var email = $('input[name="email"]').val();
            var nickname = $('input[name="nickname"]').val();
            var country = $('input[name="country"]').val();
            var password = $('input[name="password"]').val();

            $.ajax({
                type: "POST",
                url: host + "/user/create",
                data: { "email": email, "nickname": nickname, "country": country, "password": password },
                dataType: "text",
                success: function(data) {
                    if (data == "true") {
                        $('.input_box:eq(4)').next('strong').remove();
                        $('.input_box:eq(4)').after("<strong>Sign up successfully!</strong>")
                    }

                }
            })
        }
    })*/



// ---------------------
/*    $('#openidsignup').click(function() {
        if ($('.input_box span:visible').length != 5) {
            $('.input_box:eq(4)').next('strong').remove();
            $('.input_box:eq(4)').after("<strong>please complete your information</strong>")
            return false
        } else {
            $('form strong').hide();
        }
    })

    $('#invitesignup').click(function() {
        if ($('.input_box span:visible').length != 5) {
            $('.input_box:eq(4)').next('strong').remove();
            $('.input_box:eq(4)').after("<strong>please complete your information</strong>")
            return false
        } else {
            $('form strong').hide();

            var email = $('input[name="email"]').val();
            var nickname = $('input[name="nickname"]').val();
            var country = $('input[name="country"]').val();
            var password = $('input[name="password"]').val();
            var fromid = $('input[name="fromid"]').val();

            $.ajax({
                type: "POST",
                url: host + "/user/invitesignup",
                data: { "fromid": fromid, "email": email, "nickname": nickname, "country": country, "password": password },
                dataType: "text",
                success: function(data) {
                    if (data == "true") {
                        $('.input_box:eq(4)').next('strong').remove();
                        $('.input_box:eq(4)').after("<strong>Sign up successfully!</strong>")
                    }

                }
            })
        }
    })

    $('#confirmbutton').click(function() {
        if ($('.input_box span:visible').length != 3) {
            $('.input_box:eq(2)').next('strong').remove();
            $('.input_box:eq(2)').after("<strong>please complete your information</strong>")
            return false
        } else {
            $('form strong').hide();

            var password = $('input[name="password"]').val();

            $.ajax({
                type: "POST",
                url: host + "/user/pass",
                data: { "password": password },
                dataType: "text",
                success: function(data) {
                    if (data == "true") {
                        $('.input_box:eq(2)').next('strong').remove();
                        $('.input_box:eq(2)').after("<strong>Password changed successfully! Please relogin</strong>")
                    }
                }
            })
        }
    })*/

/*  $('input[name="confirm_password"]').keydown(function(){
        if(event.keyCode==13){
            $('#signupbutton').trigger("click");    
        }
    })
    
        $('input[name="email"]').keydown(function(){
        if(event.keyCode==13){
            $('#signupbutton').trigger("click");    
        }
    })
    
        $('input[name="nickname"]').keydown(function(){
        if(event.keyCode==13){
            $('#signupbutton').trigger("click");    
        }
    })
        $('input[name="country"]').keydown(function(){
        if(event.keyCode==13){
            $('#signupbutton').trigger("click");    
        }
    })
        $('input[name="password"]').keydown(function(){
        if(event.keyCode==13){
            $('#signupbutton').trigger("click");    
        }
    })*/