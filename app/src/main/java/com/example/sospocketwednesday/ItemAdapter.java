package com.example.sospocketwednesday;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemAdapter extends ListAdapter<Item,ItemAdapter.ItemHolder> {
//    private List<Item> items = new ArrayList<>();
    private OnItemClickListener listener;

    protected ItemAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item item, @NonNull Item t1) {
            return item.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item item, @NonNull Item t1) {
            return item.getName().equals(t1.getName()) &&
                    item.getPrice().equals(t1.getPrice()) &&
                    item.getType().equals(t1.getType());
        }
    };

    @NonNull
    @Override
    public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder itemHolder, int i) {
        Item currentItem = getItem(i);
        itemHolder.textViewTitle.setText(currentItem.getName());
        itemHolder.textViewPrice.setText(currentItem.getPrice() + " \u20BD");

    }

//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public void setItems(List<Item> items){
//        this.items = items;
//        notifyDataSetChanged();
//    }

    public Item getItemAt(int position){
        return getItem(position);
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPrice;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title);
            textViewPrice = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }

                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
