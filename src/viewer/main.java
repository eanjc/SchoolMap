package viewer;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import basicSet.Path;
import dataIO.DataIO;
import dataStruct.DataEdit;
import dataStruct.MGraph;
import dataStruct.ShortestPath_FLOYD;
import dataStruct.inquire;

public class main {
	public static int widthlocation=(int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-800)/2;
	public static int heightlocation=(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-600)/2;
	public static void main(String[] args) throws HeadlessException, Exception 
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//��ǰϵͳ���

		LoadWindow LW=new LoadWindow();//�ӵ��봰�ڿ���
	   
	}


	
}
