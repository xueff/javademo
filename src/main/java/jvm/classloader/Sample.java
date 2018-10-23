package jvm.classloader;
public class Sample
{
	public Sample() 
	{
		System.out.println("sample is loader by" + this.getClass().getClassLoader());
		new Dog() ;
	}
}

class Dog{
    
}