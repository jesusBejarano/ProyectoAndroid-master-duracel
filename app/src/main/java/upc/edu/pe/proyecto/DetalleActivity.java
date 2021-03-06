package upc.edu.pe.proyecto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import upc.edu.pe.task.DetalleTask;
import upc.edu.pe.task.HistorialTask;

/**
 * Created by Miguel Cardoso on 10/10/2015.
 */
public class DetalleActivity extends Activity {
    //Variables
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private Button btnRegresar;
    //Otros
    public String pedidoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_pedido_main);
        //Obetener Intent
        Intent intent = getIntent();
        //Extrayendo el extra de tipo cadena
        pedidoId = intent.getStringExtra("pedidoId");
        //Inicializamos los tipos de la variables
        btnRegresar = (Button) findViewById(R.id.btnRegresarDetalle);
        listView = (ListView) findViewById(R.id.listaDetallePedido);

        new DetalleTask(DetalleActivity.this,listView,arrayAdapter).execute(pedidoId);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarActivity(MenuActivity.class);
            }
        });

    }

    private void mostrarActivity(Class view){
        Intent i = new Intent(this, view);
        startActivity(i);
    }
}
