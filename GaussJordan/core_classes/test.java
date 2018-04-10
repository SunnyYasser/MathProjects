class test implements Cloneable{
	int model;
	test(int model)
	{
		this.model=model;
	}
	public static void main(String args[])throws CloneNotSupportedException{
		test c1 = new test(1);
		test c2 = (test)c1.clone();
		System.out.println(c2.model+1); 
	}
}