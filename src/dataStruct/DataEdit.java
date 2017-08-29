package dataStruct;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import basicSet.Path;
import basicSet.spot;
import dataIO.DataIO;

/*该类用于封装操作*/
public class DataEdit {
	
	
	public static inquire AddSpot(inquire inq)
	{
		int num;
		String nm;
		String info;
		num=Integer.parseInt(JOptionPane.showInputDialog("请输入景点编号(与存储编号无关)："));
		nm=JOptionPane.showInputDialog("请输入景点名称：").trim();
		info=JOptionPane.showInputDialog("请输入景点的介绍：").trim();
		
		inq.getPath().getG().InsertVex(num,nm, info);
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(inq.getPath().getG());
		Path newpath=new Path(inq.getPath().getG(),floyd.getD(),floyd.getP());
		inq.setPath(newpath);
		
		return inq;
	}
	
	public static inquire RemoveSpot(inquire inq,int i) throws Exception
	{
		inq.getPath().getG().DeleteVex(i);
		
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(inq.getPath().getG());
		Path newpath=new Path(inq.getPath().getG(),floyd.getD(),floyd.getP());
		inq.setPath(newpath);
		
		return inq;
		
	}
	
	public static inquire AddRoute(inquire inq,int v,int u) throws Exception
	{
		int weight=Integer.parseInt(JOptionPane.showInputDialog("输入路径长度"));
		inq.getPath().getG().InsertEdge(v, u, weight);
		
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(inq.getPath().getG());
		Path newpath=new Path(inq.getPath().getG(),floyd.getD(),floyd.getP());
		inq.setPath(newpath);
		
		return inq;
	}
	
	public static inquire RemoveRoute(inquire inq,int v,int u) throws Exception
	{
		inq.getPath().getG().DeleteEdge(v, u);
		
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(inq.getPath().getG());
		Path newpath=new Path(inq.getPath().getG(),floyd.getD(),floyd.getP());
		inq.setPath(newpath);
		
		return inq;
	}
	
	public static inquire UpdataRoute(inquire inq ,int v,int u) throws Exception
	{
		int newWeight=Integer.parseInt(JOptionPane.showInputDialog("输入路径长度"));
		inq.getPath().getG().UpdateEdge(v, u, newWeight);
		
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(inq.getPath().getG());
		Path newpath=new Path(inq.getPath().getG(),floyd.getD(),floyd.getP());
		inq.setPath(newpath);
		
		return inq;
	}
	
	public static MGraph LoadGraphFromFile(String g_path,String s_path)
	{
		MGraph G=new MGraph();
		DataIO dio=new DataIO(g_path,s_path);
		G=dio.loadAll(G);
		G.setAllSpotRelation();
		return G;
	}
	
	public static inquire LoadMapFromFile(String g_path,String s_path)
	{
		MGraph G=LoadGraphFromFile(g_path,s_path);
		ShortestPath_FLOYD floyd=new ShortestPath_FLOYD();
		floyd.FLOYD(G);
		Path newpath=new Path(G,floyd.getD(),floyd.getP());
		inquire inq=new inquire(newpath);
		return inq;
	}
	
	public static void SaveGraphToFile(inquire inq,String g_path,String s_path)
	{
		MGraph G=inq.getPath().getG();
		DataIO dio=new DataIO(g_path,s_path);
		dio.saveAll(G);
	}
	
	public static ArrayList<spot> ResultMatch(String location,inquire inq) throws Exception
	{
		ArrayList<spot> res=new ArrayList<spot>();
		int n=inq.getPath().getG().getVexNum();
		for(int i=0;i<n;i++)
		{
			if(inq.getPath().getG().getVex(i).getName().contains(location))
			{
				res.add(inq.getPath().getG().getVex(i));
			}
		}
		return res;
	}
	
	public static int UseNameGetVexNum(String name,inquire inq) throws Exception
	{
		spot vexs[]=inq.getPath().getG().getVexs();
		for(int i=0;i<vexs.length;i++)
		{
			if(name.equals(vexs[i].getName()))
			{
				return i;
			}
		}
		throw new Exception("名字为"+name+"的景点不存在！");
	}
	
	public static inquire EditSpot(inquire inq,int i) throws Exception
	{
		String newname=JOptionPane.showInputDialog("输入该景点新的名字 ：",inq.getPath().getG().getVex(i).getName()).trim();
		while(newname.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "名字不能为空!");
			newname=JOptionPane.showInputDialog("输入该景点新的名字 ：",inq.getPath().getG().getVex(i).getName()).trim();
		}
		String newinfo=JOptionPane.showInputDialog("输入该景点新的介绍 ：",inq.getPath().getG().getVex(i).getInfo()).trim();
		while(newinfo.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "介绍不能为空!");
			newinfo=JOptionPane.showInputDialog("输入该景点新的介绍 ：",inq.getPath().getG().getVex(i).getInfo()).trim();
		}
		
		inq.getPath().getG().UpdateVexName(i, newname);
		inq.getPath().getG().UpdateVexInfo(i, newinfo);
		
		return inq;
				
	}

}
