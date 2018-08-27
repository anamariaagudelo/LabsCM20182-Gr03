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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class FoodsActivity extends AppCompatActivity {
    ImageView imageFood;

    EditText editText_namef,editText_pricef, editText_ingredientf,editText_time;
    CheckBox checkBox_schedulerM, checkBox_schedulerA, checkBox_schedulerN;
    RadioButton radioButton_type_1, radioButton_type_2;
    TextView textView_Resultf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        imageFood = (ImageView) findViewById(R.id.imageVew_food);
        editText_namef = (EditText) findViewById(R.id.ediTex_name);
        checkBox_schedulerA = (CheckBox) findViewById(R.id.checkBox_afternoon);
        checkBox_schedulerM = (CheckBox) findViewById(R.id.checkBox_morning);
        checkBox_schedulerN = (CheckBox) findViewById(R.id.checkBox_nigth);
        radioButton_type_1 = (RadioButton) findViewById(R.id.radioButton_starers);
        radioButton_type_2 = (RadioButton) findViewById(R.id.radioButton_main_dish);
        editText_time = (EditText) findViewById(R.id.time);
        editText_pricef = (EditText) findViewById(R.id.ediText_price);
        editText_ingredientf = (EditText) findViewById(R.id.ediText_ingredients);
        textView_Resultf = (TextView) findViewById(R.id.textView_adds);

        loadPreferences();
    }

    public void onClickImage(View view) {
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
            imageFood.setImageURI(path);

        }
    }


    public void onClick(View view){

        switch (view.getId()){
            case R.id.button_saveF:
                savePreferences();
                break;
        }

    }
    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("fileXML", Context.MODE_PRIVATE);

        String name = editText_namef.getText().toString();
        String price = editText_pricef.getText().toString();
        String ingredient = editText_ingredientf.getText().toString();
        String time= editText_time.getText().toString();
        String schedulerf = "";
        if(checkBox_schedulerM.isChecked()){
            schedulerf += "Mañana";
        }else if (checkBox_schedulerA.isChecked()){
            schedulerf += "Tarde";
        }else if(checkBox_schedulerN.isChecked()){
            schedulerf += "Noche";
        }
        String typef = "";
        if(radioButton_type_1.isChecked()){
            typef += "Entrada";
        }else if(radioButton_type_2.isChecked()){
            typef += "Plato fuerte";
        }


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Nombre : ", name);
        editor.putString("El horario seleccionado es: ", schedulerf);
        editor.putString("Tipo: ", name);
        editor.putString("Tiempo: ", typef);
        editor.putString("Precio : ", price);
        editor.putString("Los ingredientes son: ", ingredient);

        textView_Resultf.setText("Nombre :"+name+"\nHorario: "+schedulerf+"\nTipo: "+typef+"\nTiempo :"+time+"\nPrecio :"+price+"\nIngredientes :"+ingredient);

        editor.commit();

    }
    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences("fileXML", Context.MODE_PRIVATE);
        String name = preferences.getString("Nombre :", "No hay información ingresada");
        String schedulerf = preferences.getString("Horario: ", "No hay información seleccionada");
        String typef = preferences.getString("Tipo : ", "No hay información seleccionada");
        String time = preferences.getString("Tempo : ", "No hay información seleccionada");
        String price = preferences.getString("Precio :", "No hay información ingresada");
        String ingredient = preferences.getString("Ingredientes :", "No hay información ingresada");

        textView_Resultf.setText("Nombre :"+name+"\nHorario: "+schedulerf+"Tipo: "+typef+"\nTiempo :"+time+"\nPrecio :"+price+"\nIngredientes :"+ingredient);
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
                editText_namef.setText("");
                editText_time.setText("");
                editText_pricef.setText("");
                editText_ingredientf.setText("");
                break;
            case R.id.menu_out:
                finish();

            default: super.onOptionsItemSelected(item);
        }


        return super.onOptionsItemSelected(item);
    }

}
