package com.ahmadsapplication.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    Context context;
    ArrayList<DataList> dataLists;
    public DataAdapter(Context context, ArrayList<DataList> dataLists) {
        this.context = context;
        this.dataLists = dataLists;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_third_screen,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        holder.Bind(dataLists.get(position));
    }

    @Override
    public int getItemCount() {
        return dataLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fNameLname, email;
        private ImageView avatar;
        private LinearLayout layoutAdapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fNameLname = itemView.findViewById(R.id.txtFirstNameAndLastName);
            email = itemView.findViewById(R.id.txtEmail);
            avatar = itemView.findViewById(R.id.avatarImage);
            layoutAdapter = itemView.findViewById(R.id.linearAdapter);

        }
        public void Bind(DataList dataList){
            fNameLname.setText(dataList.getFirst_name()+" "+dataList.getLast_name());
            email.setText(dataList.getEmail());
            Glide.with(context).load(dataList.getAvatar()).into(avatar);

            layoutAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent next = new Intent(view.getContext(),SecondActivity.class);
                    next.putExtra("userName",dataList.getFirst_name()+" "+dataList.getLast_name());
                    next.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    view.getContext().startActivity(next);

                }
            });
        }
    }
}
