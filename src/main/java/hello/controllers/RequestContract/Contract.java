package hello.controllers.RequestContract;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import hello.utils.SpringContext;
import hello.utils.beans.FireBaseCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class Contract
{

    //Contracts allow us to be sure the request comes with a token and that this
    //token will be validate with our fcu bean.

    protected FireBaseCustomUtils fcu;

    public String token;
    public FirebaseToken hydrated_token;

    public Contract() {
        this.fcu = (FireBaseCustomUtils) SpringContext.getAppContext().getBean("fireBaseCustomUtils");
    }

    public boolean validateToken() throws FirebaseAuthException {
        FirebaseToken ft = fcu.validateToken(this.token);
        if(ft == null)
            return false;
        this.hydrated_token = ft;
        return true;

    }

    public Contract(String token) {
        this.fcu = (FireBaseCustomUtils)SpringContext.getAppContext().getBean("fireBaseCustomUtils");
        this.token = token;
    }

    public FireBaseCustomUtils getFcu() {
        return fcu;
    }

    public void setFcu(FireBaseCustomUtils fcu) {
        this.fcu = fcu;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public FirebaseToken getHydrated_token() {
        return hydrated_token;
    }

    public void setHydrated_token(FirebaseToken hydrated_token) {
        this.hydrated_token = hydrated_token;
    }


}
