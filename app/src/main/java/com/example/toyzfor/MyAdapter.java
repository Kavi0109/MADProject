package com.example.toyzfor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
	
	private List<Upload> uploads;
	private int rowLayout;
	private Context mContext;
	private ItemClickListener clickListener;
	
	public MyAdapter(List<Upload> uploads, int rowLayout, Context context) {
		this.uploads = uploads;
		this.rowLayout = rowLayout;
		this.mContext = context;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
		return new ViewHolder(v);
	}
	
	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		final Upload upload = uploads.get(position);
		viewHolder.name.setText(upload.getName());
		Picasso.get()
				.load(upload.getImageUrl())
				.placeholder(R.mipmap.ic_launcher)
				.fit()
				.centerCrop()
				.into(viewHolder.image);
	}
	
	@Override
	public int getItemCount() {
		return uploads == null ? 0 : uploads.size();
	}
	
	public void setClickListener(ItemClickListener itemClickListener) {
		this.clickListener = itemClickListener;
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public TextView name;
		public ImageView image;
		
		public ViewHolder(View itemView) {
			super(itemView);
			name = (TextView) itemView.findViewById(R.id.name);
			image = (ImageView) itemView.findViewById(R.id.img);
			itemView.setTag(itemView);
			itemView.setOnClickListener(this);
		}
		
		@Override
		public void onClick(View view) {
			if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
		}
	}
}
