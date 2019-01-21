package vilhelmmuradyan.com.vilhelmmuradyantask.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import vilhelmmuradyan.com.vilhelmmuradyantask.R;
import vilhelmmuradyan.com.vilhelmmuradyantask.model.Result;

public class PersonViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_view);
        Toolbar toolbar = findViewById(R.id.tool_bar_person);
        setSupportActionBar(toolbar);
        ImageView imgPerson = findViewById(R.id.img_person);
        TextView txtName = findViewById(R.id.txt_name);
        TextView txtAge = findViewById(R.id.txt_age);

        Result result = (Result) getIntent().getSerializableExtra("result");

        Picasso.with(this).load(result.getPicture().getLarge()).into(imgPerson);
        txtAge.setText(String.valueOf(result.getDob().getAge()));
        txtName.setText(result.getName().getFirst());
    }
}
