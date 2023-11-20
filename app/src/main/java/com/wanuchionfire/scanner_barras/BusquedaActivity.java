package com.wanuchionfire.scanner_barras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BusquedaActivity extends AppCompatActivity {

    private EditText codeProducto;
    private ImageButton imgButtonBusqueda;
    private TextView nombreProducto, tipoProducto, fechaCreacin, lastModify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        codeProducto = findViewById(R.id.txtCodeVar);
        imgButtonBusqueda = findViewById(R.id.btnconsultar);
        nombreProducto = findViewById(R.id.lblNombrePr);
        tipoProducto = findViewById(R.id.lblTipoProducto);
        fechaCreacin = findViewById(R.id.lblFechaElav);
        lastModify = findViewById(R.id.lblLastModify);

        imgButtonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultaProductos();
            }
        });
    }

    private void consultaProductos() {
        try {
            Statement statement = conexionBD().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM E_Producto WHERE='"+codeProducto.getText().toString()+"'");
            if (resultSet.next()){
                nombreProducto.setText(resultSet.getString(4));
                tipoProducto.setText(resultSet.getString(6));
                fechaCreacin.setText(resultSet.getString(9));
                lastModify.setText(resultSet.getString(11));
            }
            codeProducto.setText("");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public Connection conexionBD() {

        Connection connection = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection= DriverManager.getConnection("jbdc.jtds:sqlserver://192.168.1.10;databaseName=CayFlowersdos;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return connection;
    }


}