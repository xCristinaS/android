package c.ejercicio39_firebase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseRecyclerAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements MyAdaptador.OnItemClickListener{

    private static final String DIR_MYBDD = "https://myfirebase01.firebaseio.com/mensajes/";
    EditText txtMensaje;
    Firebase myFirebase, refMensaje;
    String idMensaje = "-KA9TrDOOhAVutjXpo5i";
    ArrayList<Mensaje> datos = new ArrayList<>();
    RecyclerView lstMensajes;
    MyAdaptador mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Firebase refNueva;
                if (!TextUtils.isEmpty(txtMensaje.getText().toString())) {
                    refNueva = myFirebase.push();
                    idMensaje = refNueva.getKey();
                    refNueva.setValue(new Mensaje(idMensaje, txtMensaje.getText().toString()));
                }
            }
        });
    }

    @Override
    public void onItemClick(Mensaje mensaje, String key, int position) {
        Toast.makeText(this,mensaje.getMensaje() + " " + key,Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        lstMensajes = (RecyclerView) findViewById(R.id.lstMensajes);
        lstMensajes.setLayoutManager(new LinearLayoutManager(this));
        myFirebase = new Firebase(DIR_MYBDD);
        mAdapter = new MyAdaptador(myFirebase.orderByChild("mensaje"));
        mAdapter.setOnItemClickListener(this);
        lstMensajes.setAdapter(mAdapter);

        // Se crea el helper.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            // Cuando se detecta un gesto swipe to dismiss.
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.getRef(viewHolder.getAdapterPosition()).removeValue();
            }
        });
        itemTouchHelper.attachToRecyclerView(lstMensajes);

        /*
        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtMensaje.setText(dataSnapshot.getValue(Mensaje.class).getMensaje());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        */
        /*
        myFirebase.orderByChild("mensaje").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        */
        /*
        Firebase refMensaje = new Firebase(DIR_MYBDD + idMensaje);
        refMensaje.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    Mensaje m = dataSnapshot.getValue(Mensaje.class);
                    lblMensaje.setText(m.getMensaje());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
