package betianaminio.facebooklogin.interfaces;

/**
 * Created by Betiana G. Miño on 09/03/2017.
 */

public interface IApplicationListener {

    void onTaskCompleted();
    void onFailedToCompleteTask(String error);
}
