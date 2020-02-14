package handel;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Bellmusic {
	String bellPath="getm.wav";

	public void setBellPath(String bellPath) {
		this.bellPath = bellPath;
	}
	
	public void noticebell() {//通知音乐
		new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
			File musicfl=new File(bellPath);
			URI uri=musicfl.toURI();
			URL url=uri.toURL();
			AudioClip clip=Applet.newAudioClip(url);
			clip.play();
			Thread.sleep(1000);//让线程沉睡一下，使音乐能够播放
		} catch (MalformedURLException | InterruptedException e) {
		}
			
		}
		}).start();
	}
	
	public void loginedbell() {//登录音乐
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
				File musicfl=new File("open.wav");
				URI uri=musicfl.toURI();
				URL url=uri.toURL();
				AudioClip clip=Applet.newAudioClip(url);
				clip.play();
				Thread.sleep(1000);
			} catch (MalformedURLException | InterruptedException e) {
			}
			}
		}).start();
		

	}
	
	public void newonebell() {//上线音乐
new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
				File musicfl=new File("newone.wav");
				URI uri=musicfl.toURI();
				URL url=uri.toURL();
				AudioClip clip=Applet.newAudioClip(url);
				clip.play();
				Thread.sleep(1000);
			} catch (MalformedURLException | InterruptedException e) {
			}
			}
		}).start();
	}

}
