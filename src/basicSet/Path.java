package basicSet;

import dataStruct.MGraph;

public class Path //路径类，保存图数据和最短路径
{
	MGraph G;
	int [][]D;
	boolean [][][]P;
	
	public Path(MGraph _G/*图*/,int[][] _D/*最短路径长，从floyd获得*/,boolean [][][]_P/*最短路径中间点，从floyd获得*/) //初始化Path
	{
		G=_G;
		D=_D;
		P=_P;
	}

	public MGraph getG() {
		return G;
	}

	public void setG(MGraph g) {
		G = g;
	}

	public int[][] getD() {
		return D;
	}

	public void setD(int[][] d) {
		D = d;
	}

	public boolean[][][] getP() {
		return P;
	}

	public void setP(boolean[][][] p) {
		P = p;
	}
	
	

}
