var dragtip = "drag friends to group, then you can send link to them";
var blocktip = "you can send link to this friend, but link sent from this friend is blocked";
var unblocktip = "you and this friend can send link to each other";
var popuptip = "browser will create a tab when this friend send a link to you";
var unpopuptip = "link will be put in new list when this friend send a link to you";
var deletefromftip = "remove friend from your friend list";
var deletefromgtip = "remove friend from this group";
var noresulttip = "NO RESULT FOUND";



app.factory('httpInterceptor',['$q','$injector','$localStorage',function ($q,$injector,$localStorage) {
        var httpInterceptor = {
            'responseError' : function(response) {
                // ......
                return $q.reject(response);
            },
            'response' : function(response) {
                if (response.status == 21000) {
                    // console.log('do something...');
                }
                return response || $q.when(response);
            },
            'request' : function(config) {
                config.headers = config.headers || {};
                if ($localStorage.token) {
                    config.headers.token = $localStorage.token;
                    // config.headers['X-Access-Token'] = $localStorage.token;
                };

                return config || $q.when(config);

                return config;
            },
            'requestError' : function(config){
                // ......
                return $q.reject(config);
            }
        };
        return httpInterceptor;
    }])


// app.factory('httpInterceptor',['$q','$injector','$localStorage',function ($q,$injector,$localStorage) {
//         var httpInterceptor = {
//             'responseError' : function(response) {
//                 // ......
//                 return $q.reject(response);
//             },
//             'response' : function(response) {
//                 if (response.status == 21000) {
//                     // console.log('do something...');
//                 }
//                 return response || $q.when(response);
//             },
//             'request' : function(config) {
//                 config.headers = config.headers || {};
//                 if ($localStorage.access_token) {
//                     config.headers.Authorization = $localStorage.access_token;
//                     // config.headers['X-Access-Token'] = $localStorage.token;
//                 };

//                 return config || $q.when(config);

//                 return config;
//             },
//             'requestError' : function(config){
//                 // ......
//                 return $q.reject(config);
//             }
//         };
//         return httpInterceptor;
//     }])


// app.config(["$httpProvider", function ($httpProvider) {
//      $httpProvider.interceptors.push(httpInterceptor);
// }]);
app.config(["$httpProvider", function ($httpProvider) {

$httpProvider.interceptors.push(['$q','$injector','$window',function ($q,$injector,$window) {
            var httpInterceptor = {
                'responseError' : function(response) {
                    // todo...
                    return $q.reject(response);
                },
                'response' : function(response) {
                    if (response.status == 21000) {
                        // console.log('do something...');
                    }
                    return response || $q.when(response);
                },
                'request' : function(config) {
                    config.headers = config.headers || {};
                    if ($window.localStorage.access_token) {
                        config.headers.Authorization = $window.localStorage.access_token; //把你登录接口返回给你的token存到$localStorage里面，在这里取就好了
                        // config.headers['X-Access-Token'] = $localStorage.token;
                    };

                    return config || $q.when(config);
                    // return config;
                },
                'requestError' : function(config){
                    // todo...
                    return $q.reject(config);
                }
            };
            return httpInterceptor;
        }]);
}]);

