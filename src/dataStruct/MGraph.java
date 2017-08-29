package dataStruct;

import java.util.*;

import javax.swing.JOptionPane;

import basicSet.*;

/*图的定义和基本操作*/
public class MGraph 
{
	public final static int INFINITY=Integer.MAX_VALUE;
	private int vexNum;
	private int arcNum;
	private spot[] vexs;
	private int[][] arcs;

	public MGraph(int vN,int aN,spot[] _vexs,int[][] _arcs)
	{
		this.vexNum=vN;
		this.arcNum=aN;
		this.vexs=_vexs;
		this.arcs=_arcs;
	}

	public MGraph()
	{
		this(0,0,null,null);
	}

	public int getVexNum() 
	{
		return vexNum;
	}

	public void setVexNum(int vexNum)
	{
		this.vexNum = vexNum;
	}

	public int getArcNum() 
	{
		return arcNum;
	}

	public void setArcNum(int arcNum)
	{
		this.arcNum = arcNum;
	}

	public int[][] getArcs()
	{
		return arcs;
	}

	public void setArcs(int[][] arcs)
	{
		this.arcs = arcs;
	}
	
	public spot[] getVexs()
	{
		return vexs;
	}
	
	public int locateVex(int n)//定位顶点编号
	{
		for(int v=0;v<vexNum;v++)
			if(vexs[v].getNumber()==n)
				return v;
		return -1;
	}
	
