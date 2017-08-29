package viewer;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import basicSet.spot;
import dataStruct.DataEdit;
import dataStruct.MGraph;
import dataStruct.inquire;

import java.awt.event.*;
import java.util.ArrayList;

public class RouteEditWindow implements ListSelectionListener, ActionListener {
	inquire inq;
	public static int selectStatus=0;
	/*
	 * 0��ʾ������һ���б�δѡ��
	 * 1��ʾ������ͬһ����
	 * 2��ʾ���ߵ�ǰ����·��
	 * 3��ʾ���ߵ�ǰ������ֱ��·��
	 */
	public static int sp1num;//�б�һѡ��ľ�����
	public static int sp2num;//�б��ѡ��ľ�����
	
	JFrame jf=new JFrame("Route Edit Window");
	JPanel jpleft=new JPanel(new GridLayout(5, 1));
	JPanel jpright =new JPanel(new GridLayout(4, 1));
	JLabel jlb1=new JLabel("����1��");
	JLabel jlb2=new JLabel("����2��");
	JList jlist1=new JList();
	JList jlist2=new JList();
	JScrollPane jsp1=new JScrollPane(jlist1);
	JScrollPane jsp2=new JScrollPane(jlist2);
	JTextArea jta1=new JTextArea();
	JButton jb1=new JButton("����·��");
	JButton jb2=new JButton("ɾ��·��");
	JButton jb3=new JButton("�޸�·������");
	JButton jb4=new JButton("����޸Ĳ�����");
	JPanel jp1=new JPanel();
	JPanel jp2=new JPanel();
	JPanel jp3=new JPanel();
	JPanel jp4=new JPanel();
	
	RouteEditWindow(inquire _inq)
	{
		inq=_inq;
		Font x = new Font("Serif",0,25);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(1000, 1000);
		jf.setLocation(800, 300);
		jf.setLayout(new GridLayout(1, 2));
		jf.add(jpleft);
		jf.add(jpright);
		jpleft.add(jlb1);
		jpleft.add(jsp1);
		jpleft.add(jlb2);
		jpleft.add(jsp2);
		jpleft.add(jta1);
		jp1.add(jb1);
		jpright.add(jp1);
		jp2.add(jb2);
		jpright.add(jp2);
		jp3.add(jb3);
		jpright.add(jp3);
		jp4.add(jb4);
		jpright.add(jp4);
		jta1.setFont(x);
		jta1.setEditable(false);
		jlist1.addListSelectionListener(this);
		jlist2.addListSelectionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlist2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		showSpotList();
	}
	
	public void showSpotList()
	{
		Object spotNumAndName[]=new Object[inq.getPath().getG().getVexNum()];
		for(int i=0;i<inq.getPath().getG().getVexNum();i++)
		{
			spotNumAndName[i]=i+" "+inq.getPath().getG().getVexs()[i].getName();
		}
		jlist1.setListData(spotNumAndName);
		jlist2.setListData(spotNumAndName);
	}

	public void setJTAText() throws Exception
	{
		if(jlist1.isSelectionEmpty()||jlist2.isSelectionEmpty())
		{
			
			return ;
		}
		int weight=inq.getPath().getG().getEdgeWeight(sp1num, sp2num);
		String sp=inq.getPath().getG().getVex(sp1num).getName()+"��"+inq.getPath().getG().getVex(sp2num).getName();
		if(sp1num==sp2num)
		{
			sp+="��ͬһ����";
			
		}
		else
		{
			if(weight==MGraph.INFINITY)
			{
				sp+="֮����ֱ�����ӵ�·��";
				
			}
			else
			{
				sp+="֮���·������Ϊ ��\n"+Integer.toString(weight);
				
			}
		}
		jta1.setText(sp);
		
	}
	
	
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO �Զ����ɵķ������
		if(jlist1.isSelectionEmpty()||jlist2.isSelectionEmpty())
		{
			selectStatus=0;
			return ;
		}

		    sp1num=Integer.parseInt(((String) jlist1.getSelectedValue()).split(" ")[0]);
		    sp2num=Integer.parseInt(((String) jlist2.getSelectedValue()).split(" ")[0]);
		try {
			int weight=inq.getPath().getG().getEdgeWeight(sp1num, sp2num);
			String sp=inq.getPath().getG().getVex(sp1num).getName()+"��"+inq.getPath().getG().getVex(sp2num).getName();
			if(sp1num==sp2num)
			{
				sp+="��ͬһ����";
				selectStatus=1;
			}
			else
			{
				if(weight==MGraph.INFINITY)
				{
					sp+="֮����ֱ�����ӵ�·��";
					selectStatus=3;
				}
				else
				{
					sp+="֮���·������Ϊ ��\n"+Integer.toString(weight);
					selectStatus=2;
				}
			}
			jta1.setText(sp);
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO �Զ����ɵķ������
		if(e.getSource()==jb1)//����·��
		{
			if(selectStatus==0)
			{
				JOptionPane.showMessageDialog(null, "������ѡ����������");
				return ;
			}
			if(selectStatus==1)
			{
				JOptionPane.showMessageDialog(null, "������ѡ��������ͬ�ľ���");
				return ;
			}
			if(selectStatus==2)
			{
				JOptionPane.showMessageDialog(null, "���������������·����������");
				return ;
			}
			if(selectStatus==3)
			{
				try {
					DataEdit.AddRoute(inq, sp1num, sp2num);
					setJTAText();
					selectStatus=2;
					return ;
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getSource()==jb2)
		{
			if(selectStatus==0)
			{
				JOptionPane.showMessageDialog(null, "������ѡ����������");
				return ;
			}
			if(selectStatus==1)
			{
				JOptionPane.showMessageDialog(null, "������ѡ��������ͬ�ľ���");
				return ;
			}
			if(selectStatus==2)
			{
				int o=JOptionPane.showConfirmDialog(null, "���Ҫɾ������·����", "ȷ��", JOptionPane.YES_NO_OPTION);
				if(o==JOptionPane.YES_OPTION)
				{
					try {
						DataEdit.RemoveRoute(inq, sp1num, sp2num);
						setJTAText();
						selectStatus=3;
						return ;
					} catch (Exception e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					
				}
				else
				{
					return ;
				}
			}
			if(selectStatus==3)
			{
				JOptionPane.showMessageDialog(null, "��ǰ��������䲻����·�����޷�ɾ��");
				return ;
			
			}
		}
		
		if(e.getSource()==jb3)
		{
			if(selectStatus==0)
			{
				JOptionPane.showMessageDialog(null, "������ѡ����������");
				return ;
			}
			if(selectStatus==1)
			{
				JOptionPane.showMessageDialog(null, "������ѡ��������ͬ�ľ���");
				return ;
			}
			if(selectStatus==2)
			{
				try {
					DataEdit.UpdataRoute(inq, sp1num, sp2num);
					setJTAText();
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
			}
			if(selectStatus==3)
			{
				JOptionPane.showMessageDialog(null, "��ǰ��������䲻����·�����޷��޸�");
				return ;
			}
		}
		
		if(e.getSource()==jb4)
		{
			jf.dispose();
		}
	}

}
