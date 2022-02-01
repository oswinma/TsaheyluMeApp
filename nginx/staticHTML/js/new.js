app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();
    // $scope.userservice.getUserInfo();

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
            $scope.favurlService.showResult = false;
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
        $scope.favurlService.showResult = false;
        searching = false;
        searchWord = '';
        $scope.favurlService.NextPage();
        $scope.searchWord = '';
        $scope.searchClearShow = false;
    }

    $scope.infiniteScroll = function(type) {
        if (searching) {
            $scope.favurlService.search(searchWord);
        } else
            $scope.favurlService.NextPage(type);

    }

});


app.controller('instroInfo', function($scope, $http, $window, ContactService) {

    $scope.showOverlay = false;

    $scope.Contacts = [];
    $scope.provider_id;

    $scope.EmailLines = [{ nickname: '', email: '', invalidEmail: false, isExsitedEmail: false, invalidNickname: false }];
    $scope.processing = false;
    $scope.ContactService = new ContactService();
    $scope.chromeExtensionId = "omgmmlnkloflnieeakggojbkpmeebcgl";
    $scope.extensionCheckResultShow = false;
    $scope.extensionCheckBtnShow = true;

    $http.get("/api/friend/available").then(function(response) {
        var data = response.data;
        if (data === "false") {
            $('#introduction').fadeIn(100);
            $('#overlay').fadeIn(100);
            // $('#nav_out').css('opacity', 1);
            // $('#nav_out').fadeIn();
        }
    });

    $scope.ContactService.getTsahayluTeamInfo();


    $window.handleOpenIDResponse = function(data) {
        $scope.ContactService.handleOpenIDResponse(data);
    };

    $scope.inviteUser = function(Contact) {

        if ($scope.processing == false) {
            $scope.processing = true;
            if (Contact.id != null) {
                $http({
                    method: "POST",
                    url: "/api/friend/invite",
                    params: { toid: Contact.id },
                }).then(function(response) {
                    var msg = response.data;
                    if (msg == "true") {
                        tip("A friend invitation has been sent to " + Contact.nickname + "!");
                    }

                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });

            } else {

                $http({
                    method: "POST",
                    url: '/api/friend/inviteemail',
                    params: { emailjson: " [{ nickname:" + Contact.nickname + ",email:" + Contact.email + " }]" },
                }).then(function(response) {
                    var data = response.data;
                    if (data == "true") {
                        providerTip("A email invitation has been sent to " + Contact.email + "!");
                    }
                    $scope.processing = false;
                }, function() {
                    $scope.processing = false;
                });
            }
        }
    }


    $scope.submitFeedback = function() {

        var feedbackText = $scope.feedbackText;

        if (feedbackText != "") {
            $('#overlay').fadeOut(100);
            $('#feedbackFrame').fadeOut(100);
            $http({
                method: "POST",
                url: '/api/service/feedback',
                data: feedbackText,
            }).then(function(response) {
                var data = response.data;
                if (data == "true") {
                    tip("Feedback submit successfully!");
                }

            }, function() {

            });
        }

    }

    $scope.checkExtension = function() {

        $scope.installBtnShow = false;
        $scope.extensionCheckBtnShow = false;
        $scope.extensionCheckResultShow = true;
        $scope.extensionCheckStatus = "Checking";

        if (chrome.runtime) {
            chrome.runtime.sendMessage($scope.chromeExtensionId, { message: "version" },
                function(response) {
                    $scope.extensionCheckStatus = "Uninstalled";
                    if (response) {
                        if (response.version >= 3.0) {
                            $scope.extensionCheckStatus = "Installed";
                        }
                    }
                    $scope.$apply();
                });
        } else {
            $scope.extensionCheckStatus = "Uninstalled";
        }

        if ($scope.extensionCheckStatus == "Uninstalled") {
            $scope.installBtnShow = true;
            $scope.extensionCheckBtnShow = true;
        }
    }

    $scope.SentEmailInvitationEvent = function() {

        if ($scope.processing == false) {
            $scope.processing = true;
            var allValid = true;

            data = [];
            for (var i = 0; i < $scope.EmailLines.length; i++) {
                emailLine = $scope.EmailLines[i];

                if (emailLine.nickname && emailLine.nickname != "") {

                    emailLine.invalidNickname = false;
                } else {
                    emailLine.invalidNickname = true;
                    allValid = false;
                }

                if (emailLine.email && emailLine.email != "") {

                    var email = emailLine.email;
                    if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)) {
                        emailLine.invalidEmail = false;
                        $.ajax({
                            type: "post",
                            data: { "email": email },
                            url: "/api/user/emailcheck",
                            dataType: "json",
                            async: false,
                            success: function(response) {
                                if (response.pass == "false" && response.msg == "The email have been regeistered already!") {
                                    emailLine.isExsitedEmail = true;
                                    allValid = false;
                                } else {
                                    emailLine.isExsitedEmail = false;
                                    idata = { 'nickname': emailLine.nickname, 'email': email };
                                    data.push(idata);
                                }
                            }
                        });

                    } else {
                        emailLine.invalidEmail = true;
                        allValid = false;
                    }


                } else {
                    emailLine.invalidEmail = true;
                    allValid = false;
                }
            }

            if (allValid) {
                data = JSON.stringify(data);

                $http({
                    method: "POST",
                    url: '/api/friend/inviteemail',
                    params: { emailjson: data },
                }).then(function(response) {
                    var msg = response.data;
                    if (msg == "true") {
                        feedbackTip("Email invitations have been sent!");
                        $scope.EmailLines = [{ nickname: '', email: '', invalidEmail: false, isExsitedEmail: false, invalidNickname: false }];
                    }
                    $scope.processing = false;

                }, function() {
                    $scope.processing = false;
                });
            } else {
                $scope.processing = false;
            }

        }

    }

    $scope.clearSearchResult = function() {
        $scope.ContactService.Contacts = [];
        $scope.ContactService.showResult = false;
        $scope.ContactService.showResultDiv = false;
    }

    $scope.deleteLineRow = function(index) {
        $scope.EmailLines.splice(index, 1);
    }


    $scope.addLineRow = function() {
        var emailLine = { nickname: '', email: '', invalidEmail: false, isExsitedEmail: false, invalidNickname: false };
        $scope.EmailLines.push(emailLine);
    }

    $scope.tryRecieve = function() {

        if ($scope.processing == false) {
            $scope.processing = true;
            $scope.recieveResultShow = false;
            $http({
                method: "POST",
                url: '/api/favurl/tryrecieve',
            }).then(function(response) {
                var data = response.data;
                if (data.code == 100) {
                    $scope.recieveStatus = "Please check the new tab";
                } else {
                    $scope.recieveStatus = data.msg;
                }
                $scope.recieveResultShow = true;
                $scope.processing = false;
            }, function() {
                $scope.processing = false;
            });
        }
    }

    $scope.trySend = function() {

        if ($scope.processing == false) {
            $scope.processing = true;
            $scope.sendResultShow = false;
            $http({
                method: "POST",
                url: '/api/favurl/trysend',
            }).then(function(response) {
                $scope.sendResultShow = true;
                $scope.sendStatus = "Please check the new tab";
                $scope.processing = false;
            }, function() {
                $scope.processing = false;
            });
        }
    }

});

