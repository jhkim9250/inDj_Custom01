package designzoos.indjcustom.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import designzoos.indjcustom.R;

public class Login extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    TextView btnLogin;
    TextView txtSingup;
    TextView txtLook;
    ImageView btnFacebook;
    ImageView btnKakao;
    ImageView btnGmail;
    TextView txtPolicy;

    String email;
    String password;

    Intent main;
    Intent singUp;

    CallbackManager callbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        txtSingup = (TextView) findViewById(R.id.txtSignup);
        txtLook = (TextView) findViewById(R.id.txtLook);
        btnFacebook = (ImageView) findViewById(R.id.btnFacebook);
        btnKakao = (ImageView) findViewById(R.id.btnKakao);
        btnGmail = (ImageView) findViewById(R.id.btnGmail);
        txtPolicy = (TextView) findViewById(R.id.txtPolicy);
        main = new Intent(getApplicationContext(), Main.class);
        singUp = new Intent(getApplicationContext(), Join.class);

    }

    public void viewOnclick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                loginProcess();
                break;
            case R.id.txtSignup:
                startActivity(singUp);
                break;
            case R.id.txtLook:
                break;
            case R.id.btnFacebook:
                loginFacebook();
                break;
            case R.id.btnKakao:
                break;
            case R.id.btnGmail:
                break;
        }
    }


    private void loginFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("페이스북 토큰", loginResult.getAccessToken().getToken());
                Log.d("페이스북 UserID", loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.e("onCancel", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("onError", "onError " + exception.getLocalizedMessage());
            }
        });
      /*  GraphRequest request = GraphRequest.newMeRequest(, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("페이스북 로그인 결과", response.toString());

                String email = null;
                try {
                    email = object.getString("email");
                    String name = object.getString("name");

                    Log.d("페이스북 이메일", email);
                    Log.d("페이스북 이름", name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void loginProcess() {
        email = txtEmail.getText().toString().trim();
        password = txtPassword.getText().toString().trim();

        if (email.equals("qwer1234")) {
            if (!password.equals("1234")) {
                Toast.makeText(this, "Email과 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            } else {
                startActivity(main);
            }
        } else {
            Toast.makeText(this, "가입된 Email이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
