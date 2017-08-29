package dataStruct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import basicSet.spot;

/*该类为寻找亮点间所有简单路径的工具方法*/
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
	
	public static Stack stack =new Stack<spot>();//保存路径临时节点的栈
	public static ArrayList<Object[]> sers =new ArrayList<Object[]>();//保存路径集合
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
        sers.add(o); /* 转储 */  
        //System.out.println("\n");  
        Result+="\n";
    }  
 
    
    /* 
     * 寻找路径的方法  
     * cNode: 当前的起始节点currentNode 
     * pNode: 当前起始节点的上一节点previousNode 
     * sNode: 最初的起始节点startNode 
     * eNode: 终点endNode 
     */  
    public static void setRouteTOZero()//每次新的查询前进行结果重置
    {
    	RouteNum=0;
    	Result="";
    }
    
    public static boolean getPaths(spot cNode, spot pNode, spot sNode, spot eNode) {  
        spot nNode = null;  
        /* 如果符合条件判断说明出现环路，不能再顺着该路径继续寻路，返回false */  
        if (cNode != null && pNode != null && cNode == pNode)  
            return false;  
  
        if (cNode != null) {  
            int i = 0;  
            /* 起始节点入栈 */  
            stack.push(cNode);  
            /* 如果该起始节点就是终点，说明找到一条路径 */  
            if (cNode == eNode)  
            {  
                /* 转储并打印输出该路径，返回true */  
                showAndSavePath();  
                return true;  
            }  
            /* 如果不是,继续寻路 */  
            else  
            {  
                /*  
                 * 从与当前起始节点cNode有连接关系的节点集中按顺序遍历得到一个节点 
                 * 作为下一次递归寻路时的起始节点  
                 */  
                nNode = cNode.getRelationSpots().get(i);  
                while (nNode != null) {  
                    /* 
                     * 如果nNode是最初的起始节点或者nNode就是cNode的上一节点或者nNode已经在栈中 ，  
                     * 说明产生环路 ，应重新在与当前起始节点有连接关系的节点集中寻找nNode 
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
                    /* 以nNode为新的起始节点，当前起始节点cNode为上一节点，递归调用寻路方法 */  
                    if (getPaths(nNode, cNode, sNode, eNode))/* 递归调用 */  
                    {  
                        /* 如果找到一条路径，则弹出栈顶节点 */  
                        stack.pop();  
                    }  
                    /* 继续在与cNode有连接关系的节点集中测试nNode */  
                    i++;  
                    if (i >= cNode.getRelationSpots().size())  
                        nNode = null;  
                    else  
                        nNode = cNode.getRelationSpots().get(i);  
                }  
                /*  
                 * 当遍历完所有与cNode有连接关系的节点后， 
                 * 说明在以cNode为起始节点到终点的路径已经全部找到  
                 */  
                stack.pop();  
                return false;  
            }  
        } else  
            return false;  
    }  
    
    
	
    
}
