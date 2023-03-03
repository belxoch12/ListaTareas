package com.example.listadetareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    List<String> data = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data.add("Elemento 1");
        data.add("Elemento 2");
        data.add("Elemento 3");

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this,data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        FloatingActionButton FABButton = findViewById(R.id.fab_btn_add);

        FABButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Nueva tarea nuevo
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Agregar tarea");
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                //Botonagregar
                builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newItemDescription = input.getText().toString();
                            data.add(newItemDescription);
                            adapter.notifyItemInserted(data.size() - 1);
                            Toast.makeText(MainActivity.this, "Tarea agregada", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                    }
                });
                //Botonconcelar
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Mostrar el diálogo al usuario
                builder.show();
            }
        });

    }
}