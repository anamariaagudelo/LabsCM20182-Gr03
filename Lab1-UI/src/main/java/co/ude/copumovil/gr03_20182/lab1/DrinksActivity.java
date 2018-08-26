package co.ude.copumovil.gr03_20182.lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinksActivity extends AppCompatActivity {
    EditText editText_named, editText_priced, editText_ingredientD;
    TextView textView_Result;

    ImageView imageDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        imageDrink= (ImageView) findViewById(R.id.imageVew_drink);
        editText_named = (EditText) findViewById(R.id.ediTex_nameD);
        editText_priced = (EditText) findViewById(R.id.ediText_priceD);
        editText_ingredientD = (EditText) findViewById(R.id.ediText_ingredientsD);
        textView_Result = (TextView) findViewById(R.id.textView_addsD);

        loadPreferences();
    }



    public void onClickImageD(View view)  {
        cargarImagen();
    }
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imageDrink.setImageURI(path);

        }
    }

    public void onClick(View view){

        switch (view.getId()){

            case R.id.button_saveD:
                savePreferences();

                break;
        }

    }

    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("fileXML", Context.MODE_PRIVATE);
        String name = editText_named.getText().toString();
        String price = editText_priced.getText().toString();
        String ingredient = editText_ingredientD.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Nombre : ", name);
        editor.putString("Precio : ", price);
        editor.putString("Los ingredientes son: ", ingredient);

        textView_Result.setText("Nombre :"+name+"\nPrecio :"+price+"\nIngredientes :"+ingredient);
        editor.commit();

    }
    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("fileXML", Context.MODE_PRIVATE);
        String name = preferences.getString("Nombre :", "No hay información ingresada");
        String price = preferences.getString("Precio :", "No hay información ingresada");
        String ingredient = preferences.getString("Ingredientes :", "No hay información ingresada");

        textView_Result.setText("Nombre "+name+"\nPrecio "+price+"\nIngredientes "+ingredient);
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
                editText_named.setText("");
                editText_priced.setText("");
                editText_ingredientD.setText("");
                break;

            case R.id.menu_out:
                finish();

            default: super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


}
