package betianaminio.facebooklogin.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import betianaminio.facebooklogin.R;
import betianaminio.facebooklogin.controllers.LoginController;
import betianaminio.facebooklogin.interfaces.IApplicationListener;

public class LoginActivity extends AppCompatActivity {

    private LoginController mLoginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mLoginController = new LoginController();

        this.mLoginController.initializeLoginProvider(new IApplicationListener() {
            @Override
            public void onTaskCompleted() {

                goToMainActivity();
            }

            @Override
            public void onFailedToCompleteTask(String error) {

                Toast.makeText(LoginActivity.this,getString(R.string.error_message) + error, Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onActivityResult( int request_code, int result_code, Intent data){
        super.onActivityResult(request_code,result_code,data);

        this.mLoginController.onActivityResult(request_code,result_code,data);

    }

    private void goToMainActivity(){

        startActivity( new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }
}
