package com.sv.blackmagic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlackMatrixAdapter extends RecyclerView.Adapter<BlackMatrixAdapter.BlackViewHolder> {

	private Context mContext;
	private char[] mData;

	public BlackMatrixAdapter(Context context, char[] data) {
		mContext = context;
		mData = data;
	}

	@Override
	public BlackViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(mContext).inflate(R.layout.matrix_item, viewGroup, false);

		return new BlackViewHolder(v);
	}

	@Override
	public void onBindViewHolder(BlackViewHolder blackViewHolder, int i) {
		blackViewHolder.set(i);
	}

	@Override
	public int getItemCount() {
		return mData.length;
	}

	public class BlackViewHolder extends RecyclerView.ViewHolder {

		private TextView mBlackValue;

		public BlackViewHolder(View itemView) {
			super(itemView);
			mBlackValue = (TextView) itemView.findViewById(R.id.black_value);
		}

		public void set(int position) {
			mBlackValue.setText(position + "\n" + mData[position]);
		}
	}
}