app.factory('favurlService', function($http, $rootScope) {

    var favurlService = function() {
        this.FavURLShows = [];
        this.busy = false;
        this.sc = '';
        this.searchsc = '';
        this.stop = false;
        this.searchstop = false;
        this.showResult = false;
        this.favurlType = '';
    };

    favurlService.prototype.NextPage = function(type) {
        if (this.stop) return;
        if (this.busy) return;


        if (type != null) {
            this.favurlType = type;
        }

        this.busy = true;

        $http.get("/api/favurl/" + this.favurlType + "?startCursor=" + this.sc).then(function(response) {
            var data = response.data;
            var favurlshows = data.FavURLShows;
            for (var i = 0; i < favurlshows.length; i++) {
                favurlshows[i].show = true;
                favurlshows[i].deleteConfirmBox = false;
                var sendt = getLocalSendTime(favurlshows[i].sendtime);
                favurlshows[i].sendtime = jQuery.timeago(sendt);
                this.FavURLShows.push(favurlshows[i]);
            }

            if (this.sc == data.startCursor)
                this.stop = true

            this.sc = data.startCursor;

            this.busy = false;
        }.bind(this));
    };


    favurlService.prototype.userNextPage = function(id) {
        if (this.stop) return;
        if (this.busy) return;

        this.busy = true;

        $http.get("/api/favurl/my?fromid=" + id + "&startCursor=" + this.sc).then(function(response) {
            var data = response.data;
            var favurlshows = data.FavURLShows;
            for (var i = 0; i < favurlshows.length; i++) {
                favurlshows[i].show = true;
                favurlshows[i].deleteConfirmBox = false;
                var sendt = getLocalSendTime(favurlshows[i].sendtime);
                favurlshows[i].sendtime = jQuery.timeago(sendt);
                this.FavURLShows.push(favurlshows[i]);
            }

            if (this.sc == data.startCursor)
                this.stop = true

            this.sc = data.startCursor;

            this.busy = false;
        }.bind(this));
    };


    favurlService.prototype.markRead = function(FavURLShow) {

        if (FavURLShow.status == 1)
            FavURLShow.status = 2;
        else
            FavURLShow.status = 1;

        if (this.favurlType != "fav") {
            FavURLShow.show = false;
        }

        var json = '[{"id":' + FavURLShow.id + ',"status":' + FavURLShow.status + '}]';
        $http({
            method: "POST",
            url: "/api/favurl/status",
            data: json,
        }).then(null, function() {
            if (FavURLShow.status == 1)
                FavURLShow.status = 2;
            else
                FavURLShow.status = 1;
            if (this.favurlType != "fav") {
                FavURLShow.show = true;
            }
        }.bind(this));
    };

    favurlService.prototype.markFav = function(FavURLShow, index) {

        FavURLShow.fav = !FavURLShow.fav;
        var data = FavURLShow.id + '&fav=' + FavURLShow.fav;

        if (this.favurlType == "fav") {
            if (FavURLShow.fav) {
                FavURLShow.show = true;
            } else {
                FavURLShow.show = false;
            }
        }

        $http({
            method: "POST",
            url: "/api/favurl/fav/update?id=" + data,
        }).then(null, function() {
            FavURLShow.fav = !fav;
            if (this.favurlType == "fav") {
                if (FavURLShow.fav) {
                    FavURLShow.show = true;
                } else {
                    FavURLShow.show = false;
                }
            }
        }.bind(this));

    };

    favurlService.prototype.markDelete = function(FavURLShow) {

        FavURLShow.deleteConfirmBox = false;

        var status = 3;

        FavURLShow.show = false;

        var json = '[{"id":' + FavURLShow.id + ',"status":' + status + '}]';
        $http({
            method: "POST",
            url: "/api/favurl/status",
            data: json
        }).then(null, function() {
            FavURLShow.show = true;
        }.bind(this));

    };


    favurlService.prototype.showDeleteConfirmBox = function(FavURLShow) {

        FavURLShow.deleteConfirmBox = true;


    };

    favurlService.prototype.hideDeleteConfirmBox = function(FavURLShow) {

        FavURLShow.deleteConfirmBox = false;

    };

    favurlService.prototype.search = function(word) {
        if (this.searchstop) return;
        if (this.busy) return;

        this.busy = true;

        $http.get("/api/favurl/search?word=" + word + '&startCursor=' + this.searchsc).then(function(response) {

            var data = response.data;
            var favurlshows = data.FavURLShows;

            if (favurlshows.length == 0 && this.searchsc == '') {
                this.showResult = true;
            } else {
                for (var i = 0; i < favurlshows.length; i++) {
                    favurlshows[i].show = true;
                    favurlshows[i].deleteConfirmBox = false;
                    var sendt = getLocalSendTime(favurlshows[i].sendtime);
                    favurlshows[i].sendtime = jQuery.timeago(sendt);
                    this.FavURLShows.push(favurlshows[i]);
                }
            }

            if (this.searchsc == data.startCursor)
                this.searchstop = true
            this.searchsc = data.startCursor;

            this.busy = false;

        }.bind(this));
    };

    return favurlService;
});

app.factory('userService', function($http) {

    var userService = function() {
        this.password_box = false;
        this.showOverlay = false;
        this.user = {};
        this.getUserInfo();
    };

    userService.prototype.getUserInfo = function() {

        $http.get("/api/user/basic").then(function(response) {
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

            }, null);
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
                if (response == "true") {
                    this.password_box = false;
                    this.showOverlay = false;
                }

            }, null);
        else {
            tip("Invalid password");
        }

    };

    return userService;

});

