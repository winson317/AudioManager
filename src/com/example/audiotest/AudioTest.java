package com.example.audiotest;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class AudioTest extends Activity {
	
	Button play, up, down;
	ToggleButton mute;
	AudioManager aManager;
	private static final int DOWN = 0;
	private static final int UP = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);  //获取系统的音频服务
        play = (Button)findViewById(R.id.play);
        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        mute = (ToggleButton)findViewById(R.id.mute);
        
        play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				MediaPlayer mPlay = MediaPlayer.create(AudioTest.this, R.raw.waitan); //初始化MediaPlayer对象,准备播放音乐
				mPlay.setLooping(true);  //设置循环播放
				mPlay.start(); //开始播放
			}
		});
        
        /*if (flag == 0)
        {
        	aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }
        else if (flag == 1)
        {
        	aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FX_FOCUS_NAVIGATION_UP);
        }*/
        
        
        
        up.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				//指定调节音量 的音频、增大音量、显示音量图形的示意
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
			}
		});
        
        down.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				//指定调节音量 的音频、降低音量、显示音量图形的示意
				aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
						AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
			}
		});
        
        mute.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton source, boolean isChecked) {
				// TODO Auto-generated method stub
				//指定调节音量的音频、根据isChecked确定是否需要静音
				aManager.setStreamMute(AudioManager.STREAM_MUSIC, isChecked);
			}
		});
    
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) 
		{
		case KeyEvent.KEYCODE_VOLUME_UP:
			aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, 0);			
			return true;
			//break;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			aManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
			return true;
		default:
			break;
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
