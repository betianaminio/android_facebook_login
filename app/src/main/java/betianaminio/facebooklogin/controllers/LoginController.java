package betianaminio.facebooklogin.controllers;

import android.content.Intent;

import betianaminio.facebooklogin.interfaces.IApplicationListener;
import betianaminio.facebooklogin.models.FacebookProvider;


/**
 * Created by Betiana G. Miño on 09/03/2017.
 */

public class LoginController {

    public LoginController(){

    }

    public void initializeLoginProvider(IApplicationListener applicationListener){

        FacebookProvider.getInstance().initialize(applicationListener);

    }

    public void onActivityResult( int request_code, int result_code, Intent data){

        FacebookProvider.getInstance().onActivityResult(request_code,result_code,data);
    }
}
