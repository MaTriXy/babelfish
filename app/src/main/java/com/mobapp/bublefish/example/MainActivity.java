package com.mobapp.bublefish.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.mobapp.bublefish.BabelFish;
import com.mobapp.bublefish.network.GoogleTranslateWithTokenModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final String TOKEN = "YOUR_TOKEN_GOING_HERE";

    @BindView(R.id.edit_text) EditText editText;
    @BindView(R.id.text) TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BabelFish.get().setLanguage("he");
        //example of using google translate with token
//        BabelFish.get().setLanguage("he").setTranslate(new GoogleTranslateWithTokenModule(TOKEN));
    }

    @OnClick(R.id.translate)
    public void onTranslateClick(){
        BabelFish.get().translate(textView, editText.getText().toString());
    }
}
