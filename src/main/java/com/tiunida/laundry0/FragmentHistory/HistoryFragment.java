package com.tiunida.laundry0.FragmentHistory;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.FragmentHistory.Adapter.OrderAdapter;
import com.tiunida.laundry0.FragmentHistory.Model.Model;
import com.tiunida.laundry0.ActivityOrderDetail.ui.OrderDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference ordersRef = firebaseFirestore.collection("Orders");

    private LinearLayoutManager mLayoutManager;
    private OrderAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private String user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_history, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();

        ButterKnife.bind(this, myFragment);

        Query query = ordersRef.whereEqualTo("a_user_id", user_id).orderBy("a_uniq_id",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Model> options = new FirestoreRecyclerOptions.Builder<Model>()
                .setQuery(query, Model.class)
                .build();

        adapter = new OrderAdapter(options);

        RecyclerView recyclerView = myFragment.findViewById(R.id.order_list_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Model model = documentSnapshot.toObject(Model.class);
                String id = documentSnapshot.getId();
                Toast.makeText(getActivity(), "posisi : " + position + " id : " + id, Toast.LENGTH_LONG).show();

                Intent orderDetailIntent = new Intent(getView().getContext(), OrderDetailActivity.class);
                orderDetailIntent.putExtra("id",id);
                startActivity(orderDetailIntent);

            }
        });

        return myFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}



