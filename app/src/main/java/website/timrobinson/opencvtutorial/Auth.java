package website.timrobinson.opencvtutorial;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Auth extends Activity implements View.OnClickListener {

    private EditText et_mail;
    private EditText et_pass;
    private ImageView bt_action;
    private Switch sw_logOrReg;

    private Boolean logOrReg = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser != null)redirect(parseUser);

        et_mail = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_password);
        bt_action =  (ImageView)findViewById(R.id.bt_action);
        bt_action.setOnClickListener(this);
        sw_logOrReg = findViewById(R.id.sw_logOrReg);


        sw_logOrReg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                logOrReg = b;
                Log.i("logOrReg" , b + "");
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bt_action:

                //Toast.makeText(getApplicationContext()," logOrReg : " + logOrReg , 0 ).show();

                action();

                break;

        }

    }

    private void action() {

        if(logOrReg) register();
        else login();

    }

    private void login() {

        String entered_mail = et_mail.getText().toString().trim();
        String entered_pass = et_pass.getText().toString().trim();

        ParseUser.logInInBackground(entered_mail, entered_pass, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {

                    Toast.makeText(Auth.this, "Successfully Logged In!", Toast.LENGTH_SHORT).show();
                    redirect(user);


                } else {

                    Toast.makeText(Auth.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void register() {

        String entered_mail = et_mail.getText().toString().trim();
        String entered_pass = et_pass.getText().toString().trim();

        final ParseUser user = new ParseUser();
        user.setUsername(entered_mail.substring(0,5));
        user.setPassword(entered_pass);
        user.setEmail(entered_mail);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if(e == null){

                    //Toast.makeText(Auth.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    redirect(user);

                }else{

                    Toast.makeText(Auth.this, "" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }

            }

        });

    }


    private void redirect(ParseUser parseUser){

        Intent intent = new Intent(this,Map.class);
        intent.putExtra("currentUser", parseUser.getEmail());
        startActivity(intent);

    }
}
