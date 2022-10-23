package me.tsaheylu.serviceImpl;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MessageNumDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Group;
import me.tsaheylu.model.User;
import me.tsaheylu.service.PushChannelService;
import me.tsaheylu.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PushChannelServiceImpl implements PushChannelService {
    private static final String FIREBASEJSONPATH = "me/tsaheylu/config/tsahayluapp-firebase.json";


    private static FirebaseAuth defaultAuth = null;
    private static FirebaseDatabase defaultDatabase = null;
    private static FirebaseApp defaultApp = null;
    private final UserService userService;

    private void initFirebase() {
        if (defaultApp == null) {
            try {

                ClassPathResource classPathResourcePub = new ClassPathResource("tsahayluapp-firebase.json");

                InputStream inStream = classPathResourcePub.getInputStream();

//                FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Oswin\\Downloads\\tsahayluapp-firebase-adminsdk-604fk-cc3dcbbe0b.json");

                FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(inStream)).setDatabaseUrl("https://tsahayluapp.firebaseio.com").build();

                defaultApp = FirebaseApp.initializeApp(options);
                defaultAuth = FirebaseAuth.getInstance();
                defaultDatabase = FirebaseDatabase.getInstance();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public String setupChannel(Long id) {
        // TODO Auto-generated method stub

        initFirebase();

        User u = userService.Get(id);
        Date signuptime = u.getSignuptime();
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("signuptime", signuptime);

        String customToken;
        try {
            customToken = defaultAuth.createCustomToken(id.toString(), claims);
            return customToken;
        } catch (FirebaseAuthException e) {
            // TODO Auto-generated catch block
        }

        return null;
    }

    @Override
    public void sendMsgNumToChannel(MessageNumDTO msgnum) {
        String path = String.format("/channels/%s/MsgNum", msgnum.getToid());
        firebasePut(path, msgnum);
    }

    @Override
    public void removeMsgNumFromChannel(MessageNumDTO msgnum) {

        String path = String.format("/channels/%s/MsgNum", msgnum.getToid());
        firebaseDelete(path);
    }

    @Override
    public void removeFromChannel(FavURL favurl) {

        String path = String.format("/channels/%s/FavURLShows/%s", favurl.getToid(), favurl.getId());
        firebaseDelete(path);
    }

    @Override
    public void sendToChannel(FavURLDTO favurlDto) {
        Map<String, Object> fs = new HashMap<String, Object>();
        fs.put(favurlDto.getId().toString(), favurlDto);
        Map<String, Object> id = new HashMap<String, Object>();
        id.put(favurlDto.getToid().toString(), fs);
        Map<String, Object> channels = new HashMap<String, Object>();
        channels.put("channels", id);

        String path = String.format("/channels/%s/FavURLShows/%s", favurlDto.getToid(), favurlDto.getId());
        firebasePut(path, favurlDto);
    }

    @Override
    public void sendGroupsToChannel(int op, Group group) {
/*        List<Group> list = gd.getGroupsByType(group.getId(), GroupType.SEND);

        if (op == 1) {
            list.add(group);
        } else if (op == 2) {
            list.remove(group);
        } else if (op == 3) {
            for (int j = 0, len = list.size(); j < len; j++) {
                Group og = list.get(j);
                if (og.getId() == group.getId()) {
                    list.remove(j);
                    list.add(group);
                    break;
                }
            }
        }

        String path =
                String.format("%s/channels/%s/Groups", Constants.FIREBASEDBURL, group.getFromid());
        firebasePut(path, list);*/
    }

    @Override
    public void removeGroupsFromChannel(Group group) {
        String path = String.format("%s/channels/%s/Groups", Constants.FIREBASEDBURL, group.getFromid());
        firebaseDelete(path);
    }

    public void firebasePut(String path, Object object) {
        initFirebase();
        DatabaseReference ref = defaultDatabase.getReference(path);
        ref.setValueAsync(object);
    }

    public void firebaseDelete(String path) {
        initFirebase();
        defaultDatabase.getReference(path).removeValueAsync();
    }
}
