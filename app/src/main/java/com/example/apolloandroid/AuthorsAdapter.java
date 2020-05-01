package com.example.apolloandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AuthorsAdapter extends RecyclerView.Adapter<AuthorsAdapter.AuthorsViewHolder> {

    Context authorsContext;
    ArrayList<MyAuthor> myAuthorsList;

    public AuthorsAdapter(Context mContext, ArrayList<MyAuthor> myList) {
        authorsContext = mContext;
        myAuthorsList = myList;
    }

    @NonNull
    @Override
    public AuthorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View authorsView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .custom_authors_view, parent, false);
       RecyclerView.LayoutParams authorsLayoutParams = new RecyclerView.LayoutParams(ViewGroup.
               LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       authorsView.setLayoutParams(authorsLayoutParams);

       return new AuthorsViewHolder(authorsView);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsViewHolder holder, int position) {
        holder.firstNameTv.setText(myAuthorsList.get(position).getFirstName());
        holder.lastNameTv.setText(myAuthorsList.get(position).getLastName());

    }


    @Override
    public int getItemCount() {
        return myAuthorsList.size();

    }


    public class AuthorsViewHolder extends RecyclerView.ViewHolder {
        TextView firstNameTv;
        TextView lastNameTv;
        ConstraintLayout myAuthorsView;

        public AuthorsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.firstNameTv = itemView.findViewById(R.id.first_name);
            this.lastNameTv = itemView.findViewById(R.id.last_name);
            this.myAuthorsView = itemView.findViewById(R.id.auth_view);


        }
    }
}