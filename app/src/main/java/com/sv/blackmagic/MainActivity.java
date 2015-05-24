package com.sv.blackmagic;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener {

	private static final String TAG = "com.sv.blackmagic";
	private static final String LOG_TAG = "MainActivity";
	private static final String UNICODE_PREFIX = "U+26";
	private static final int CAPACITY = 16;

	private BlackMatrixAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridLayoutManager layoutManager = new GridLayoutManager(this, 10);
		layoutManager.setReverseLayout(true);
		RecyclerView matrix = (RecyclerView) findViewById(R.id.matrix);
		matrix.addItemDecoration(
				new SpacesItemDecoration(
						getResources().getDimensionPixelSize(R.dimen.black_item_margin)
				)
		);
		matrix.setLayoutManager(layoutManager);
		matrix.setHasFixedSize(true);

		mAdapter = new BlackMatrixAdapter(this, _generateMatrix());
		matrix.setAdapter(mAdapter);
		findViewById(R.id.magic_circle).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		TextView text = (TextView) v;

		if (text.getText().equals("?")) {
			text.setText(mAdapter.getResultValue());
		} else {
			text.setText("?");
			mAdapter.setData(_generateMatrix());
		}
	}

	private char[] _generateMatrix() {
		Random random = new Random();
		char[] matrix = new char[100];
		String firstHex = Integer.toHexString(random.nextInt(CAPACITY));
		String secondHex = Integer.toHexString(random.nextInt(CAPACITY));
		String unicodeString = UNICODE_PREFIX + firstHex + secondHex;
		String resultUnicode = UNICODE_PREFIX + firstHex + secondHex;
		char result = (char) Integer.parseInt(resultUnicode.substring(2), CAPACITY);

		matrix[0] = (char) Integer.parseInt(unicodeString.substring(2), CAPACITY);
		for (int i = 1; i < matrix.length; i++) {
			firstHex = Integer.toHexString(random.nextInt(CAPACITY));
			secondHex = Integer.toHexString(random.nextInt(CAPACITY));
			unicodeString = UNICODE_PREFIX + firstHex + secondHex;
			Log.d(LOG_TAG, unicodeString);

			if (i % 9 == 0) {
				matrix[i] = result;
			} else {
				matrix[i] = (char) Integer.parseInt(unicodeString.substring(2), CAPACITY);
			}
		}

		return matrix;
	}

	private class SpacesItemDecoration extends RecyclerView.ItemDecoration {
		private int mSpace;

		public SpacesItemDecoration(int space) {
			this.mSpace = space;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			outRect.left = mSpace;
			outRect.right = mSpace;
			outRect.bottom = mSpace;

			if(parent.getChildLayoutPosition(view) == 0) {
				outRect.top = mSpace;
			}
		}
	}
}
