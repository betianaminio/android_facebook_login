package betianaminio.facebooklogin.controllers;

import betianaminio.facebooklogin.interfaces.IApplicationListener;
import betianaminio.facebooklogin.interfaces.IRetrieveUserData;
import betianaminio.facebooklogin.models.FacebookProvider;
import betianaminio.facebooklogin.models.UserLogged;


/**
 * Created by Betiana G. Miño on 09/03/2017.
 */

public class MainController {

    private UserLogged mUserLogged;

    public MainController(final IApplicationListener applicationListener){

        FacebookProvider.getInstance().retrieveUserData(new IRetrieveUserData() {
            @Override
            public void onRetrieveDataSuccess(UserLogged userLogged) {

                MainController.this.mUserLogged = userLogged;

                applicationListener.onTaskCompleted();
            }

            @Override
            public void onFailedToRetrieveData(String error) {

                applicationListener.onFailedToCompleteTask(error);
            }
        });

    }

    public void logOut(){

        FacebookProvider.getInstance().logOut();
    }

    public String getUserName(){

        if ( this.mUserLogged != null )
            return this.mUserLogged.getName();

        return null;
    }

    public String getUserPhoto(){

        if ( this.mUserLogged != null)
            return this.mUserLogged.getUrlPhoto();

        return null;
    }
}
