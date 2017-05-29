package designzoos.indjcustom.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import designzoos.indjcustom.R;

public class Intro extends AppCompatActivity {
    private Intent login;
    private Intent join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        login = new Intent(getApplicationContext(), Login.class);
        join = new Intent(getApplicationContext(), Join.class);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLogin:
                startActivity(login);
                break;
            case R.id.imgJoin:
                startActivity(join);
                break;
        }
    }
}
