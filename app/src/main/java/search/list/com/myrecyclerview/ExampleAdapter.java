package search.list.com.myrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOLPHIN on 12/13/2018.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {

    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;

    ExampleAdapter(List<ExampleItem> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList); // use independent copy list by new arrayList
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.example_item, viewGroup, false); //find layout which item contains show in recycler view
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int position) {
        ExampleItem currentItem = exampleList.get(position);

        exampleViewHolder.imageView.setImageResource(currentItem.getImageResource());
        exampleViewHolder.textView1.setText(currentItem.getText1());
        exampleViewHolder.textView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(exampleListFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getText1().toLowerCase().contains(filterPattern)) { // change what u need to search
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            exampleList.clear();
            exampleList.addAll((List) filterResults.values);
            notifyDataSetChanged(); //refresh data
        }
    };

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;

        ExampleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view1);
            textView2 = itemView.findViewById(R.id.text_view2);
        }
    }
}
