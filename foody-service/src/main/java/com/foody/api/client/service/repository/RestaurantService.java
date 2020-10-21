package com.foody.api.client.service.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.foody.api.client.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RestaurantService {
    public static final String COL_NAME="restaurants";

    public String saveRestaurantDetails(Restaurant restaurant) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document().set(restaurant);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Restaurant getRestaurantDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Restaurant restaurant = null;

        if(document.exists()) {
            restaurant = document.toObject(Restaurant.class);
            return restaurant;
        }else {
            return null;
        }
    }

    public List<Restaurant> getRestaurants() throws InterruptedException, ExecutionException {
        List<Restaurant> restaurants = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences= dbFirestore.collection(COL_NAME).listDocuments();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFirestore.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            //System.out.println(document.getId() + " => " + document.toObject(Restaurant.class));
            restaurants.add(document.toObject(Restaurant.class));
        }

        return restaurants;
    }

    public String updateRestaurantDetails(Restaurant restaurant) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(restaurant.getId()).set(restaurant);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteRestaurant(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id).delete();
        return "Document with Restaurant ID "+id+" has been deleted";
    }
}
