package basicSet;

import dataStruct.MGraph;

public class Path //·���࣬����ͼ���ݺ����·��
{
	MGraph G;
	int [][]D;
	boolean [][][]P;
	
	public Path(MGraph _G/*ͼ*/,int[][] _D/*���·��������floyd���*/,boolean [][][]_P/*���·���м�㣬��floyd���*/) //��ʼ��Path
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
