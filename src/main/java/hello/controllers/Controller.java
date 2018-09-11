package hello.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuthException;
import hello.controllers.RequestContract.Contract;
import hello.controllers.RequestContract.GameCollectionContract;

import java.io.IOException;

public class Controller {

    Contract deserialize(String json, String contractType) throws FirebaseAuthException {

        ObjectMapper om = new ObjectMapper();

        Contract gcag = null;

        try {
            gcag = (Contract) Class.forName(contractType).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            try {
                gcag = (Contract) om.readValue(json,  Class.forName(contractType));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(gcag.validateToken() == true) {
                return gcag;
            }
            else{
                //TODO Throw invalid token then http error
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
