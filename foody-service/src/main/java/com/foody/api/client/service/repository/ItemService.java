package com.foody.api.client.service.repository;

import com.foody.api.client.model.entities.Item;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ItemService {
    public static final String COL_NAME="items";

    public String saveItemDetails(Item item) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        item.initialize();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(item.getId().toString()).set(item);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Item getItemDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Item item = null;

        if(document.exists()) {
            item = document.toObject(Item.class);
            return item;
        }else {
            return null;
        }
    }

    public List<Item> getItems() throws InterruptedException, ExecutionException {
        List<Item> items = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        //Iterable<DocumentReference> documentReferences= dbFirestore.collection(COL_NAME).listDocuments();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Item.class));
            items.add(document.toObject(Item.class));
        }

        return items;
    }

    public List<Item> getItemsByRestaurantId(String restaurantId) throws InterruptedException, ExecutionException {
        List<Item> items = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).whereEqualTo("restaurantId",restaurantId).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Item.class));
            items.add(document.toObject(Item.class));
        }

        return items;
    }

    public String updateItemDetails(Item item) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(item.getId().toString()).set(item);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteItem(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Item ID "+id+" has been deleted";
    }
}
