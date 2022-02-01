var provider_id;
var searchsc;

app.controller("headerInfo", function($scope, userService, messageService) {

    $scope.userservice = new userService();

    $scope.messageservice = new messageService();
    $scope.messageservice.getMsgNum();

});

app.controller('friends_list', function($scope, $http, $window, ContactService, GroupService) {

    $scope.instrductionTitle = 'Friends List';
    $scope.showCreateGroupBox = false;
    $scope.showUpdateGroupBox = false;
    $scope.showDeleteGroupBox = false;
    $scope.showOverlay = false;
    $scope.showSentEmailInvitationBox = false;
    $scope.tsahayluTeamShow = true;

    $scope.ContactService = new ContactService();
    $scope.GroupService = new GroupService();
    $scope.EmailLines = [{ nickname: '', email: '', invalidEmail: false, isExsitedEmail: false, invalidNickname: false }];
    $scope.processing = false;

    $scope.ContactService.getTsahayluTeamInfo();
    $scope.ContactService.init();
    $scope.GroupService.init();


    /*    $scope.findFriends = function(provider_id) {

            ContactService.findFriends(provider_id);

            // $http({
            //     method: "POST",
            //     url: '/api/find/' + provider_id,
            // }).then(function(response) {
            //     var data = response.data;
            //     if (data.status == "ok") {
            //         var contacts = data.Contacts;
            //         $scope.ContactService.parseContacts(contacts);
            //     }
            //     if (data.status == "fail") {
            //         var w = $window.open("/api/find/" + $scope.provider_id + "?reOauth=true", 'openid_popup', 'width=450,height=500,location=1,status=1,resizable=yes');
            //         var coords = getCenteredCoords(450, 500);
            //         w.moveTo(coords[0], coords[1]);
            //     }
            //     if (data.status == "error") {
            //         $scope.ContactService.showResult = true;
            //         $scope.ContactService.resultMsg = data.msg;
            //         $scope.ContactService.showFindFriendLoading = false;
            //     }

            // }, function() {

            // });


        }*/

    $window.handleOpenIDResponse = function(data) {
        $scope.tsahayluTeamShow = false;
        $scope.ContactService.handleOpenIDResponse(data);
    }

    $scope.getFriendsInGroup = function(GroupDTO) {
        $scope.tsahayluTeamShow = false;
        GroupDTO.showButtons = true;
        isGroupFriends = true;
        $scope.ContactService.getFriends(GroupDTO);
        $scope.instrductionTitle = 'Friends in Group ' + GroupDTO.name;

    }

    $scope.allFriendsReset = function() {
        $scope.ContactService.init();
        $scope.GroupService.init();
        isGroupFriends = false;
        $scope.instrductionTitle = 'Friends List';
        $scope.tsahayluTeamShow = true;
    }

    $scope.showCreateGroupBoxEvent = function() {
        $scope.showCreateGroupBox = true;
        $scope.showOverlay = true;
    }

    $scope.showUpdateGroupBoxEvent = function(GroupDTO) {
        $scope.showUpdateGroupBox = true;
        $scope.showOverlay = true;
        $scope.groupIdUpdateBox = GroupDTO.id;
        $scope.groupNameUpdateBox = GroupDTO.name;
        $scope.groupDesUpdateBox = GroupDTO.des;
    }


    $scope.ShowDeleteGroupBoxEvent = function(GroupDTO) {
        $scope.showDeleteGroupBox = true;
        $scope.showOverlay = true;
        $scope.groupIdDeleteBox = GroupDTO.id;
        $scope.groupNameDeleteBox = GroupDTO.name;
    }

    $scope.hideCreateGroupBoxEvent = function() {
        $scope.showCreateGroupBox = false;
        $scope.showOverlay = false;
    }

    $scope.hideUpdateGroupBoxEvent = function() {
        $scope.showUpdateGroupBox = false;
        $scope.showOverlay = false;
    }

    $scope.hideDeleteGroupBoxEvent = function() {
        $scope.showDeleteGroupBox = false;
        $scope.showOverlay = false;
    }

    $scope.createGroup = function() {

        var name = $scope.groupNameCreateBox;
        var des = $scope.groupDesCreateBox;
        $scope.GroupService.createGroup(name, des);
        $scope.showCreateGroupBox = false;
        $scope.showOverlay = false;
        $scope.groupNameCreateBox = '';
        $scope.groupDesCreateBox = '';

    }

    $scope.updateGroup = function() {

        var id = $scope.groupIdUpdateBox;
        var name = $scope.groupNameUpdateBox;
        var des = $scope.groupDesUpdateBox;
        $scope.GroupService.updateGroup(id, name, des);
        $scope.showUpdateGroupBox = false;
        $scope.showOverlay = false;

    }

    $scope.deleteGroup = function() {

        var id = $scope.groupIdDeleteBox
        var name = $scope.groupNameDeleteBox
        $scope.GroupService.deleteGroup(id, name);
        $scope.showDeleteGroupBox = false;
        $scope.showOverlay = false;
    }

    $scope.findFriends = function(provider_name) {
        $scope.tsahayluTeamShow = false;
        $scope.ContactService.findFriends(provider_name);
        $scope.instrductionTitle = 'Result of find friends in ' + provider_name;
    }

    $scope.searchUserEvent = function() {

        $scope.ContactService.noSearchResult = false
        searchWord = $scope.searchWord;

        if (searchWord != '') {
            $scope.tsahayluTeamShow = false;
            $scope.ContactService.Contacts = [];
            $scope.ContactService.searchstop = false;
            $scope.ContactService.searchsc = '';
            $scope.ContactService.searchUser(searchWord);
            $scope.instrductionTitle = 'Result of search word: ' + searchWord;
        }

    }

    $scope.clearSearchResult = function() {
        $scope.ContactService.Contacts = [];
        $scope.ContactService.showResult = false;
        $scope.ContactService.sc = '';
        $scope.ContactService.searchsc = '';
        $scope.ContactService.stop = false;
        $scope.ContactService.searchstop = false;
        $scope.ContactService.noSearchResult = false;
        searching = false;
        searchWord = '';
        isGroupFriends = false;
        $scope.ContactService.init();
        $scope.searchWord = '';
        $scope.tsahayluTeamShow = true;
        $scope.instrductionTitle = 'Friends List';
    }

    $scope.inviteSignupEmailEvent = function() {
        var email = $scope.searchWord;
        $scope.ContactService.inviteFriendByEmail(email);
    }

    var isGroupFriends = false;

    $scope.removeFriendEvent = function(Contact) {
        Contact.showDeleteFriendConfirmDial = false;
        $scope.ContactService.deleteFriend(Contact);
    }

    $scope.removeFriendFromGroupEvent = function(Contact) {

        Contact.showDeleteFriendConfirmDial = false;
        $scope.GroupService.removeFriend(Contact);
    }

    $scope.blockFriendEvent = function(Contact) {
        $scope.ContactService.blockFriend(Contact);
    }

    $scope.changePopupEvent = function(Contact) {
        $scope.ContactService.updateFriendPopup(Contact);
    }

    $scope.showDeleteConfirmDialEvent = function(Contact) {
        if (!isGroupFriends)
            Contact.showDeleteFriendConfirmDial = true;
        else
            Contact.showDeleteGroupConfirmDial = true;
    }


    $scope.hideDeleteFriendConfirmDialEvent = function(Contact) {

        Contact.showDeleteFriendConfirmDial = false;
    }

    $scope.hideDeleteGroupConfirmDialEvent = function(Contact) {

        Contact.showDeleteGroupConfirmDial = false;
    }

    $scope.inviteUserEvent = function(Contact) {
        $scope.ContactService.inviteFriendByUser(Contact);
    }

    $scope.showSentEmailInvitationBoxEvent = function() {
        $scope.showSentEmailInvitation = true;
        $scope.showOverlay = true;
    }

    $scope.hideSentEmailInvitationBoxEvent = function() {
        $scope.showSentEmailInvitation = false;
        $scope.showOverlay = false;
    }

    $scope.deleteLineRow = function(index) {
        $scope.EmailLines.splice(index, 1);
    }


    $scope.addLineRow = function() {
        var emailLine = { nickname: '', email: '', invalidEmail: false, isExsitedEmail: false, invalidNickname: false };
        $scope.EmailLines.push(emailLine);
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
                        $scope.showSentEmailInvitation = false;
                        $scope.showOverlay = false;
                        tip("Email invitations have been sent!");
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

    $scope.overlayClick = function() {
        $scope.showSentEmailInvitation = false;
        $scope.showOverlay = false;
        $scope.showCreateGroupBox = false;
        $scope.showUpdateGroupBox = false;
    }


});

function onDragStart(e) {
    toid = $(e).attr('user-id');
    nickname = $(e).find('.friend_nickname').html();
    contactEmail = $(e).find('#contactEmail').html();

    $(e).css('opacity', '0.4');
}

function onDragEnd(e) {
    $(e).css('opacity', '1');
    $('.circle_outer').css({ 'padding': '20px', 'margin': '14px' })
}

function onDragEnter(e) {
    $('.circle_outer').css({ 'padding': '20px', 'margin': '14px' })
    $(e).find('.circle_outer').css({ 'padding': '34px', 'margin': '0' })
}

function onDragOver(e) {
    this.event.preventDefault();
}

function onDrop(e) {
    var groupid = $(e).attr('group-id');
    var groupname = $(e).find('.group_name').html();
    addToGroupRequest(nickname, contactEmail, toid, groupid, groupname, e);
}

function addToGroupRequest(nickname, email, toid, groupid, groupname, e) {

    if (toid == "" || !toid) {
        $.ajax({
            type: "POST",
            url: "/api/friend/inviteemail",
            data: { emailjson: " [{ nickname:" + nickname + ",email:" + email + " }]" },
            dataType: "text",
            success: function(response) {
                if (response == "true") {
                    tip("A email invitation has been sent to " + email + "!");
                }
            }
        });
    } else {
        $.ajax({
            type: "POST",
            url: "/api/group/add",
            data: { "toid": toid, "groupid": groupid },
            dataType: "text",
            success: function(data) {
                if (data == "false")
                    tip(nickname + ' alreay in your "' + groupname + '"!');
                else {
                    tip(nickname + ' has been add to "' + groupname + '"!');
                }
            }
        });
    }

}


function NameEmail(nickname, email) {
    this.nickname = nickname;
    this.email = email;
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

    // navInitial();

    // addFindFriendEvent();

    // $('.delete_row').click(function() {
    //     $(this).parent('div').remove();
    // });

    // $('.delete_row').on("click", function() {
    //     $(this).parent('div').remove();
    // });

    // $('#add_row').click(function() {
    //     $('#invite_box_line').append('<div class="input_box"><input class="nickname"/><input class="email"/><a href="###" class="delete_row" title="Delete this row" ><img src="/images/delete_row.jpg" /></a><div class="clear"></div></div>');
    // });

    $('#search_user_clear,#search_friends_result span').on("click", function() {
        $('#search_friend').val('');
        $(this).hide();
    });

    $('#search_friend').focus(function() {
        $('#search_user_clear').show();
    }).blur(function() {
        if ($('#search_friend').val() == '') {
            $('#search_user_clear').hide();
        }
    });

    $('#search_box h4 a').click(function() {
        location.reload();
    });

    $('#friends_list').on('mouseenter', 'li', function() {
        $(this).find('.del_friend,.remove_friend,.popup,.unblock').show();
    });

    $('#friends_list').on('mouseleave', 'li', function() {
        $(this).find('.del_friend,.remove_friend,.popup,.unblock').hide();
    });

})