package betianaminio.facebooklogin.models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import betianaminio.facebooklogin.interfaces.IApplicationListener;
import betianaminio.facebooklogin.interfaces.IRetrieveUserData;

/**
 * Created by Betiana G. Miño on 09/03/2017.
 */

public class FacebookProvider {

    private static FacebookProvider s_instance;

    public static synchronized FacebookProvider getInstance(){

        if ( s_instance == null ){
            s_instance = new FacebookProvider();
        }

        return s_instance;
    }


    private CallbackManager mCallbackManager;

    public void initialize(final IApplicationListener applicationListener){

        this.mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                applicationListener.onTaskCompleted();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                applicationListener.onFailedToCompleteTask(error.getMessage());
            }
        });

    }

    public void onActivityResult( int request_code, int result_code, Intent intent){

        this.mCallbackManager.onActivityResult(request_code,result_code,intent);
    }

    public void retrieveUserData(final IRetrieveUserData iRetrieveUserData){

        Bundle params = new Bundle();
        params.putString("fields", "id,name,picture.type(large)");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();

                                if (data.has("picture") && data.has("name")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    String name = data.getString("name");

                                    UserLogged userLogged = new UserLogged();
                                    userLogged.setName(name);
                                    userLogged.setUrlPhoto(profilePicUrl);

                                    iRetrieveUserData.onRetrieveDataSuccess(userLogged);

                                }


                            } catch (Exception e) {
                                iRetrieveUserData.onFailedToRetrieveData(e.getLocalizedMessage());
                            }
                        }
                    }
                }).executeAsync();

    }

    public void logOut( ){

        LoginManager.getInstance().logOut();

    }

}
