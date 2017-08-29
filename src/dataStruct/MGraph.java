package dataStruct;

import java.util.*;

import javax.swing.JOptionPane;

import basicSet.*;

/*ͼ�Ķ���ͻ�������*/
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
	
	public int locateVex(int n)//��λ������
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
			JOptionPane.showMessageDialog(null, "�߲�����,���鶥��", "����", JOptionPane.ERROR_MESSAGE);
			throw new Exception("�����������鶥��");
		}
		return arcs[u][v];
	}
	
	public void createUDN()//��ʼ����������
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("��ֱ�����ͼ�Ķ�������ͼ�ı���:");
		vexNum = sc.nextInt();
		arcNum = sc.nextInt();
		vexs = new spot[vexNum];
		//System.out.println("��ֱ�����ͼ�ĸ�������ı��:");
		for(int v=0;v<vexNum;v++)
		{
			vexs[v]=new spot(v);
			
		}
		System.out.println("��ֱ�����ͼ��Ӧ�����ŵ�����:");
		for(int v=0;v<vexNum;v++)
		{
			System.out.println(vexs[v].getNumber());
			vexs[v].setName(sc.next().trim());
		}
		System.out.println("��ֱ�����ͼ��Ӧ�����ź����ƵĽ��ܣ�");
		for(int v=0;v<vexNum;v++)
		{
			System.out.println(vexs[v].getNumber()+"  "+vexs[v].getName());
			vexs[v].setInfo(sc.next().trim());
		}
		
		arcs=new int[vexNum][vexNum];
		for (int v = 0; v < vexNum; v++)
			// ��ʼ���ڽӾ���
			for (int u = 0; u < vexNum; u++)
				arcs[v][u] = INFINITY;
		
		System.out.println("������������㣨���㣩���֮��ıߵ�Ȩֵ��·�����ȣ�[vex1 vex2 value]��");
		for(int k=0;k<arcNum;k++)
		{
			int v=locateVex(sc.nextInt());
			int u=locateVex(sc.nextInt());
			arcs[v][u]=arcs[u][v]=sc.nextInt();
		}
		
		setAllSpotRelation();
	}
	
	public spot getVex(int v) throws Exception  // ����v��ʾ����ֵ�� 0 <= v < vexNum
	{
		if (v < 0 || v >= vexNum)
			throw new Exception("��" + v + "�����㲻����!");
		return vexs[v];
	}
	
	public spot useNumGetVex(int n) throws Exception //�ö���ĵ��Ų����ڶ��������е�λ�ò����ص�
	{
		boolean flag=false;

		for(int i=0;i<vexNum;i++)
		{
			if(vexs[i].getNumber()==n)
				return vexs[i];
		}
		throw new Exception("���Ϊ"+n+"�Ķ��㲻����");
	}
	
	public int useNumGetVexLoaction(int n) throws Exception//�ö���ĵ��Ų����ڶ��������е�λ�ò�����λ�ñ��
	{
		boolean flag=false;

		for(int i=0;i<vexNum;i++)
		{
			if(vexs[i].getNumber()==n)
				return i;
		}
		throw new Exception("���Ϊ"+n+"�Ķ��㲻����");
	}
			
	public int firstAdjVex(int v) throws Exception // ����v�ĵ�һ���ڽӵ㣬��vû���ڽӵ��򷵻�-1�� 0 <= v < vexNum
{
		if (v < 0 && v >= vexNum)
			throw new Exception("��" + v + "�����㲻����!");

		for (int j = 0; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}
			
	public int nextAdjVex(int v, int w) throws Exception// ����v�����w����һ���ڽӵ㣬��w��v�����һ���ڽӵ㣬�򷵻�-1������0��v, w<vexNum
   {
		if (v < 0 && v >= vexNum)
			throw new Exception("��" + v + "�����㲻����!");

		for (int j = w + 1; j < vexNum; j++)
			if (arcs[v][j] != 0 && arcs[v][j] < INFINITY)
				return j;

		return -1;
	}	
	
	public void InsertVex(int number ,String _name,String _info)//��Ӷ���,�������������д洢Ϊ�����number�޹�
	{
		int newVexNum=vexNum+1;
		//ˢ��vexs����	
		spot newVexs[]=new spot[newVexNum];
		for(int i=0;i<newVexNum-1;i++)
		{
			newVexs[i]=vexs[i];
		}
	    newVexs[newVexNum-1]=new spot(number,_name,_info);
	    
	    //ˢ��arcs����
	    
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
	    
	    //��������д��MGraph
	    vexNum=newVexNum;
	    vexs=newVexs;
	    arcs=newArcs;	   	
	    setAllSpotRelation();
	}
	
	public void InsertEdge(int v,int u,int weight) throws Exception//����� v,u,Ϊ���㣬weightΪȨֵ
	{
		if(v>=vexNum||u>=vexNum||weight<=0||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "�����������鶥�㼰Ȩֵ", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("�����������鶥�㼰Ȩֵ");
		}
		if(isEdgeExist(v, u))
		{
			JOptionPane.showMessageDialog(null, "���Ѵ��ڣ��������", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("�������󣬱��Ѵ��ڣ��������");
		}
		arcNum=arcNum+1;
		arcs[v][u]=arcs[u][v]=weight;
		setAllSpotRelation();
	}
	
	public void DeleteEdge(int v,int u) throws Exception//ɾ���ߣ�v,uΪ�ߵ���������
	{
		if(v>=vexNum||u>=vexNum||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "�����������鶥��", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("�����������鶥��");
		}
		if(!isEdgeExist(v, u))
		{
			JOptionPane.showMessageDialog(null, "�߲�����", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("�������󣬱߲�����");
		}
		arcNum=arcNum-1;
		arcs[u][v]=arcs[v][u]=INFINITY;
		setAllSpotRelation();
	}
	
	public void DeleteVex(int location) throws Exception //ɾ�����㣬locationΪ���������е�λ��
	{		
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "����λ�ò�����", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("����λ�ò�����");
		}
		int newVexNum=vexNum-1;
		//ˢ��vexs����
		spot newVexs[]=new spot[newVexNum];
		int k=0;
		for(int i=0;i<newVexNum;i++)
		{
			if(i==location)
				continue;
			else
				newVexs[k++]=vexs[i];
		}
		//ˢ��arcs����
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
		
		//��������д��MGraph
		vexNum=newVexNum;
		vexs=newVexs;
		arcs=newArcs;		
		setAllSpotRelation();
	}
	
	public void UpdateEdge(int v,int u,int newWeight) throws Exception //���±ߵ�Ȩֵ
	{
		if(v>vexNum||u>vexNum||newWeight<=0||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "���鶥�㼰Ȩֵ", "����", JOptionPane.ERROR_MESSAGE);
			return;
			//throw new Exception("�����������鶥�㼰Ȩֵ");
		}
		arcs[v][u]=arcs[u][v]=newWeight;
		setAllSpotRelation();

	}
		
	public void UpdateVexName(int location,String newName) throws Exception
	{
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "����λ�ò�����", "����", JOptionPane.ERROR_MESSAGE);
			return ;
		//	throw new Exception("����λ�ô���");			
		}
		vexs[location].setName(newName);
	}
	
	public void UpdateVexInfo(int location ,String newInfo) throws Exception
	{
		if(location>=vexNum)
		{
			JOptionPane.showMessageDialog(null, "����λ�ò�����", "����", JOptionPane.ERROR_MESSAGE);
			return ;
			//throw new Exception("����λ�ô���");
		}
		vexs[location].setInfo(newInfo);
	}
	
	public boolean isEdgeExist(int v,int u) throws Exception
	{
		if(v>=vexNum||u>=vexNum||v<0||u<0)
		{
			JOptionPane.showMessageDialog(null, "�����������鶥��", "����", JOptionPane.ERROR_MESSAGE);
			throw new Exception("�����������鶥��");
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