app.factory('messageService', function($http) {

    var messageService = function() {
        this.MessageInfos = [];
        this.busy = false;
        this.sc = '';
        this.stop = false;
        this.msgNum = 0;

    };

    messageService.prototype.getMsgNum = function() {

        $http.get("/api/message/unreadnum").then(function(response) {
            if (response.data != null) {
                this.msgNum = parseInt(response.data);
            }
        }.bind(this), null);
    };

    messageService.prototype.nextPage = function() {
        if (this.stop) return;
        if (this.busy) return;

        this.busy = true;

        $http.get("/api/message?startCursor=" + this.sc).then(function(response) {
            var data = response.data;
            var messageInfos = data.MessageInfos;
            for (var i = 0; i < messageInfos.length; i++) {
                messageInfos[i].show = true;
                messageInfos[i].highLight = true;
                this.MessageInfos.push(messageInfos[i]);
            }

            if (this.sc == data.startCursor)
                this.stop = true

            this.sc = data.startCursor;

            this.busy = false;
        }.bind(this), null);
    };

    messageService.prototype.markRead = function(MessageInfo) {

        MessageInfo.show = false;
        MessageInfo.highLight = false;

        $http({
            method: "POST",
            url: "/api/message/remove?id=" + MessageInfo.id
        }).then(null, function() {
            MessageInfo.show = true;
            MessageInfo.highLight = true;

        });
    };

    return messageService;

});

app.factory('GroupService', function($http) {

    var GroupService = function() {
        this.GroupDTOs = [];
        this.Contacts = [];
        this.busy = false;
        this.sc = '';
        this.searchsc = '';
        this.stop = false;
    };

    GroupService.prototype.init = function() {
        if (this.stop) return;
        if (this.busy) return;

        this.busy = true;

        $http.get("/api/group/info").then(function(response) {
            var data = response.data;
            var groupdtos = data.GroupDTOs;

            for (var i = 0; i < groupdtos.length; i++) {
                groupdtos[i].showButtons = false;
            }

            this.GroupDTOs = groupdtos;

            this.busy = false;
        }.bind(this));
    };

    GroupService.prototype.updateGroup = function(groupid, name, des) {

        $http({
            method: "POST",
            url: "/api/group/update?groupid=" + groupid + '&name=' + name + '&des=' + des,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                this.init();
                tip('Group: ' + name + ' has been updated!');
            }

        }.bind(this));
    };

    GroupService.prototype.createGroup = function(name, des) {

        $http({
            method: "POST",
            url: "/api/group/create?name=" + name + "&des=" + des,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                this.init();
                tip('Group: ' + name + ' created succesfully!');
            }
        }.bind(this));
    };

    GroupService.prototype.deleteGroup = function(id, name) {

        $http({
            method: "POST",
            url: "/api/group/delete?groupid=" + id,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                this.init();
                tip('Group: ' + name + ' has been deleted!');
            }
        }.bind(this));
    };


    GroupService.prototype.moveGroup = function(GroupDTO) {

        $http({
            method: "POST",
            url: "/api/group/move?toid=" + toid + "&fromgroup=" + fromgroup + "&togroup=" + togroup,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {

            }
        }.bind(this));
    };


    GroupService.prototype.addFriend = function(toid, groupid) {

        $http({
            method: "POST",
            url: "/api/group/add?groupid=" + groupid + "&toid=" + toid,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {

            }
        }.bind(this));
    };

    GroupService.prototype.removeFriend = function(contact) {

        $http({
            method: "POST",
            url: "/api/group/remove?groupdataid=" + contact.groupdataid,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                tip(contact.nickname + ' has been removed from group!');
                contact.show = false;
            }
        }.bind(this));
    };

    return GroupService;
});


