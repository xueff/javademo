package jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义classloader
 * @author liuxiaochen
 *
 */
public class MyClassLoader extends ClassLoader
{
	String path = "d:\\";
	String name;
	String fileType = ".class";
	
	public MyClassLoader(String name) {
		super() ;
		this.name = name ;
	}
	
	public MyClassLoader(ClassLoader classloader, String name) {
		super(classloader) ;
		this.name = name ;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] data = loadClassData(name) ;
		return defineClass(name, data, 0, data.length) ;
	}
	
	

	@Override
	public String toString()
	{
		return this.name ;
	}

	private byte[] loadClassData(String name)
	{
		byte[] data = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		try
		{
			this.name = this.name.replace(".", "\\");
			in = new FileInputStream(new File(path + name + fileType));
			
			baos = new ByteArrayOutputStream();
			int cnt = 0;
			while (-1 != (cnt = in.read()))
			{
				baos.write(cnt);
			}
			data = baos.toByteArray();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				in.close() ;
				baos.close() ;
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return data;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		MyClassLoader loader1 = new MyClassLoader("loader1") ;
		loader1.setPath("d:\\") ;
		
		MyClassLoader loader2 = new MyClassLoader(loader1, "loader2") ;
		loader2.setPath("d:\\myapp\\loader2lib\\") ;
		
		MyClassLoader loader3 = new MyClassLoader(null, "loader3") ;
		loader3.setPath("d:\\myapp\\syslib\\") ;
		
		System.out.println("-------------");
		test(loader1) ;
		System.out.println("-------------");
		test(loader2) ;
		System.out.println("-------------");
//		test(loader3) ;
	}
	
	public static void test(ClassLoader classloder) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> classzz = classloder.loadClass("Sample") ;
		Object instance = classzz.newInstance() ;
	}
}