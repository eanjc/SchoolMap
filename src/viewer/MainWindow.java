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

public class MainWindow implements ListSelectionListener, ActionListener, WindowListener {
	inquire inq;
	
	JFrame jf=new JFrame("Main Window");
	JPanel jpleft=new JPanel(new GridLayout(2, 1));//��봰��
	JPanel jpright=new JPanel(new GridLayout(6, 1));//�Ұ봰��
	JPanel jp1=new JPanel();
	JList jlist1=new JList();
	JScrollPane jsp1=new JScrollPane(jlist1);
	JTextArea jtaspinfo=new JTextArea();
	JPanel jp2=new JPanel();
	JButton jb1=new JButton("��ѯ·��");
	JButton jb2=new JButton("��Ӿ���");
	JButton jb3=new JButton("ɾ������");
	JPanel jp3=new JPanel();
	JPanel jp4=new JPanel();
	JButton jb4=new JButton("·���༭");
	JPanel jp5=new JPanel();
	JButton jb5=new JButton("�޸ľ���");
	
	
	MainWindow(inquire _inq)
	{
		inq=_inq;
		Font x = new Font("Serif",0,35);
		jf.setSize(1000, 1000);
		//jf.setLocation(800, 300);
		jf.setLocation(main.widthlocation,main.heightlocation);
		jf.addWindowListener(this);
		jf.setVisible(true);
		jf.setLayout(new GridLayout(1, 2));
		jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		jpleft.add(jsp1);
		jpleft.add( jtaspinfo);
		jtaspinfo.setEditable(false);
		jtaspinfo.setFont(x);
		jf.add(jpleft);
		jf.add(jpright);
		showSpotList();
		jlist1.addListSelectionListener(this);
		jp2.add(jb1);
		jb1.addActionListener(this);
		jpright.add(jp2);
		jp3.add(jb2);
		jp3.add(jb3);
		jpright.add(jp3);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jp5.add(jb5);
		jpright.add(jp5);
		jb5.addActionListener(this);
		jp4.add(jb4);
		jpright.add(jp4);
		jb4.addActionListener(this);
		jlist1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public void showSpotList()
	{
		Object spotNumAndName[]=new Object[inq.getPath().getG().getVexNum()];
		for(int i=0;i<inq.getPath().getG().getVexNum();i++)
		{
			spotNumAndName[i]=i+" "+inq.getPath().getG().getVexs()[i].getName();
		}
		jlist1.setListData(spotNumAndName);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO �Զ����ɵķ������
		if(jlist1.isSelectionEmpty())
			return ;
		int spnum=Integer.parseInt(((String) jlist1.getSelectedValue()).split(" ")[0]);
		try {
			String spname=inq.getPath().getG().getVex(spnum).getName();
			String spinfo=inq.getPath().getG().getVex(spnum).getInfo();
			 jtaspinfo.setText("���� : "+spname+"\n"+"���� ��"+spinfo);
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO �Զ����ɵķ������
		if(e.getSource()==jb1)//��ѯ·��
		{
			try {
				new SearchRouteWindow(inq);
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==jb2)//��Ӿ���
		{
			DataEdit.AddSpot(inq);
			showSpotList();
		}
		
		if(e.getSource()==jb3)//ɾ������
		{
			if(jlist1.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "�������б���ѡ��Ҫɾ���ľ���", "����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			else
			{				
				int spnum=Integer.parseInt(((String) jlist1.getSelectedValue()).split(" ")[0]);
				String spname=((String) jlist1.getSelectedValue()).split(" ")[1];
				int o=JOptionPane.showConfirmDialog(null, "���Ҫɾ������ "+spname+" ��","��ȷ��",JOptionPane.YES_NO_OPTION);
				if(o==JOptionPane.YES_OPTION)
				{
					try {
						DataEdit.RemoveSpot(inq, spnum);
					} catch (Exception e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					showSpotList();
					
				}
				else
				{
					return ;
				}
			}
		}
		
		if(e.getSource()==jb4)//·���༭
		{
			new RouteEditWindow(inq);
		}
		
		if(e.getSource()==jb5)//����༭
		{
			if(jlist1.isSelectionEmpty())
			{
				JOptionPane.showMessageDialog(null, "�������б���ѡ��Ҫ�޸ĵľ���", "����", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int spnum=Integer.parseInt(((String) jlist1.getSelectedValue()).split(" ")[0]);
			try {
				DataEdit.EditSpot(inq, spnum);
				String spname=inq.getPath().getG().getVex(spnum).getName();
				String spinfo=inq.getPath().getG().getVex(spnum).getInfo();
				 jtaspinfo.setText("���� : "+spname+"\n"+"���� ��"+spinfo);
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		JOptionPane.showMessageDialog(null, "�ļ�����������","Successfully Exited",JOptionPane.INFORMATION_MESSAGE);
		System.out.println("�ļ�����������");
	}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		// TODO �Զ����ɵķ������
		int o=JOptionPane.showConfirmDialog(null, "ȷ�Ϲرգ�","�ر�",JOptionPane.YES_NO_OPTION);
		if(o==JOptionPane.NO_OPTION)
			return ;
		if(o==JOptionPane.YES_OPTION)
		{
			DataEdit.SaveGraphToFile(inq, "dataofgraph.txt", "dataofspot.txt");
			jf.dispose();
			//System.exit(0);
		}

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

}
