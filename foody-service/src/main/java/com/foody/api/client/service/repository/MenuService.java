package com.foody.api.client.service.repository;

import com.foody.api.client.model.entities.Menu;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



//No creo que sea necesaria esta clase o en tal caso debe ser reformulada.
@Service
public class MenuService {
    public static final String COL_NAME="menus";

    public String saveMenuDetails(Menu menu) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        menu.initialize();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(menu.getId().toString()).set(menu);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Menu getMenuDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Menu menu = null;

        if(document.exists()) {
            menu = document.toObject(Menu.class);
            return menu;
        }else {
            return null;
        }
    }

    public List<Menu> getMenus() throws InterruptedException, ExecutionException {
        List<Menu> menus = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences= dbFirestore.collection(COL_NAME).listDocuments();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Menu.class));
            menus.add(document.toObject(Menu.class));
        }

        return menus;
    }

    public String updateMenuDetails(Menu menu) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(menu.getId().toString()).set(menu);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteMenu(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Menu ID "+id+" has been deleted";
    }
}
