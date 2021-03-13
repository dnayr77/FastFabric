package edu.umich.fastfabricui1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.MyViewHolder> {

    private List<Result> resultList;
    private Context context;
    private OnResultListener mOnResultListener;

    public ResultListAdapter(Context context, List<Result> resultList, OnResultListener onResultListener) {
        this.resultList = resultList;
        this.mOnResultListener = onResultListener;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name, cost;
        public ImageView fabricImage;
        OnResultListener onResultListener;

        public MyViewHolder(View view, OnResultListener onResultListener) {
            super(view);
            fabricImage = (ImageView) view.findViewById(R.id.fabricImage);
            name = (TextView) view.findViewById(R.id.name);
            cost = (TextView) view.findViewById(R.id.cost);
            this.onResultListener = onResultListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            onResultListener.onResultClick(getAdapterPosition());
        }
    }

    @Override
    public ResultListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.list_results, parent, false);

        return new ResultListAdapter.MyViewHolder(itemView, mOnResultListener);
    }

    @Override
    public void onBindViewHolder(ResultListAdapter.MyViewHolder holder, int position) {
        Result result = resultList.get(position);
        new ImageLoad(result.getImage(), holder.fabricImage).execute();
        holder.name.setText(result.getName());
        String costString = "$" + result.getCost();
        Character check = costString.charAt(costString.length() - 3);
        while (check != '.'){
            costString = costString + "0";
            check = costString.charAt(costString.length() - 3);
        }
        holder.cost.setText(costString);
    }

    public interface OnResultListener{
        void onResultClick(int position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
