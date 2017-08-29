package dataIO;
import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import basicSet.*;
import dataStruct.*;

public class DataIO {
	File File_G;
	File File_Spot;
	
	public DataIO(String p_f_G,String p_f_S) //初始化指定文件
	{
		if(new File(p_f_G).exists())//文件存在则直接读取，不存在则创建
			File_G=new File(p_f_G);
		else
			File_G=new File("dataofgraph.txt");
		if(new File(p_f_S).exists())
			File_Spot=new File(p_f_S);	
		else
			File_Spot=new File("dataofspot.txt");
	}
	
	public DataIO()
	{
		this("","");
	}
	
	public void saveAll(MGraph G)
	{
		try
		{
			BufferedWriter bw_g = new BufferedWriter(new FileWriter(File_G)); //讲MGraph的数据写入文件
			bw_g.write(Integer.toString(G.getVexNum()));
			bw_g.newLine();
			bw_g.write(Integer.toString(G.getArcNum()));
			bw_g.newLine();
			for(int i=0;i<G.getVexNum();i++)
			{
				for(int j=0;j<G.getVexNum();j++)
					bw_g.write(Integer.toString(G.getArcs()[i][j])+" ");
				bw_g.newLine();
			}
			bw_g.flush();
			bw_g.close();
			
			BufferedWriter bw_s = new BufferedWriter(new FileWriter(File_Spot));//将MGraph中的vexs[]写入文件
			for(int i=0;i<G.getVexNum();i++)
			{
				bw_s.write(G.getVex(i).getNumber()+" "+G.getVex(i).getName()+" "+G.getVex(i).getInfo());
				bw_s.newLine();
			}
			bw_s.flush();
			bw_s.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public MGraph loadAll(MGraph G)
	{
		int vn=0;
		int an=0;
		int t_arcs[][]=null;
		spot t_vexs[] = null;
		try//读取文件
		{
			BufferedReader br_g = new BufferedReader(new FileReader(File_G));
			BufferedReader br_s = new BufferedReader(new FileReader(File_Spot));
			 vn=Integer.parseInt(br_g.readLine()) ;
			 an=Integer.parseInt(br_g.readLine());
			 t_arcs=new int[vn][vn];
			int i=0;
			 t_vexs=new spot[vn];
			while(br_g.ready())
			{
				String stmp=br_g.readLine();
				String tmp_s_value[]=stmp.split(" ");
				for(int j=0;j<vn;j++)
				{
					t_arcs[i][j]=Integer.parseInt(tmp_s_value[j]);
				}
				i++;
			}
			int k=0;
			while(br_s.ready())
			{
			
				String s[]=br_s.readLine().split(" ");
	            t_vexs[k]=new spot(Integer.parseInt(s[0]),s[1],s[2]);
	            k++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return G=new MGraph(vn, an, t_vexs, t_arcs);
	}

}