	public int getEdgeWeight(int u,int v) throws Exception
	{
		if(v>=vexNum||u>=vexNum||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "边不存在,请检查顶点", "错误", JOptionPane.ERROR_MESSAGE);
			throw new Exception("发生错误，请检查顶点");
		}
		return arcs[u][v];
	}
	
	public void createUDN()//初始创建无向网
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("请分别输入图的顶点数、图的边数:");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new spot[vexNum];
		//System.out.println("请分别输入图的各个顶点的编号:");
		for(int v=0;v<vexNum;v++)
		{
			vexs[v]=new spot(v);
			
		}
		System.out.println("请分别输入图对应顶点编号的名称:");
		for(int v=0;v<vexNum;v++)
		{
			System.out.println(vexs[v].getNumber());
			vexs[v].setName(sc.next().trim());
		}
		System.out.println("请分别输入图对应顶点编号和名称的介绍：");
		for(int v=0;v<vexNum;v++)
		{
			System.out.println(vexs[v].getNumber()+"  "+vexs[v].getName());
			vexs[v].setInfo(sc.next().trim());
		}
		
		arcs=new int[vexNum][vexNum];
		for (int v = 0; v < vexNum; v++)
			// 初始化邻接矩阵
			for (int u = 0; u < vexNum; u++)
				arcs[v][u] = INFINITY;
		
		System.out.println("请输入各个顶点（景点）编号之间的边的权值（路径长度）[vex1 vex2 value]：");
		for(int k=0;k<arcNum;k++)
		{
			int v=locateVex(sc.nextInt());
			int u=locateVex(sc.nextInt());
			arcs[v][u]=arcs[u][v]=sc.nextInt();
		}
		
		setAllSpotRelation();
	}
	
	public spot getVex(int v) throws Exception  // 返回v表示结点的值， 0 <= v < vexNum
	{
		if (v < 0 || v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");
		return vexs[v];
	}
	
	public spot useNumGetVex(int n) throws Exception //用定义的点编号查找在顶点数组中的位置并返回点
	{
		boolean flag=false;

		for(int i=0;i<vexNum;i++)
		{
			if(vexs[i].getNumber()==n)
				return vexs[i];
		}
		throw new Exception("编号为"+n+"的顶点不存在");
	}
	
	public int useNumGetVexLoaction(int n) throws Exception//用定义的点编号查找在顶点数组中的位置并返回位置编号
	{
		boolean flag=false;

		for(int i=0;i<vexNum;i++)
		{
			if(vexs[i].getNumber()==n)
				return i;
		}
		throw new Exception("编号为"+n+"的顶点不存在");
	}
			
	public int firstAdjVex(int v) throws Exception // 返回v的第一个邻接点，若v没有邻接点则返回-1， 0 <= v < vexNum
{
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		for (int j = 0; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}
			
	public int nextAdjVex(int v, int w) throws Exception// 返回v相对于w的下一个邻接点，若w是v的最后一个邻接点，则返回-1，其中0≤v, w<vexNum
   {
		if (v < 0 && v >= vexNum)
			throw new Exception("第" + v + "个顶点不存在!");

		for (int j = w + 1; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}	
	
	public void InsertVex(int number ,String _name,String _info)//添加顶点,顶点编号在数组中存储为最后与number无关
	{
		int newVexNum=vexNum+1;
		//刷新vexs数组	
		spot newVexs[]=new spot[newVexNum];
		for(int i=0;i<newVexNum-1;i++)
		{
			newVexs[i]=vexs[i];
		}
	    newVexs[newVexNum-1]=new spot(number,_name,_info);
	    
	    //刷新arcs数组
	    
	    int newArcs[][]=new int[newVexNum][newVexNum];
	    for(int i=0;i<vexNum;i++)
	    {
	    	for(int j=0;j<vexNum;j++)
	    	{
	    		newArcs[i][j]=arcs[i][j];
	    	}
	    }
	    for(int i=0;i<newVexNum;i++)
	    {
	    	newArcs[newVexNum-1][i]=INFINITY;
	    	newArcs[i][newVexNum-1]=INFINITY;
	    }
	    
	    //将新数据写入MGraph
	    vexNum=newVexNum;
	    vexs=newVexs;
	    arcs=newArcs;	   	
	    setAllSpotRelation();
	}
	
	public void InsertEdge(int v,int u,int weight) throws Exception//插入边 v,u,为顶点，weight为权值
	{
		if(v>=vexNum||u>=vexNum||weight<=0||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "发生错误，请检查顶点及权值", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("发生错误，请检查顶点及权值");
		}
		if(isEdgeExist(v, u))
		{
			JOptionPane.showMessageDialog(null, "边已存在，不能添加", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("发生错误，边已存在，不能添加");
		}
		arcNum=arcNum+1;
		arcs[v][u]=arcs[u][v]=weight;
		setAllSpotRelation();
	}
	
	public void DeleteEdge(int v,int u) throws Exception//删除边，v,u为边的两个顶点
	{
		if(v>=vexNum||u>=vexNum||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "发生错误，请检查顶点", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("发生错误，请检查顶点");
		}
		if(!isEdgeExist(v, u))
		{
			JOptionPane.showMessageDialog(null, "边不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("发生错误，边不存在");
		}
		arcNum=arcNum-1;
		arcs[u][v]=arcs[v][u]=INFINITY;
		setAllSpotRelation();
	}
	
	public void DeleteVex(int location) throws Exception //删除顶点，location为其在数组中的位置
	{		
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "顶点位置不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("顶点位置不存在");
		}
		int newVexNum=vexNum-1;
		//刷新vexs数组
		spot newVexs[]=new spot[newVexNum];
		int k=0;
		for(int i=0;i<newVexNum;i++)
		{
			if(i==location)
				continue;
			else
				newVexs[k++]=vexs[i];
		}
		//刷新arcs数组
		int newArcs[][]=new int[newVexNum][newVexNum];
		int ni;
		int nj;
		ni=nj=0;
		for(int i=0;i<newVexNum;i++)
		{
			if(i==location)
				continue;
			else
			{
				for(int j=0;j<newVexNum;j++)
				{
					if(j==location)
						continue;
					else
					{
						newArcs[ni][nj++]=arcs[i][j];
					}
				}
				ni++;
				nj=0;
			}
		}
		
		//将新数据写入MGraph
		vexNum=newVexNum;
		vexs=newVexs;
		arcs=newArcs;		
		setAllSpotRelation();
	}
	
	public void UpdateEdge(int v,int u,int newWeight) throws Exception //更新边的权值
	{
		if(v>vexNum||u>vexNum||newWeight<=0||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "请检查顶点及权值", "错误", JOptionPane.ERROR_MESSAGE);
			return;
			//throw new Exception("发生错误，请检查顶点及权值");
		}
		arcs[v][u]=arcs[u][v]=newWeight;
		setAllSpotRelation();

	}
		
	public void UpdateVexName(int location,String newName) throws Exception
	{
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "顶点位置不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
		//	throw new Exception("顶点位置错误");			
		}
		vexs[location].setName(newName);
	}
	
	public void UpdateVexInfo(int location ,String newInfo) throws Exception
	{
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "顶点位置不存在", "错误", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("顶点位置错误");
		}
		vexs[location].setInfo(newInfo);
	}
	
	public boolean isEdgeExist(int v,int u) throws Exception
	{
		if(v>=vexNum||u>=vexNum||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "发生错误，请检查顶点", "错误", JOptionPane.ERROR_MESSAGE);
			throw new Exception("发生错误，请检查顶点");
		}
		if(arcs[v][u]<INFINITY)
			return true;
		else
			return false;
	}
	
	public void setAllSpotRelation()
	{
		for(int i=0;i<vexNum;i++)
		{
			vexs[i].relationSpots.clear();
			for(int j=0;j<vexNum;j++)
			{
				if(i!=j&&arcs[i][j]<INFINITY)
				{
					vexs[i].relationSpots.add(vexs[j]);
				}
			}
		}
	}
	
	
}