function feedbackTip(msg) {
    clearTimeout(timer);
    $('#feedbakTip').html(msg).fadeIn(300)
    var timer = setTimeout(function() {

        $('#feedbakTip').fadeOut(300)
    }, 5000)
}

function providerTip(msg) {
    clearTimeout(timer);
    $('#providerTip').html(msg).fadeIn(300)
    var timer = setTimeout(function() {

        $('#providerTip').fadeOut(300)
    }, 5000)
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

    $('#manual').click(function() {
        $('#overlay').fadeIn(100);
        $('#manualFrame').fadeIn(100);
        $('#introduction').fadeIn(100);
    })


    $('.close').click(function() {
        $('#overlay').fadeOut(100);
        $('#manualFrame').fadeOut(100);
        $('#introduction').fadeOut();
        $('#step2').fadeOut();
        $('#step3').fadeOut();
        $('#invite_friend_box').fadeOut();
        $('#intro_fin').fadeOut();
        $('#sendTestDiv').fadeOut();
    })

    $('#overlay').on("click", function(event) {
        $('#overlay').fadeOut(100);
        $('#manualFrame').fadeOut(100)
        $('#introduction').fadeOut();
        $('#step2').fadeOut();
        $('#step3').fadeOut();
        $('#invite_friend_box').fadeOut();
        $('#intro_fin').fadeOut();
        $('#feedbackFrame').fadeOut();
        $('#sendTestDiv').fadeOut();
    })

    $('#introduction #next').on("click", function() {
        $('#introduction').fadeOut(100);
        $('#step2').fadeIn(100);
    })

    $('#step2 #next').on("click", function() {
        $('#step2').fadeOut(100);
        $('#step3').fadeIn(100);
    })

    $('#step2 #back').on("click", function() {
        $('#step2').fadeOut(100);
        $('#introduction').fadeIn(100);
    })

    $('#step3 #back').on("click", function() {
        $('#step3').fadeOut(100);
        $('#step2').fadeIn(100);
    })

    $('#step3 #next').on("click", function() {
        $('#step3').fadeOut(100);
        $('#invite_friend_box').fadeIn(100);
    })

    $('#invite_friend_box #next').on("click", function() {
        $('#invite_friend_box').fadeOut(100);
        $('#sendTestDiv').fadeIn(100);
    })

    $('#invite_friend_box #back').on("click", function() {
        $('#invite_friend_box').fadeOut(100);
        $('#step3').fadeIn(100);
    })

    $('#sendTestDiv #back').on("click", function() {
        $('#sendTestDiv').fadeOut(100);
        $('#invite_friend_box').fadeIn(100);
    })

    $('#sendTestDiv #next').on("click", function() {
        $('#sendTestDiv').fadeOut(100);
        $('#intro_fin').fadeIn(100);
    })

    $('#intro_fin #back').on("click", function() {
        $('#intro_fin').fadeOut(100);
        $('#sendTestDiv').fadeIn(100);
    })

    $('#intro_fin #next').on("click", function() {
        $('#overlay').fadeOut(100);
        $('#manualFrame').fadeOut(100);
        $('#introduction').fadeOut();
        $('#step2').fadeOut();
        $('#step3').fadeOut();
        $('#invite_friend_box').fadeOut();
        $('#intro_fin').fadeOut();
    })

    $('#feedback').click(function() {
        $('#overlay').fadeIn(100);
        $('#feedbackFrame').fadeIn(100);
    })

    $('#feedbackFrame #cancel').click(function() {
        $('#overlay').fadeOut(100);
        $('#feedbackFrame').fadeOut(100);
    })


});