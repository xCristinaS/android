package cristina.ejercicio33_swiperefreshlayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnRefreshListener{

    RecyclerView lstFechas;
    SwipeRefreshLayout swipeRefresh;
    Adaptador mAdaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initViews();
    }

    private void initViews(){
        configRecyclerView();
        configSwipeRefreshLayout();
    }

    private void configSwipeRefreshLayout() {
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }


    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);
        new Tarea().execute();
    }

    private void configRecyclerView(){
        lstFechas = (RecyclerView) findViewById(R.id.lstFechas);
        mAdaptador = new Adaptador();
        lstFechas.setAdapter(mAdaptador);
        lstFechas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstFechas.setItemAnimator(new DefaultItemAnimator());
    }

    private void agregarFecha(){
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        //((Adaptador)lstFechas.getAdapter()).addItem(formato.format(new Date()),((Adaptador)lstFechas.getAdapter()).getFechas().isEmpty()?0:((Adaptador)lstFechas.getAdapter()).getFechas().size());
        mAdaptador.addItem(formato.format(new Date()), mAdaptador.getFechas().isEmpty() ? 0 : mAdaptador.getFechas().size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class Tarea extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            swipeRefresh.setRefreshing(false);
            agregarFecha();
        }
    }
}
