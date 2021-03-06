package upc.edu.pe.task;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import upc.edu.pe.adapter.HistorialAdapter;
import upc.edu.pe.proyecto.MainActivity;
import upc.edu.pe.proyecto.MenuActivity;
import upc.edu.pe.proyecto.R;
import upc.edu.pe.type.Cliente;
import upc.edu.pe.type.Distrito;
import upc.edu.pe.type.Pedido;
import upc.edu.pe.utils.HttpClientUtil;

/**
 * Created by Miguel Cardoso on 09/10/2015.
 */
public class HistorialTask extends AsyncTask<String,Void,String> {

    private Context context;
    ListView listView;
    ArrayAdapter arrayAdapter;

    //Variables
    private Gson json = new Gson();
    private List<Pedido> listPedidos;

    public HistorialTask(Context context,ListView listView, ArrayAdapter arrayAdapter) {
        this.context = context;
        this.listView = listView;
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpClientUtil RestClient = new HttpClientUtil();
        String mensaje="OK";
        try {
            /*String result = RestClient.GET("pedidos/todo/"+params[0]);
            if(result != null || !result.isEmpty()){
                Type type = new TypeToken<List<Pedido>>(){}.getType();
                listPedidos = json.fromJson(result, type);
                mensaje = result;
            }*/
            listPedidos = new ArrayList<Pedido>();
            Pedido pedido = new Pedido();
            pedido.setCliente(new Cliente());
            pedido.getCliente().setId_cliente(1);
            pedido.setDistrito(new Distrito());
            pedido.getDistrito().setId_distrito(2);
            pedido.getDistrito().setNombre("San Miguel");

            pedido.setDireccion("Av.La Marina 2014");
            pedido.setCantidad(20);
            pedido.setEstado("A");
            pedido.setFecha("2016-01-20 10:45:02");

            listPedidos.add(pedido);

            pedido = new Pedido();
            pedido.setCliente(new Cliente());
            pedido.getCliente().setId_cliente(1);
            pedido.setDistrito(new Distrito());
            pedido.getDistrito().setId_distrito(2);
            pedido.getDistrito().setNombre("San Miguel");

            pedido.setDireccion("Av.La Paz 03");
            pedido.setCantidad(100);
            pedido.setEstado("P");
            pedido.setFecha("2016-02-05 15:45:02");

            listPedidos.add(pedido);


        } catch (Exception e) {
            mensaje = null;
            e.printStackTrace();
            Log.d("Error en Task Pedido:", e.getMessage());
        }
        return mensaje;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null){
            arrayAdapter = new HistorialAdapter(context,listPedidos);
            listView.setAdapter(arrayAdapter);

            if(listPedidos.isEmpty()){
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle(R.string.dialog_header);
                dialog.setMessage("No tiene historial para consultar.");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(context, MenuActivity.class);
                            context.startActivity(i);
                        }
                    });
                dialog.show();
            }

        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle(R.string.dialog_header);
            dialog.setMessage("Error en cargar Historial de Pedidos.");
            dialog.show();
        }
    }
}
