package dataStruct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import basicSet.spot;

/*����ΪѰ����������м�·���Ĺ��߷���*/
public class GetAllSimplePath {
	MGraph G;
	GetAllSimplePath(MGraph g)
	{
		G=g;
	}
	public MGraph getG()
	{
		return G;
	}
	public void setG(MGraph g)
	{
		G = g;
	}
	
	public static Stack stack =new Stack<spot>();//����·����ʱ�ڵ��ջ
	public static ArrayList<Object[]> sers =new ArrayList<Object[]>();//����·������
	public static int RouteNum=0;
    public static String Result="";
	
	
	public static boolean isSpotInStack(spot s)
	{
        Iterator<spot> it = stack.iterator();  
        while(it.hasNext())
        {
        	 spot stmp = (spot) it.next();  
             if (s == stmp)  
                 return true;  
        }
        return false;
	}
	
	
    public static void showAndSavePath()  
    {  
    	RouteNum++;
        Object[] o = stack.toArray();  
        for (int i = 0; i < o.length; i++) {  
            spot nNode = (spot) o[i];  
              
            if(i < (o.length - 1))  
            {
                //System.out.print(nNode.getName() + "->");  
                Result+=nNode.getName() + "->";
            }
            else  
            {
               // System.out.print(nNode.getName());  
                Result+=nNode.getName();
            }
        }  
        sers.add(o); /* ת�� */  
        //System.out.println("\n");  
        Result+="\n";
    }  
 
    
    /* 
     * Ѱ��·���ķ���  
     * cNode: ��ǰ����ʼ�ڵ�currentNode 
     * pNode: ��ǰ��ʼ�ڵ����һ�ڵ�previousNode 
     * sNode: �������ʼ�ڵ�startNode 
     * eNode: �յ�endNode 
     */  
    public static void setRouteTOZero()//ÿ���µĲ�ѯǰ���н������
    {
    	RouteNum=0;
    	Result="";
    }
    
    public static boolean getPaths(spot cNode, spot pNode, spot sNode, spot eNode) {  
        spot nNode = null;  
        /* ������������ж�˵�����ֻ�·��������˳�Ÿ�·������Ѱ·������false */  
        if (cNode != null && pNode != null && cNode == pNode)  
            return false;  
  
        if (cNode != null) {  
            int i = 0;  
            /* ��ʼ�ڵ���ջ */  
            stack.push(cNode);  
            /* �������ʼ�ڵ�����յ㣬˵���ҵ�һ��·�� */  
            if (cNode == eNode)  
            {  
                /* ת������ӡ�����·��������true */  
                showAndSavePath();  
                return true;  
            }  
            /* �������,����Ѱ· */  
            else  
            {  
                /*  
                 * ���뵱ǰ��ʼ�ڵ�cNode�����ӹ�ϵ�Ľڵ㼯�а�˳������õ�һ���ڵ� 
                 * ��Ϊ��һ�εݹ�Ѱ·ʱ����ʼ�ڵ�  
                 */  
                nNode = cNode.getRelationSpots().get(i);  
                while (nNode != null) {  
                    /* 
                     * ���nNode���������ʼ�ڵ����nNode����cNode����һ�ڵ����nNode�Ѿ���ջ�� ��  
                     * ˵��������· ��Ӧ�������뵱ǰ��ʼ�ڵ������ӹ�ϵ�Ľڵ㼯��Ѱ��nNode 
                     */  
                    if (pNode != null  
                            && (nNode == sNode || nNode == pNode || isSpotInStack(nNode))) {  
                        i++;  
                        if (i >= cNode.getRelationSpots().size())  
                            nNode = null;  
                        else  
                            nNode = cNode.getRelationSpots().get(i);  
                        continue;  
                    }  
                    /* ��nNodeΪ�µ���ʼ�ڵ㣬��ǰ��ʼ�ڵ�cNodeΪ��һ�ڵ㣬�ݹ����Ѱ·���� */  
                    if (getPaths(nNode, cNode, sNode, eNode))/* �ݹ���� */  
                    {  
                        /* ����ҵ�һ��·�����򵯳�ջ���ڵ� */  
                        stack.pop();  
                    }  
                    /* ��������cNode�����ӹ�ϵ�Ľڵ㼯�в���nNode */  
                    i++;  
                    if (i >= cNode.getRelationSpots().size())  
                        nNode = null;  
                    else  
                        nNode = cNode.getRelationSpots().get(i);  
                }  
                /*  
                 * ��������������cNode�����ӹ�ϵ�Ľڵ�� 
                 * ˵������cNodeΪ��ʼ�ڵ㵽�յ��·���Ѿ�ȫ���ҵ�  
                 */  
                stack.pop();  
                return false;  
            }  
        } else  
            return false;  
    }  
    
    
	
    
}
