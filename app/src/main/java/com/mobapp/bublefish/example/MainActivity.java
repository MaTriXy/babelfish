package com.mobapp.bublefish.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.mobapp.bublefish.BabelFish;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text) EditText editText;
    @BindView(R.id.text) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BabelFish.get().setLanguage("he");
    }

    @OnClick(R.id.translate)
    public void onTranslateClick(){
        BabelFish.get().translate(textView, editText.getText().toString());
    }
}
