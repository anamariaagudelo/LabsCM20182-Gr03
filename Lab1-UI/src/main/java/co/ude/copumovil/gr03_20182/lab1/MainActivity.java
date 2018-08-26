package co.ude.copumovil.gr03_20182.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static co.ude.copumovil.gr03_20182.lab1.R.*;

public class MainActivity extends AppCompatActivity {
    Button food;
    Button drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        food = (Button) findViewById(id.butttonFood);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food = new Intent(MainActivity.this, FoodsActivity.class);
                startActivity(food);
            }
        });
        drink = (Button) findViewById(id.buttonDrink);
        drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent drink = new Intent(MainActivity.this, DrinksActivity.class);
                startActivity(drink);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        switch (id){
            case R.id.menu_clear:
                return true;

            case R.id.menu_out:
                finish();

            default: super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

}