app.factory('ContactService', function($http, $window) {

    var ContactService = function() {
        this.Contacts = [];
        this.busy = false;
        this.sc = '';
        this.stop = false;
        this.searchstop = false;
        this.showResult = false;
        this.resultMsg = "No search result found!";
        this.showInviteLink = false;
        this.inviteSignupEmail = '';
        this.showFindFriendLoading = false;
        this.showFindGoogleFriendLoading = false;
        this.showFindFacebookFriendLoading = false;
        this.showFindTwitterFriendLoading = false;
        this.showResultDiv = false;
        this.tsahayluTeam = {};
    };

    ContactService.prototype.init = function() {
        if (this.stop) return;
        if (this.busy) return;

        this.busy = true;
        this.Contacts = [];
        $http.get("/api/friend/info").then(function(response) {
            var data = response.data;
            var contacts = data.Contacts;

            if (contacts) {
                for (var i = 0; i < contacts.length; i++) {
                    contacts[i].show = true;
                    if (!contacts[i].avatarURL)
                        contacts[i].avatarURL = mysteryManPhotoList[i % 12];
                    contacts[i].showButtons = true;
                }

                this.Contacts = contacts;
            }

            this.busy = false;
        }.bind(this));
    };

    ContactService.prototype.getFriends = function(GroupDTO) {

        $http({
            method: "GET",
            url: "/api/group/data?groupid=" + GroupDTO.id,
        }).then(function(response) {
            var data = response.data;
            var contacts = data.Contacts;
            if (contacts) {
                for (var i = 0; i < contacts.length; i++) {
                    contacts[i].show = true;
                    contacts[i].showButtons = true;

                }
            }
            this.Contacts = contacts;

        }.bind(this));

    };

    ContactService.prototype.deleteFriend = function(Contact) {

        $http({
            method: "POST",
            url: "/api/friend/delete?id=" + Contact.friendid,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                Contact.show = false;
                tip(Contact.nickname + ' has been deleted!');
            }

        }.bind(this));
    };

    ContactService.prototype.blockFriend = function(Contact) {

        var block;
        if (Contact.status == 1)
            block = true;
        else
            block = false;

        $http({
            method: "POST",
            url: "/api/friend/block?id=" + Contact.friendid + '&block=' + block,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                if (block) {
                    Contact.status = 2
                    tip(blocktip);
                } else {
                    Contact.status = 1;
                    tip(unblocktip);
                }
            }

        }.bind(this));
    };

    ContactService.prototype.updateFriendPopup = function(Contact) {

        var popup = !Contact.popup;

        $http({
            method: "POST",
            url: "/api/friend/popup?id=" + Contact.friendid + '&popup=' + popup,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                Contact.popup = popup;
                if (popup == true) tip(popuptip);
                if (popup == false) tip(unpopuptip);
            }

        }.bind(this));
    };

    ContactService.prototype.searchUser = function(word) {
        if (this.searchstop) return;
        if (this.busy) return;

        this.showFindFriendLoading = true;
        this.showResult = false;
        this.Contacts = [];

        this.busy = true;

        $http.get("/api/user/search?word=" + word).then(function(response) {
            var data = response.data;
            var contacts = data.Contacts;

            if (contacts.length == 0 && this.searchsc == '') {
                this.showResult = true;
                this.resultMsg = "No search result found!";

                if (/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(word)) {
                    this.showInviteLink = true;
                    this.inviteSignupEmail = 'Invite ' + word + ' to signup';
                }
            }
            for (var i = 0; i < contacts.length; i++) {
                contacts[i].show = true;

                if (contacts[i].friendid != null) {

                    if (contacts[i].status == 0) {
                        contacts[i].showInviteButton = true;
                        contacts[i].showEmail = true;
                    } else {
                        contacts[i].showButtons = true;
                        contacts[i].showInviteButton = false;
                        contacts[i].showEmail = false;
                    }
                } else {
                    contacts[i].showInviteButton = true;
                    contacts[i].showEmail = true;
                }
                if (!contacts[i].avatarURL)
                    contacts[i].avatarURL = mysteryManPhotoList[i % 12];
            }

            this.Contacts = contacts;

            if (this.searchsc == data.startCursor)
                this.searchstop = true

            this.searchsc = data.startCursor;

            this.busy = false;
            this.showFindFriendLoading = false;
        }.bind(this));
    };

    ContactService.prototype.inviteFriendByEmail = function(email) {

        $http({
            method: "POST",
            url: "/api/friend/inviteemail?nickname=''&email=" + email,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                tip("A email invitation has been sent to " + email + "!");
            }

        }.bind(this));

    };

    ContactService.prototype.inviteFriendByUser = function(Contact) {
        $http({
            method: "POST",
            url: "/api/friend/invite?toid=" + Contact.id,
        }).then(function(response) {
            var msg = response.data;
            if (msg == "true") {
                tip("A friend invitation has been sent to " + Contact.nickname + "!");
            }

        }.bind(this));
    };

    ContactService.prototype.parseContacts = function(contacts) {

        if (contacts) {

            if (contacts.length > 0) {
                for (var i = 0; i < contacts.length; i++) {
                    contacts[i].show = true;
                    contacts[i].showButtons = false;
                    contacts[i].showEmail = true;

                    if (contacts[i].friendid != null) {

                        if (contacts[i].status == 0) {
                            contacts[i].showInviteButton = true;
                            contacts[i].showEmail = true;
                        } else {
                            contacts[i].showButtons = true;
                            contacts[i].showInviteButton = false;
                            contacts[i].showEmail = false;
                        }
                    } else {
                        contacts[i].showInviteButton = true;
                        contacts[i].showEmail = true;
                    }

                    if (!contacts[i].avatarURL)
                        contacts[i].avatarURL = mysteryManPhotoList[i % 12];
                }
            } else {
                this.showResult = true;
                this.resultMsg = "No contact found!";
            }
            this.Contacts = contacts;
        } else {
            this.showResult = true;
            this.resultMsg = "No contact found!";
        }
        this.showResultDiv = true;
        this.showFindFriendLoading = false;
        this.showFindGoogleFriendLoading = false;
        this.showFindFacebookFriendLoading = false;
        this.showFindTwitterFriendLoading = false;

    };

    ContactService.prototype.handleOpenIDResponse = function(data) {
        this.Contacts = [];
        this.showFindFriendLoading = true;
        $http({
            method: "POST",
            url: '/api/find/' + this.provider_id + '?reOauth=true&is_return=true&' + data,
        }).then(function(response) {
            var data = response.data;
            var contacts = data.Contacts;
            this.parseContacts(contacts);
        }.bind(this));

    };

    ContactService.prototype.findFriends = function(provider_id) {

        this.showFindFriendLoading = true;
        this.showResult = false;
        this.showResultDiv = false;
        this.Contacts = [];

        if (provider_id == 'google')
            this.showFindGoogleFriendLoading = true;

        if (provider_id == 'facebook')
            this.showFindFacebookFriendLoading = true;

        if (provider_id == 'twitter')
            this.showFindTwitterFriendLoading = true;

        this.provider_id = provider_id;

        $http({
            method: "POST",
            url: '/api/find/' + this.provider_id,
        }).then(function(response) {
            var data = response.data;
            if (data.status == "ok") {
                var contacts = data.Contacts;
                this.parseContacts(contacts);
            }
            if (data.status == "fail") {
                var w = $window.open("/api/find/" + this.provider_id + "?reOauth=true", 'openid_popup', 'width=450,height=500,location=1,status=1,resizable=yes');
                var coords = getCenteredCoords(450, 500);
                w.moveTo(coords[0], coords[1]);
            }
            if (data.status == "error") {
                this.showResultDiv = true;
                this.showResult = true;
                this.resultMsg = data.msg;
                this.showFindFriendLoading = false;
                this.showFindGoogleFriendLoading = false;
                this.showFindFacebookFriendLoading = false;
                this.showFindTwitterFriendLoading = false;
            }

        }.bind(this), function() {

        });


    };


    ContactService.prototype.getTsahayluTeamInfo = function() {

        // this.tsahayluTeam.id = 1000000;
        // this.tsahayluTeam.avatarURL = "/images/logo_col.png";
        // this.tsahayluTeam.nickname = "Tsahaylu Team";
        // this.tsahayluTeam.status = 1;
        // this.tsahayluTeam.popup = true;
        $http({
            method: "get",
            url: '/api/friend/tsahayluteam',
        }).then(function(response) {
            var data = response.data;

            this.tsahayluTeam.id = data.id;
            this.tsahayluTeam.avatarURL = data.avatarURL;
            this.tsahayluTeam.nickname = data.nickname;
            this.tsahayluTeam.status = data.status;
            this.tsahayluTeam.popup = data.popup;
            this.tsahayluTeam.friendid = data.friendid;
        }.bind(this), function() {

        });

    };


    return ContactService;
});