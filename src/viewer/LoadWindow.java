package viewer;

import java.awt.*;
import javax.swing.*;

import basicSet.Path;
import dataStruct.DataEdit;
import dataStruct.MGraph;
import dataStruct.ShortestPath_FLOYD;
import dataStruct.inquire;

import java.awt.event.*;

public class LoadWindow implements ActionListener {
	JFrame jf=new JFrame("开启地图");
	JButton jb1=new JButton("从文件载入地图");
	JButton jb2 =new JButton("重新绘制地图");
	
	inquire inq=new inquire();
	
	LoadWindow()
	{
		jf.setVisible(true);
		jf.setLocation(800, 300);
		jf.setLayout(new FlowLayout());
		jb1.setSize(150, 100);
		jb2.setSize(150,100);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jf.add(jb1);
		jf.add(jb2);
		jf.setSize(500, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public inquire getInq()
	{
		return inq;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			inq=DataEdit.LoadMapFromFile("dataofgraph.txt", "dataofspot.txt");
			jf.dispose();
			try {
				MainWindow MW=new MainWindow(inq);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==jb2)
		{
			MGraph G=new MGraph();
			G.createUDN();
			ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
			floyd.FLOYD(G);
			Path newpath=new Path(G,floyd.getD(),floyd.getP());
			inquire inq=new inquire(newpath);
			jf.dispose();
			try {
				MainWindow MW=new MainWindow(inq);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
