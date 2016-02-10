package upc.edu.pe.proyecto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import upc.edu.pe.adapter.CatalogoAdapter;
import upc.edu.pe.task.CatalogoTask;
import upc.edu.pe.type.Producto;

/**
 * Created by jesus on 7/02/2016.
 */
public class FragmentoProductos extends Fragment {


    //private RecyclerView recycler;
   // private RecyclerView.Adapter adapter;
   // private RecyclerView.LayoutManager lManager;
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adaptador;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.catalogo_main, container, false);


        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        new CatalogoTask(getActivity(),reciclador).execute("");



        /*adaptador = new CatalogoAdapter();
        reciclador.setAdapter(adaptador);*/

        /** Obtener el Recycler
        recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);

        new CatalogoTask(getActivity(),recycler,adapter).execute();**/

        return view;
    }
}
