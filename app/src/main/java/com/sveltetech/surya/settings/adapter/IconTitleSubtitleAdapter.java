package com.sveltetech.surya.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sveltetech.surya.R;
import com.sveltetech.surya.databinding.AdapterTitleSubtitleListItemBinding;
import com.sveltetech.surya.settings.model.SettingModel;

import java.util.List;

public class IconTitleSubtitleAdapter extends RecyclerView.Adapter<IconTitleSubtitleAdapter.ViewHolder> {

    private Context context;
    private List<SettingModel> settingModelList;
    private ItemActionListener mItemActionListener;

    public IconTitleSubtitleAdapter(Context context,List<SettingModel> settingModelList) {
        this.context=context;
        this.settingModelList = settingModelList;
    }

    public interface ItemActionListener {
        void onClickView(int title);
    }

    public void setItemActionListener(ItemActionListener mItemActionListener) {
        this.mItemActionListener = mItemActionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterTitleSubtitleListItemBinding binding = AdapterTitleSubtitleListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemActionListener(mItemActionListener);
        holder.bindTo(settingModelList.get(position).getTitle(), settingModelList.get(position).getSubtitle(),settingModelList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return settingModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterTitleSubtitleListItemBinding binding;
        private ItemActionListener itemActionListener;

        public ViewHolder(@NonNull AdapterTitleSubtitleListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(int titlename, int subtitlename, Integer icon) {
            binding.title.setText(titlename);
            if(subtitlename!=0){
                binding.subtitle.setText(subtitlename);
            }
            binding.icon.setImageResource(icon);

            binding.getRoot().setOnClickListener(v -> {
                itemActionListener.onClickView(titlename);
            });
        }

        public void setItemActionListener(ItemActionListener listener) {
            this.itemActionListener = listener;
        }
    }

}
