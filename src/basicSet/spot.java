package basicSet;

import java.util.ArrayList;

//�����࣬�൱�ڶ���
public class spot {
	int number; //������
	String name;//��������
	String info;//�������
	
    public ArrayList<spot> relationSpots = new ArrayList<spot>();  //������ýڵ�ֱ�������Ľڵ�
	 
	 
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
