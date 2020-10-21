package com.foody.api.client.service.repository.connectors;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FireBaseInitialize {
    @PostConstruct
    public void initialize() {
        try {

            final String serviceAccountKey = "C:\\Users\\ferod\\IdeaProjects\\Foody\\foody-service\\conf\\local\\serviceAccountKey.json";
            FileInputStream serviceAccount =
                    new FileInputStream(serviceAccountKey);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://foody-86257.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
