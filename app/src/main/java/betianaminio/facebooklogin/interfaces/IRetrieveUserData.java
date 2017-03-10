package betianaminio.facebooklogin.interfaces;

import betianaminio.facebooklogin.models.UserLogged;

/**
 * Created by Betiana G. Miño on 09/03/2017.
 */

public interface IRetrieveUserData {

    void onRetrieveDataSuccess(UserLogged userLogged);
    void onFailedToRetrieveData(String error);
}
