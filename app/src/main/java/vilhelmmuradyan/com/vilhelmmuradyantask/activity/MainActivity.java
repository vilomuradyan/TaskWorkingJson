package vilhelmmuradyan.com.vilhelmmuradyantask.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vilhelmmuradyan.com.vilhelmmuradyantask.R;
import vilhelmmuradyan.com.vilhelmmuradyantask.adapter.ResultAdapter;
import vilhelmmuradyan.com.vilhelmmuradyantask.model.Result;
import vilhelmmuradyan.com.vilhelmmuradyantask.model.Results;
import vilhelmmuradyan.com.vilhelmmuradyantask.network.ResultServer;
import vilhelmmuradyan.com.vilhelmmuradyantask.network.RetrofitIstance;

public class MainActivity extends AppCompatActivity{

    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Result> resultsList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ResultServer server = RetrofitIstance.getRetrofit().create(ResultServer.class);
        Call<Results> call = server.getResults();

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull Call<Results> call, @NonNull Response<Results> response) {
                resultsList = Objects.requireNonNull(response.body()).getResults();
                getInfo();
            }

            @Override
            public void onFailure(@NonNull Call<Results> call, @NonNull Throwable t) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                startActivity(getIntent());
                close();
            }
        });
    }

    public void close(){
                swipeRefreshLayout.setRefreshing(false);
            }

    private void getInfo() {
        ResultAdapter adapter = new ResultAdapter(MainActivity.this,resultsList);
        recyclerView.setAdapter(adapter);
    }

}
