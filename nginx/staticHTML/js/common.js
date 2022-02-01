// var host = 'http://127.0.0.1/api';
//var host='https://www.tsahaylu.com';

var defaultIconURL = '../images/defaulticon.ico';
var defaultAvatarURL = '../images/mystery-man.jpg';
var TsahayluTeamId = '1000000';

var mysteryManPhotoList = ["/images/mystery-man-00887D.jpg",
    "/images/mystery-man-0097A7.jpg",
    "/images/mystery-man-024D44.jpg",
    "/images/mystery-man-1D5597.jpg",
    "/images/mystery-man-3087D0.jpg",
    "/images/mystery-man-376922.jpg",
    "/images/mystery-man-5229A6.jpg",
    "/images/mystery-man-67A03B.jpg",
    "/images/mystery-man-AC47BB.jpg",
    "/images/mystery-man-BA1158.jpg",
    "/images/mystery-man-BA3200.jpg",
    "/images/mystery-man-E43C77.jpg",
    "/images/mystery-man-E56C00.jpg"
];

function getLocalSendTime(sendtime) {
    var d = new Date(sendtime);
    var sendtime = d.format("yyyy-MM-dd hh:mm:ss");
    return sendtime;
}

function getUTCSendTime() {
    var d = new Date();
    var localTime = d.getTime();
    localOffset = d.getTimezoneOffset() * 60000;
    utc = localTime + localOffset;
    var nd = new Date(utc);
    var sendtime = nd.format("yyyy-MM-dd hh:mm:ss");
    return sendtime;
}

Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function tip(msg) {
    clearTimeout(timer);
    $('#globe_tip').html(msg).fadeIn(300)
    var timer = setTimeout(function() {

        $('#globe_tip').fadeOut(300)
    }, 5000)
}

var app = angular.module('myApp', ['infinite-scroll']);


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


app.directive('ngEmailCheck', ['$http', function($http) {
    return {
        require: 'ngModel',
        link: function(scope, ele, attrs, c, ngModel) {
            element.bind("blur", function() {

                var inputEmail = ngModel.$modelValue;
                var correctEmailFormat = false;
                var exsitedEmail = false;

                if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(inputEmail)) {

                    correctEmailFormat = true;
                    $http({
                        method: 'POST',
                        url: '/api/user/emailcheck',
                        params: { 'email': inputEmail }
                    }).success(function(data) {
                        if (data.pass == "false" && data.msg == "The email have been regeistered already!") {
                            exsitedEmail = true;
                        } else {
                            exsitedEmail = false;
                        }
                    }).error(function(data) {
                        exsitedEmail = false;
                    });
                }

                c.$setValidity('correctEmailFormat', correctEmailFormat);
                c.$setValidity('exsitedEmail', exsitedEmail);
            });
        }
    }
}]);




/*var user_id = undefined;
var user_avatarURL = undefined;
var user_nickname = undefined;
*/

function getCookie(name) {
    var start = document.cookie.indexOf(name + "=");
    var len = start + name.length + 1;
    if ((!start) && (name != document.cookie.substring(0, name.length))) {
        return null;
    }
    if (start == -1) return null;
    var end = document.cookie.indexOf(';', len);
    if (end == -1) end = document.cookie.length;
    return unescape(document.cookie.substring(len, end));
}

function isLogin() {
    // curemail = getCookie("current_USERID");

    // localStorage.setItem('access_token', response.token);
        token = window.localStorage.getItem('access_token') 
    if (token ==  null)
        window.location.href = "/login.html";
    return token;
}

var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-37210396-1']);
_gaq.push(['_trackPageview']);


function getCenteredCoords(width, height) {
    var xPos = null;
    var yPos = null;
    if (window.ActiveXObject) {
        xPos = window.event.screenX - (width / 2) + 100;
        yPos = window.event.screenY - (height / 2) - 100;
    } else {
        var parentSize = [window.outerWidth, window.outerHeight];
        var parentPos = [window.screenX, window.screenY];
        xPos = parentPos[0] +
            Math.max(0, Math.floor((parentSize[0] - width) / 2));
        yPos = parentPos[1] +
            Math.max(0, Math.floor((parentSize[1] - (height * 1.25)) / 2));
    }
    return [xPos, yPos];
}