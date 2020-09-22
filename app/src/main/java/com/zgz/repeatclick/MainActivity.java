package com.zgz.repeatclick;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.zgz.repeatclick.annotation.FastClick;

public class MainActivity extends AppCompatActivity {
    private Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    @FastClick(value =1500)
    private void login() {
        //do something
        Log.e("zhouguizhi","登录逻辑");
    }
}