package com.tiunida.laundry0.historyFrag.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.historyFrag.Model.Model;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderAdapter extends FirestoreRecyclerAdapter<Model, OrderAdapter.OrderHolder> {

    private OnItemClickListener listener;

    public OrderAdapter(@NonNull FirestoreRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderHolder orderHolder, int i, @NonNull Model model) {
        orderHolder.mOrderDateTxt.setText(model.getA_time());
        orderHolder.mDoneTimeTxt.setText(model.getA_waktu_selesai());


        if (model.getA_price2() != null) {
            Log.d("tidak ", "null");
            int priceInt = Integer.valueOf(model.getA_price2());
            int diskonInt = Integer.valueOf(model.getA_diskon());
            int hasil = priceInt * diskonInt / 100;
            int totalPrice = priceInt - hasil;
            orderHolder.mPrice.setText(String.valueOf(totalPrice));
        }

        //orderHolder.mPrice.setText(model.getA_price2());


        String string1 = "1";
        if (model.getH_accepted2().equals(string1)) {
            orderHolder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mAcceptedLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
        } else {
            orderHolder.mAcceptIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mAcceptedLine.setBackgroundResource(R.drawable.ractangle_view_putih);
        }

        if (model.getH_on_proses2().equals(string1)) {
            orderHolder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mProsesLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
        } else {
            orderHolder.mProsesIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mProsesLine.setBackgroundResource(R.drawable.ractangle_view_putih);
        }

        if (model.getH_done2().equals(string1)) {
            orderHolder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mDoneLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
        } else {
            orderHolder.mDoneIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mDoneLine.setBackgroundResource(R.drawable.ractangle_view_putih);
        }

        if (model.getH_paid2().equals(string1)) {
            orderHolder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_background);
            orderHolder.mPaidLine.setBackgroundResource(R.drawable.ractangle_view_birulaut);
        } else {
            orderHolder.mPaindIndicator.setBackgroundResource(R.drawable.circle_view_border);
            orderHolder.mPaidLine.setBackgroundResource(R.drawable.ractangle_view_putih);
        }

        if (model.getH_delivered2().equals(string1)) {
            orderHolder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_background);
        } else {
            orderHolder.mDeliveredIndicator.setBackgroundResource(R.drawable.circle_view_border);
        }
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laundry_list, parent, false);
        return new OrderHolder(view);
    }

    class OrderHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.doneTime)
        TextView mDoneTimeTxt;
        @BindView(R.id.orderDate)
        TextView mOrderDateTxt;
        @BindView(R.id.price)
        TextView mPrice;
        @BindView(R.id.acceptIndicator)
        View mAcceptIndicator;
        @BindView(R.id.acceptLine)
        View mAcceptedLine;
        @BindView(R.id.prosesIndicator)
        View mProsesIndicator;
        @BindView(R.id.prosesLine)
        View mProsesLine;
        @BindView(R.id.doneIndicator)
        View mDoneIndicator;
        @BindView(R.id.doneLine)
        View mDoneLine;
        @BindView(R.id.paidIndicator)
        View mPaindIndicator;
        @BindView(R.id.paidLine)
        View mPaidLine;
        @BindView(R.id.deliveredIndicator)
        View mDeliveredIndicator;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posision = getAdapterPosition();
                    if (posision != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(posision), posision);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
