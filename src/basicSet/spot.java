package basicSet;

import java.util.ArrayList;

//景点类，相当于顶点
public class spot {
	int number; //景点编号
	String name;//景点名称
	String info;//景点介绍
	
    public ArrayList<spot> relationSpots = new ArrayList<spot>();  //保存与该节点直接相连的节点
	 
	 
	public int getNumber()
	{
		return number;
	}

	public void setNumber(int _number)
	{
		this.number = _number;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info) 
	{
		this.info = info;
	}

	public spot(int no,String nm,String _info)
	{
		setNumber(no);
		setName(nm);
		setInfo(_info);
		
	}
	
	public spot(int no)
	{
		this(no,null,null);
	}
	
	 public ArrayList<spot> getRelationSpots() 
	 {  
	        return relationSpots;  
	 }  
	  
	 public void setRelationSpots(ArrayList<spot> relationSpots) 
     {  
	        this.relationSpots = relationSpots;  
	 }  

}
