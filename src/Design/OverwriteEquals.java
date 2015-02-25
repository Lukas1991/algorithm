package Design;

public class OverwriteEquals {

	/**
	 * @param args
	 */
	private int num;
	private String data;
	
	public OverwriteEquals(int num, String data){
		this.num=num;
		this.data=data;
	
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj==null) return false;
		if(obj==this) return true;
		if(obj.getClass()!=this.getClass()) return false;
		//cannot use instanceof, incase one is the subclass of the other
		
		OverwriteEquals obje=(OverwriteEquals)obj;
		if(this.num!=obje.num) return false;
		else if(obje.data == null){
			return (this.data == null);
		}else{
			return obje.data.equals(this.data);
		}
		
	}
	
	@Override
	public int hashCode(){
		int h=7;
		h=31*num+7;
		h=31*h + (data==null? 0:data.hashCode());
		return h;		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test=null;
		String test2="test";
		System.out.println(test2.equals(null));
		
	}

}
