package hello.utils.beans;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

@Component
public class FireBaseCustomUtils {

    public FireBaseCustomUtils() {
        System.out.println("FIREBASE CUTOM INSTANCIATED");
        //TODO place path and url in properties
        URL classLoader = getClass().getResource("/firebase-sdk.json");
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream(classLoader.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://test-7d3f1.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
    }

    public String testmethode(){
        return "Test is wow";
    }

    public FirebaseToken validateToken(String token){
        //TODO Handle the not valid token (disconnect)
        FirebaseToken decodedToken = null;
        FirebaseAuth fa = FirebaseAuth.getInstance();
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return decodedToken;
    }
}
