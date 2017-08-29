package viewer;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import basicSet.spot;
import dataStruct.DataEdit;
import dataStruct.inquire;

import java.awt.event.*;
import java.util.ArrayList;

public class SearchRouteWindow implements DocumentListener, ListSelectionListener, ActionListener {
	inquire inq;
	
	int currentLocation; //当前位置
	

	SearchRouteWindow(inquire _inq) throws Exception
	{
		inq=_inq;
		//Run();
		Font x = new Font("Serif",0,20);
		jf.setSize(1500, 1000);
		jf.setLocation(800, 300);
		jf.setLayout(new GridLayout(1, 2));//主界面窗口
		jf.setVisible(true);
		jp2.add(jl1);
		jp2.add(jtf1);
		jp1.add(jp2);
		jp1.add(jsp1);
		jf.add(jp1);
		jf.add(jpR);
		jp3.add(jl2);
		jp3.add(jtf2);
		jp1.add(jp3);
		jp1.add(jsp2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp4.add(jb1);
		jp4.add(jb2);
		jp1.add(jp4);
		//jpR.add(jta);
		jta.setFont(x);
		jta.setEditable(false);
		jtf1.getDocument().addDocumentListener(this);
		jtf2.getDocument().addDocumentListener(this);
		jlist1.addListSelectionListener(this);
		jlist2.addListSelectionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jpR.add(jsp3);
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public inquire getInq()
	{
		return inq;
	}

	public void setInq(inquire inq) 
	{
		this.inq = inq;
	}
	
	public int getCurrentLocation() 
	{
		return currentLocation;
	}

	public void setCurrentLocation(int currentLocation) 
	{
		this.currentLocation = currentLocation;
	}
	//界面布局
	JFrame jf=new JFrame("SearchRouteWindow");
	JTextField jtf1=new JTextField();
	JTextField jtf2=new JTextField();
	JLabel jl1=new JLabel("起点 :");
	JLabel jl2=new JLabel("终点 :");
	JLabel jl3=new JLabel();
	JLabel jl4=new JLabel();
	JPanel jp1=new JPanel(new GridLayout(7, 1));	//主界面左侧窗体
	JPanel jp2=new JPanel(new GridLayout(1, 2));//起点文本框
	JPanel jpR=new JPanel(new GridLayout(1, 1)); //主界面右侧窗体
	JList jlist1=new JList();//起点选择表
	JList jlist2=new JList();//终点选择表
	JPanel jp3=new JPanel(new GridLayout(1, 2));//终点文本框
	JPanel jp4=new JPanel(new GridLayout(1, 2));//查询按钮窗体
	JButton jb1=new JButton("查询最短路径");
	JButton jb2=new JButton("查询所有路径");
	JTextArea jta=new JTextArea();
	JScrollPane jsp1=new JScrollPane(jlist1);
	JScrollPane jsp2=new JScrollPane(jlist2);
	JScrollPane jsp3=new JScrollPane(jta);


	//这个函数用于调试
	public void Run() throws Exception
	{
        JOptionPane.showMessageDialog(null, inq.getShortestRoute(0, 2), "查询结果", JOptionPane.INFORMATION_MESSAGE);
        inq.getAllRoute(0, 2);
        inq.getAllRoute(0, 1);
        inq.getAllRoute(0, 4);
        inq.getAllRoute(1, 2);
        inq.getAllRoute(1, 3);
        DataEdit.SaveGraphToFile(inq, "dataofgraph.txt", "dataofspot.txt");
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) 
	{
		// TODO 自动生成的方法存根
		ArrayList <spot> res1=new ArrayList<spot>();
		try {
			res1=DataEdit.ResultMatch(jtf1.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name1 =new Object[res1.size()];
		for(int i=0;i<res1.size();i++)
		{
			name1[i]=res1.get(i).getName();
		}
		jlist1.setListData(name1);
		
		ArrayList <spot> res2=new ArrayList<spot>();
		try {
			res2=DataEdit.ResultMatch(jtf2.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name2 =new Object[res2.size()];
		for(int i=0;i<res2.size();i++)
		{
			name2[i]=res2.get(i).getName();
		}
		jlist1.setListData(name2);
	}

	@Override
	public void insertUpdate(DocumentEvent arg0) 
	{
		// TODO 自动生成的方法存根
		ArrayList <spot> res1=new ArrayList<spot>();
		try {
			res1=DataEdit.ResultMatch(jtf1.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name1 =new Object[res1.size()];
		for(int i=0;i<res1.size();i++)
		{
			name1[i]=res1.get(i).getName();
		}
		jlist1.setListData(name1);
		
		ArrayList <spot> res2=new ArrayList<spot>();
		try {
			res2=DataEdit.ResultMatch(jtf2.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name2 =new Object[res2.size()];
		for(int i=0;i<res2.size();i++)
		{
			name2[i]=res2.get(i).getName();
		}
		jlist2.setListData(name2);
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) 
	{
		// TODO 自动生成的方法存根
		ArrayList <spot> res1=new ArrayList<spot>();
		try {
			res1=DataEdit.ResultMatch(jtf1.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name1 =new Object[res1.size()];
		for(int i=0;i<res1.size();i++)
		{
			name1[i]=res1.get(i).getName();
		}
		jlist2.setListData(name1);
		
		ArrayList <spot> res2=new ArrayList<spot>();
		try {
			res2=DataEdit.ResultMatch(jtf2.getText(), inq);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		Object[] name2 =new Object[res2.size()];
		for(int i=0;i<res2.size();i++)
		{
			name2[i]=res2.get(i).getName();
		}
		jlist2.setListData(name2);
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		// TODO 自动生成的方法存根
		if(e.getSource()==jlist1)
		{
			jl3.setText("选定的起点："+(String) jlist1.getSelectedValue());
		}
		
		if(e.getSource()==jlist2)
		{
			jl4.setText("选定的终点："+(String) jlist2.getSelectedValue());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO 自动生成的方法存根
		if(e.getSource()==jb1)
		{
			if(jlist1.isSelectionEmpty()||jlist2.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "请选择景点", "错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			String name_start=(String) jlist1.getSelectedValue();
			String name_end=(String) jlist2.getSelectedValue();
			int v_start;
			int v_end;
			try {
				
				 v_start=DataEdit.UseNameGetVexNum(name_start, inq);
				 v_end=DataEdit.UseNameGetVexNum(name_end, inq);
				 String res=inq.getShortestRoute(v_start, v_end);
				 JOptionPane.showMessageDialog(null, res, "查询结果", JOptionPane.INFORMATION_MESSAGE);
				 jta.append(res);
				 jta.append("\n");
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==jb2)
		{
			if(jlist1.isSelectionEmpty()||jlist2.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "请选择景点", "错误",JOptionPane.ERROR_MESSAGE);
				return ;
			}
			String name_start=(String) jlist1.getSelectedValue();
			String name_end=(String) jlist2.getSelectedValue();
			int v_start=0;
			int v_end=0;
			try {
				 v_start=DataEdit.UseNameGetVexNum(name_start, inq);
				 v_end=DataEdit.UseNameGetVexNum(name_end, inq);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			try {
				String str=inq.getAllRoute(v_start, v_end);
				jta.append(str);
				jta.append("\n");
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		
	}
}
