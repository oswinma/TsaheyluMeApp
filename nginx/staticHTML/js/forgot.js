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

app.controller('forget_info', function($scope, $http) {

    $scope.responseShow = false;
    $scope.emailInputFocus = false;
    $scope.emailInputValid = false;
    $scope.processing = false;
    $scope.emailChecking = false;
    $scope.emailCheck = function() {
        var email = $scope.email;
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
                        $scope.responseShow = true;
                        // $scope.responseContent = "Valid email!";
                    } else {
                        $scope.responseContent = "The email haven't been regeistered, please input right email address!";
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

    $scope.submit = function() {

        if ($scope.emailInputValid == false) {
            $scope.emailCheck();
        }

        if ($scope.emailInputValid) {
            var email = $scope.email;

            if ($scope.processing == false) {
                $scope.processing = true;
                $http({
                    method: "POST",
                    url: "/api/user/forgot?email=" + email,
                }).then(function(response) {
                    if (response.data == "true") {
                        $scope.responseContent = "Password reset email has been sent successfully!";
                        $scope.responseShow = true;
                        $scope.emailInputValid = false;
                    }

                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        }
    }

});