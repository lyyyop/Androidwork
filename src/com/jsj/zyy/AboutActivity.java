package com.jsj.zyy;

import com.jsj.zyy.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AboutActivity extends Activity {
		final static double MAX = 100;
	    final static double MAX_STAR = 5;
	    private ProgressBar pb;
	    private SeekBar sb;
	    private RatingBar rb;
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.about);
	        pb = (ProgressBar)this.findViewById(R.id.progressBar1);
	        sb = (SeekBar)this.findViewById(R.id.seekBar1);
	        Button btn1=(Button)this.findViewById(R.id.button1);
	        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					// TODO Auto-generated method stub
					pb.setProgress(sb.getProgress());
					rb.setRating((float)(sb.getProgress()/MAX*MAX_STAR));
				}

				public void onStartTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}

				public void onStopTrackingTouch(SeekBar seekBar) {
					// TODO Auto-generated method stub
					
				}
	        	
	        });
	        rb = (RatingBar)this.findViewById(R.id.ratingBar1);
	        rb.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

				public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
					// TODO Auto-generated method stub
					int progress = (int)(rb.getRating()*MAX/MAX_STAR);
					pb.setProgress(progress);
					sb.setProgress(progress);
				}
	        	
	        });
	        btn1.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});
	    }
	}
	

