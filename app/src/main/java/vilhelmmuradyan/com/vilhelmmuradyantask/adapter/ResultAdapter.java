package vilhelmmuradyan.com.vilhelmmuradyantask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import vilhelmmuradyan.com.vilhelmmuradyantask.R;
import vilhelmmuradyan.com.vilhelmmuradyantask.activity.PersonViewActivity;
import vilhelmmuradyan.com.vilhelmmuradyantask.model.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private List<Result> resultsList;

    public ResultAdapter(Context context, List<Result> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    public interface OnResultAdapterListener {
        void onListener();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, int position) {
        holder.txtFirstName.setText(resultsList.get(position).getName().getFirst());
        holder.txtLastName.setText(resultsList.get(position).getName().getLast());
        holder.txtState.setText(resultsList.get(position).getLocation().getState());
        holder.txtCity.setText(resultsList.get(position).getLocation().getCity());
        holder.txtPhone.setText(resultsList.get(position).getPhone());
        Picasso.with(context).load(resultsList.get(position).getPicture().getLarge()).into(holder.imgLarge);

        holder.setOnResultAdapterListener(new OnResultAdapterListener() {
            @Override
            public void onListener() {
                Intent intent = new Intent(context, PersonViewActivity.class);
                intent.putExtra("result", resultsList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    class ResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private OnResultAdapterListener onResultAdapterListener;
        private TextView txtFirstName;
        private TextView txtLastName;
        private TextView txtState;
        private TextView txtCity;
        private TextView txtPhone;
        private ImageView imgLarge;

        ResultViewHolder(View itemView) {
            super(itemView);
            txtFirstName = itemView.findViewById(R.id.txt_first_name);
            txtLastName = itemView.findViewById(R.id.txt_last_name);
            txtState = itemView.findViewById(R.id.txt_state);
            txtCity = itemView.findViewById(R.id.txt_city);
            txtPhone = itemView.findViewById(R.id.txt_phone);
            imgLarge = itemView.findViewById(R.id.img_large_image);

            itemView.setOnClickListener(this);
        }

        private void setOnResultAdapterListener(OnResultAdapterListener onResultAdapterListener) {
            this.onResultAdapterListener = onResultAdapterListener;
        }

        @Override
        public void onClick(View v) {
            onResultAdapterListener.onListener();
        }
    }
}
