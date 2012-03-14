package com.aselalee.imageview;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ImageViewActivity extends Activity {

	private int [][] colors = { {255,0,0,255},
								{0,255,0,255},
								{0,0,255,255} };
	
	private ImageView iv = null;
	private Bitmap bitmap= null;
	EditText et1r, et1g, et1b, et1a = null;
	EditText et2r, et2g, et2b, et2a = null;
	EditText et3r, et3g, et3b, et3a = null;
	EditText editTextBeingAdjusted = null;
	
	private final int WIDTH = 100;
	private final int STRIP_HEIGHT = 50;
	private final int NO_OF_STRIPS = 3;
	private final int HEIGHT = STRIP_HEIGHT * NO_OF_STRIPS;
	
	private int [] bitmapColors = null;
	private int sliderValue = 0;
	private final int DIALOG_SLIDER = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		iv = (ImageView) findViewById(R.id.imageView1);
		bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
		bitmapColors = new int[WIDTH*HEIGHT];

		et1r = (EditText) findViewById(R.id.editText1r);
		et1g = (EditText) findViewById(R.id.editText1g);
		et1b = (EditText) findViewById(R.id.editText1b);
		et1a = (EditText) findViewById(R.id.editText1a);

		et2r = (EditText) findViewById(R.id.editText2r);
		et2g = (EditText) findViewById(R.id.editText2g);
		et2b = (EditText) findViewById(R.id.editText2b);
		et2a = (EditText) findViewById(R.id.editText2a);

		et3r = (EditText) findViewById(R.id.editText3r);
		et3g = (EditText) findViewById(R.id.editText3g);
		et3b = (EditText) findViewById(R.id.editText3b);
		et3a = (EditText) findViewById(R.id.editText3a);

		et1r.setText(String.valueOf(colors[0][0])); et1r.setKeyListener(null);
		et1g.setText(String.valueOf(colors[0][1])); et1g.setKeyListener(null);
		et1b.setText(String.valueOf(colors[0][2])); et1b.setKeyListener(null);
		et1a.setText(String.valueOf(colors[0][3])); et1a.setKeyListener(null);

		et2r.setText(String.valueOf(colors[1][0])); et2r.setKeyListener(null);
		et2g.setText(String.valueOf(colors[1][1])); et2g.setKeyListener(null);
		et2b.setText(String.valueOf(colors[1][2])); et2b.setKeyListener(null);
		et2a.setText(String.valueOf(colors[1][3])); et2a.setKeyListener(null);

		et3r.setText(String.valueOf(colors[2][0])); et3r.setKeyListener(null);
		et3g.setText(String.valueOf(colors[2][1])); et3g.setKeyListener(null);
		et3b.setText(String.valueOf(colors[2][2])); et3b.setKeyListener(null);
		et3a.setText(String.valueOf(colors[2][3])); et3a.setKeyListener(null);

		updateImageView();
	}

	private void updateImageView() {
		for(int strip = 0; strip < NO_OF_STRIPS; strip++) {
			for(int row = 0; row < STRIP_HEIGHT; row++) {
				for(int pixel = 0; pixel < WIDTH; pixel++) {
					int curRow = row + (STRIP_HEIGHT * strip);
					int curPixel = pixel + (WIDTH * curRow);
					bitmapColors[ curPixel ] = 
							(colors[strip][3] & 0xff) << 24 |
							(colors[strip][0] & 0xff) << 16 |
							(colors[strip][1] & 0xff) << 8 |
							(colors[strip][2] & 0xff);
					}
				}
			}

		bitmap.setPixels(bitmapColors, 0, WIDTH, 0, 0, WIDTH, HEIGHT);
		iv.setImageBitmap(bitmap);
	}

	public void updateVariables() {
		if(et1r.getText().toString().isEmpty() == true) et1r.setText("0");
		colors[0][0] = Integer.parseInt(et1r.getText().toString());
		if(et1g.getText().toString().isEmpty() == true) et1g.setText("0");
		colors[0][1] = Integer.parseInt(et1g.getText().toString());
		if(et1b.getText().toString().isEmpty() == true) et1b.setText("0");
		colors[0][2] = Integer.parseInt(et1b.getText().toString());
		if(et1a.getText().toString().isEmpty() == true) et1a.setText("0");
		colors[0][3] = Integer.parseInt(et1a.getText().toString());

		if(et2r.getText().toString().isEmpty() == true) et2r.setText("0");
		colors[1][0] = Integer.parseInt(et2r.getText().toString());
		if(et2g.getText().toString().isEmpty() == true) et2g.setText("0");
		colors[1][1] = Integer.parseInt(et2g.getText().toString());
		if(et2b.getText().toString().isEmpty() == true) et2b.setText("0");
		colors[1][2] = Integer.parseInt(et2b.getText().toString());
		if(et2a.getText().toString().isEmpty() == true) et2a.setText("0");
		colors[1][3] = Integer.parseInt(et2a.getText().toString());

		if(et3r.getText().toString().isEmpty() == true) et3r.setText("0");
		colors[2][0] = Integer.parseInt(et3r.getText().toString());
		if(et3g.getText().toString().isEmpty() == true) et3g.setText("0");
		colors[2][1] = Integer.parseInt(et3g.getText().toString());
		if(et3b.getText().toString().isEmpty() == true) et3b.setText("0");
		colors[2][2] = Integer.parseInt(et3b.getText().toString());
		if(et3a.getText().toString().isEmpty() == true) et3a.setText("0");
		colors[2][3] = Integer.parseInt(et3a.getText().toString());
		updateImageView();
	}
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
			case DIALOG_SLIDER:
				dialog = new Dialog(ImageViewActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
				dialog.setContentView(R.layout.slider);
				Button button = (Button) dialog.findViewById(R.id.ok);
				button.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						dismissDialog(DIALOG_SLIDER);
					}
				});
				final TextView tv = (TextView)dialog.findViewById(R.id.DialogTextView);
				final SeekBar sb = (SeekBar) dialog.findViewById(R.id.seekBar);
				sb.setMax(255);
				sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
					}
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
						sliderValue=progress;
						tv.setText(String.valueOf(progress));
						updateEditText(progress);
						updateVariables();
					}
				});
				break;
			default:
				return null;
		}
		return dialog;
	}
	private void updateEditText(int value) {
		editTextBeingAdjusted.setText(String.valueOf(value));
	}
	protected void onPrepareDialog(int id, Dialog dialog) {
		TextView tv = (TextView)dialog.findViewById(R.id.DialogTextView);
		tv.setText(String.valueOf(sliderValue));
		SeekBar sb = (SeekBar) dialog.findViewById(R.id.seekBar);
		sb.setProgress(sliderValue);
	}
	public void getSliderInput(View v) {
		editTextBeingAdjusted = (EditText)v;
		sliderValue = Integer.parseInt(editTextBeingAdjusted.getText().toString());
		showDialog(DIALOG_SLIDER);
	}
}