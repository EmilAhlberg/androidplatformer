package game.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.example.emil.app.R;

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void newGame(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityHandler.class);
        intent.putExtra("ActivityConstant", ActivityConstants.GAME);
        intent.putExtra("level", 1); //start at level 1
        startActivity(intent);
        finish(); //Stänger Activityn
    }
}
