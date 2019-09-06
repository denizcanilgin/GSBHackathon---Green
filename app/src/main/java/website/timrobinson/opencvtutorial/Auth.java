package website.timrobinson.opencvtutorial;

import android.app.Activity;
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

public class Auth extends Activity implements View.OnClickListener {

    private EditText et_mail;
    private EditText et_pass;
    private ImageView bt_action;
    private Switch sw_logOrReg;

    private Boolean logOrReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

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

                Toast.makeText(getApplicationContext()," logOrReg : " + logOrReg , 0 ).show();

                break;

        }

    }
}
