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
	
	public void noticebell() {//֪ͨ����
		new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
			File musicfl=new File(bellPath);
			URI uri=musicfl.toURI();
			URL url=uri.toURL();
			AudioClip clip=Applet.newAudioClip(url);
			clip.play();
			Thread.sleep(1000);//���̳߳�˯һ�£�ʹ�����ܹ�����
		} catch (MalformedURLException | InterruptedException e) {
		}
			
		}
		}).start();
	}
	
	public void loginedbell() {//��¼����
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
	
	public void newonebell() {//��������
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
