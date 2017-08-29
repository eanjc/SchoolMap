package dataStruct;

import basicSet.*;
import java.util.*;
import dataStruct.*;

import javax.swing.JOptionPane;

/*�����ṩ��Խ������ľ������ʵ��*/
public class inquire {
	Path path;
	
	public inquire(Path p)
	{
		path=p;
	}
	public inquire()
	{
		path=null;
	}
	
	
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public String getSpotInfo(int n) throws Exception
	{
		return path.getG().getVex(n).getInfo();
	}
	
	public String getSpotName(int n) throws Exception
	{
		return path.getG().getVex(n).getName();
	}
	
	public String getShortestRoute(int v,int w,Path p) throws Exception //������·��
	{
		int RouteLength=p.getD()[v][w];//������·������
		if(RouteLength==MGraph.INFINITY)
		{
			return "��·��";
		}
		int MidSpot[]=new int[p.getG().getVexNum()];
		int i=0;
		for(int k=0;k<p.getG().getVexNum();k++)//ȡ�ø�·���м�Ķ���
		{
			if(p.getP()[v][w][k]&&v!=k)
				MidSpot[i++]=k;
		}
		for(int z=0;z<i;z++) //��˳�������м�Ķ���
			for(int x=0;x<i-1;x++)
			{
				if(p.getD()[v][MidSpot[x]]>p.getD()[v][MidSpot[x+1]])
				{
					int tmp=MidSpot[x+1];
					MidSpot[x+1]=MidSpot[x];
					MidSpot[x]=tmp;
				}
			}
		String name_v=getSpotName(v);
		String name_w=getSpotName(w);
		String output_1="��"+name_v+"��"+name_w+"�����·������Ϊ"+RouteLength+". ";
		String output_2=";�� "+name_v+" ";
		for(int k=0;k<i;k++)
		{
			output_2=output_2+"-->"+getSpotName(MidSpot[k]);
		}
		output_2=output_2+".";
		String output=output_1+output_2;
		
		return output;

	}
	
	public String getShortestRoute(int v,int w) throws Exception //������·��
	{
		int RouteLength=path.getD()[v][w];//������·������
		if(RouteLength==MGraph.INFINITY)
		{
			return "��·��";
		}
		int MidSpot[]=new int[path.getG().getVexNum()];
		int i=0;
		for(int k=0;k<path.getG().getVexNum();k++)//ȡ�ø�·���м�Ķ���
		{
			if(path.getP()[v][w][k]&&v!=k)
				MidSpot[i++]=k;
		}
		for(int z=0;z<i;z++) //��˳�������м�Ķ���
			for(int x=0;x<i-1;x++)
			{
				if(path.getD()[v][MidSpot[x]]>path.getD()[v][MidSpot[x+1]])
				{
					int tmp=MidSpot[x+1];
					MidSpot[x+1]=MidSpot[x];
					MidSpot[x]=tmp;
				}
			}
		String name_v=getSpotName(v);
		String name_w=getSpotName(w);
		String output_1="��"+name_v+"��"+name_w+"�����·������Ϊ"+RouteLength+". ";
		String output_2=";�� "+name_v+" ";
		for(int k=0;k<i;k++)
		{
			output_2=output_2+" --> "+getSpotName(MidSpot[k]);
		}
		output_2=output_2+".";
		String output=output_1+output_2;
		return output;
	}
	
	public String getAllRoute(int v,int u) throws Exception //��ȡ��������м�·��
	{
		GetAllSimplePath.setRouteTOZero();
		String title="��"+path.getG().getVex(v).getName()+"��"+path.getG().getVex(u).getName()+"��ȫ����·��Ϊ ��";
		//System.out.println(title);
		GetAllSimplePath.getPaths(path.getG().getVex(v), null, path.getG().getVex(v), path.getG().getVex(u));
		if(GetAllSimplePath.RouteNum==0)
		{
			//System.out.println("������");
			JOptionPane.showMessageDialog(null, "����������䲻���ڼ�·��", "���", JOptionPane.INFORMATION_MESSAGE);
			GetAllSimplePath.Result+="������";
		}
		else
		{
			JOptionPane.showMessageDialog(null,title+"\n"+GetAllSimplePath.Result, "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return title+GetAllSimplePath.Result;
	}

}
