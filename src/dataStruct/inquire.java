package dataStruct;

import basicSet.*;
import java.util.*;
import dataStruct.*;

import javax.swing.JOptionPane;

/*该类提供针对界面对象的具体操作实现*/
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
	
	public String getShortestRoute(int v,int w,Path p) throws Exception //输出最短路径
	{
		int RouteLength=p.getD()[v][w];//获得最短路径长度
		if(RouteLength==MGraph.INFINITY)
		{
			return "无路径";
		}
		int MidSpot[]=new int[p.getG().getVexNum()];
		int i=0;
		for(int k=0;k<p.getG().getVexNum();k++)//取得该路径中间的顶点
		{
			if(p.getP()[v][w][k]&&v!=k)
				MidSpot[i++]=k;
		}
		for(int z=0;z<i;z++) //按顺序排序中间的顶点
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
		String output_1="从"+name_v+"到"+name_w+"的最短路径长度为"+RouteLength+". ";
		String output_2="途径 "+name_v+" ";
		for(int k=0;k<i;k++)
		{
			output_2=output_2+"-->"+getSpotName(MidSpot[k]);
		}
		output_2=output_2+".";
		String output=output_1+output_2;
		
		return output;

	}
	
	public String getShortestRoute(int v,int w) throws Exception //输出最短路径
	{
		int RouteLength=path.getD()[v][w];//获得最短路径长度
		if(RouteLength==MGraph.INFINITY)
		{
			return "无路径";
		}
		int MidSpot[]=new int[path.getG().getVexNum()];
		int i=0;
		for(int k=0;k<path.getG().getVexNum();k++)//取得该路径中间的顶点
		{
			if(path.getP()[v][w][k]&&v!=k)
				MidSpot[i++]=k;
		}
		for(int z=0;z<i;z++) //按顺序排序中间的顶点
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
		String output_1="从"+name_v+"到"+name_w+"的最短路径长度为"+RouteLength+". ";
		String output_2="途经 "+name_v+" ";
		for(int k=0;k<i;k++)
		{
			output_2=output_2+" --> "+getSpotName(MidSpot[k]);
		}
		output_2=output_2+".";
		String output=output_1+output_2;
		return output;
	}
	
	public String getAllRoute(int v,int u) throws Exception //获取两点间所有简单路径
	{
		GetAllSimplePath.setRouteTOZero();
		String title="从"+path.getG().getVex(v).getName()+"到"+path.getG().getVex(u).getName()+"的全部简单路径为 ：";
		//System.out.println(title);
		GetAllSimplePath.getPaths(path.getG().getVex(v), null, path.getG().getVex(v), path.getG().getVex(u));
		if(GetAllSimplePath.RouteNum==0)
		{
			//System.out.println("不存在");
			JOptionPane.showMessageDialog(null, "这两个景点间不存在简单路径", "结果", JOptionPane.INFORMATION_MESSAGE);
			GetAllSimplePath.Result+="不存在";
		}
		else
		{
			JOptionPane.showMessageDialog(null,title+"\n"+GetAllSimplePath.Result, "查询结果", JOptionPane.INFORMATION_MESSAGE);
		}
		
		return title+GetAllSimplePath.Result;
	}

}
