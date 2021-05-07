package com.example.vercukornezegeto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vercukornezegeto.R;
import com.example.vercukornezegeto.UpdateActivity;
import com.example.vercukornezegeto.entities.Observation;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ObsAdapter extends RecyclerView.Adapter<ObsAdapter.ViewHolder> {
    private ArrayList<Observation> mListingItemsData;
    private Context mContext;

    public ObsAdapter(Context context, ArrayList<Observation> itemsData){
        System.out.println("-------------------------ObsAdapter init-------------------------");
        this.mListingItemsData = itemsData;
        this.mContext = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("-------------------------Inflate start-------------------------" + "to Context " + mContext.getPackageName());
        return new ViewHolder(LayoutInflater.from(mContext).inflate(com.example.vercukornezegeto.R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ObsAdapter.ViewHolder holder, int position) {
        Observation currentItem = mListingItemsData.get(position);
        System.out.println("----------------------Current item-------------------: " + currentItem);

        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mListingItemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private CollectionReference mFireStore = FirebaseFirestore.getInstance().collection("Observations");

        private TextView dateText;

        private TextView mWBCText;
        private TextView mRBCText;
        private TextView mHGBText;
        private TextView mHCTText;
        private TextView mMCVText;
        private TextView mMCHText;
        private TextView mMCHCText;
        private TextView mPLTText;
        private TextView mRDWSDText;
        private TextView mRDWCVText;
        private TextView mPDWText;
        private TextView mMPVText;
        private TextView mPCTText;
        private TextView mNEUTText;
        private TextView mLYMPHText;
        private TextView mMONOText;
        private TextView mEOText;
        private TextView mBASOText;
        private TextView mIGText;

        private TextView mWBCb;
        private TextView mRBCb;
        private TextView mHGBb;
        private TextView mHCTb;
        private TextView mMCVb;
        private TextView mMCHb;
        private TextView mMCHCb;
        private TextView mPLTb;
        private TextView mRDWSDb;
        private TextView mRDWCVb;
        private TextView mPDWb;
        private TextView mMPVb;
        private TextView mPCTb;
        private TextView mNEUTb;
        private TextView mLYMPHb;
        private TextView mMONOb;
        private TextView mEOb;
        private TextView mBASOb;
        private TextView mIGb;

        private ArrayList<TextView> listOfTextValues = new ArrayList<>();
        private ArrayList<TextView> listOfTextBasedOn = new ArrayList<>();


        public ViewHolder(View itemView) {
            super(itemView);
            System.out.println("-------------------------ViewHolder inicialized-------------------------" + "to Context " + mContext.getPackageName());
            dateText = itemView.findViewById(R.id.dateText);
            listOfTextValues.add(mWBCText = itemView.findViewById(R.id.wbcValue));
            listOfTextValues.add(mRBCText = itemView.findViewById(R.id.rbcValue));
            listOfTextValues.add(mHGBText = itemView.findViewById(R.id.hgbValue));
            listOfTextValues.add(mHCTText = itemView.findViewById(R.id.hctValue));
            listOfTextValues.add(mMCVText = itemView.findViewById(R.id.mcvValue));
            listOfTextValues.add(mMCHText = itemView.findViewById(R.id.mchValue));
            listOfTextValues.add(mMCHCText = itemView.findViewById(R.id.mchcValue));
            listOfTextValues.add(mPLTText = itemView.findViewById(R.id.pltValue));
            listOfTextValues.add(mRDWSDText = itemView.findViewById(R.id.rdwsdValue));
            listOfTextValues.add(mRDWCVText = itemView.findViewById(R.id.rdwcvValue));
            listOfTextValues.add(mPDWText = itemView.findViewById(R.id.pdwValue));
            listOfTextValues.add(mMPVText = itemView.findViewById(R.id.mpvValue));
            listOfTextValues.add(mPCTText = itemView.findViewById(R.id.pctValue));
            listOfTextValues.add(mNEUTText = itemView.findViewById(R.id.neutValue));
            listOfTextValues.add(mLYMPHText = itemView.findViewById(R.id.lymphValue));
            listOfTextValues.add(mMONOText = itemView.findViewById(R.id.monoValue));
            listOfTextValues.add(mEOText = itemView.findViewById(R.id.eoValue));
            listOfTextValues.add(mBASOText = itemView.findViewById(R.id.basoValue));
            listOfTextValues.add(mIGText = itemView.findViewById(R.id.igValue));

            listOfTextBasedOn.add(mWBCb = itemView.findViewById(R.id.wbcBasedOn));
            listOfTextBasedOn.add(mRBCb = itemView.findViewById(R.id.rbcBasedOn));
            listOfTextBasedOn.add(mHGBb = itemView.findViewById(R.id.hgbBasedOn));
            listOfTextBasedOn.add(mHCTb = itemView.findViewById(R.id.hctBasedOn));
            listOfTextBasedOn.add(mMCVb = itemView.findViewById(R.id.mcvBasedOn));
            listOfTextBasedOn.add(mMCHb = itemView.findViewById(R.id.mchBasedOn));
            listOfTextBasedOn.add(mMCHCb = itemView.findViewById(R.id.mchcBasedOn));
            listOfTextBasedOn.add(mPLTb = itemView.findViewById(R.id.pltBasedOn));
            listOfTextBasedOn.add(mRDWSDb = itemView.findViewById(R.id.rdwsdBasedOn));
            listOfTextBasedOn.add(mRDWCVb = itemView.findViewById(R.id.rdwcvBasedOn));
            listOfTextBasedOn.add(mPDWb = itemView.findViewById(R.id.pdwBasedOn));
            listOfTextBasedOn.add(mMPVb = itemView.findViewById(R.id.mpvBasedOn));
            listOfTextBasedOn.add(mPCTb = itemView.findViewById(R.id.pctBasedOn));
            listOfTextBasedOn.add(mNEUTb = itemView.findViewById(R.id.neutBasedOn));
            listOfTextBasedOn.add(mLYMPHb = itemView.findViewById(R.id.lymphBasedOn));
            listOfTextBasedOn.add(mMONOb = itemView.findViewById(R.id.monoBasedOn));
            listOfTextBasedOn.add(mEOb = itemView.findViewById(R.id.eoBasedOn));
            listOfTextBasedOn.add(mBASOb = itemView.findViewById(R.id.basoBasedOn));
            listOfTextBasedOn.add(mIGb = itemView.findViewById(R.id.igBasedOn));



        }
        @SuppressLint("SetTextI18n")
        public void bindTo(Observation currentItem){
            for (int i = 0; i<listOfTextValues.size(); i++){
                dateText.setText(currentItem.getEffectiveInstant());
                listOfTextValues.get(i).setText(currentItem.getFocus().get(i)+ ": "+currentItem.getComponent().get(i).getValueString());
                listOfTextBasedOn.get(i).setText(currentItem.getBasedOn().get(i));

                String[] basedOn = listOfTextBasedOn.get(i).getText().toString().replaceAll("\\s+","").split("–");

                float low = Float.parseFloat(basedOn[0]);
                float high = Float.parseFloat(basedOn[1]);

                String[] value = listOfTextValues.get(i).getText().toString().split(" ");
                float currentValue = Float.parseFloat(value[1]);
                if (currentValue > low && currentValue < high){
                    System.out.println(listOfTextValues.get(i).getText() + " " + currentValue + ">" + low + "&&" +currentValue+"<"+high);
                    listOfTextValues.get(i).setTextColor(Color.rgb(32,178,170));
                }
                if (currentValue == low || currentValue == high){
                    System.out.println(listOfTextValues.get(i).getText() + " " +currentValue + "==" + low + "||" +currentValue+"=="+high);
                    listOfTextValues.get(i).setTextColor(Color.rgb(240,128,128));
                }
                if (currentValue > high || currentValue < low){
                    System.out.println(listOfTextValues.get(i).getText() + " " +currentValue + "<" + low + "||" +currentValue+">"+high);
                    listOfTextValues.get(i).setTextColor(Color.rgb(165,42,42));
                }
            }
            itemView.findViewById(R.id.updateItem).setOnClickListener(e->{
                Intent intent = new Intent(itemView.getContext(), UpdateActivity.class);
                intent.putExtra("currentItem", currentItem);

                itemView.getContext().startActivity(intent);
                }
            );
            itemView.findViewById(R.id.deleteItem).setOnClickListener(v -> {
                mFireStore.document(currentItem.getDocumentId()).delete().addOnSuccessListener(unused -> {
                    mListingItemsData.remove(currentItem);
                    notifyDataSetChanged();
                });
            });
        }
    }
}
