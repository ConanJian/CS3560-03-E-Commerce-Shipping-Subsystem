package Demo_Connection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import connection.*;
public class SqlServerConnectionDemo 
{
	//To-Do
	//each type have own connection or individually each have one
	
	//must test package
	public static void main(String[] args) throws SQLException, IOException 
	{
		//this place is only a testing ground
		//testing if can push to individual branch and can still merge into test branch
		String url = "jdbc:sqlserver://DESKTOP-S0HM9HP\\SQLEXPRESS:64784;databaseName="
				+ "WarehouseShippingSubsystem";
		String user = "conan";
		String pass = "password";
		SqlConnection connect = new SqlConnection(url,user,pass);
		
		PackageConnection p = new PackageConnection(connect);
		p.createPackage(1, 1, 0);
		
		
//		String path = "C:\\Users\\conan\\Desktop\\Cpp Fall 2021\\CS 3110 Formal\\Module 14\\Test.png";
//		File file = new File(path);
//		BufferedImage bImage = ImageIO.read(file);
//		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
//		ImageIO.write(bImage, "png", baoStream);
//		byte[] byteArray = baoStream.toByteArray();
//		InputStream inStream = new ByteArrayInputStream(byteArray);
//		
//		ShippingLabelConnection shipLabel = new ShippingLabelConnection(connect);
//		ArrayList<HashMap<String, Object>> test2 = shipLabel.getShippingLabel(2);
//		
//		byte[] byteStream = (byte[]) test2.get(0).get("label");
//		FileOutputStream s = new FileOutputStream("C:\\Users\\conan\\Desktop\\Label2.png");
//		s.write(byteStream);
//		s.close();
		
		connect.close();
		
		
//		PackageConnection temp = new PackageConnection(connect);
		
		//When testing create package, you must first create a label and then you may make a package.
		//User may be able to delete a package when they realized they made it wrong
//		ArrayList<HashMap<String, Object>> table = temp.getPackage(1);
//		for(int i = 0 ; i < table.size(); i++)
//		{
//			System.out.println(table.get(i));
//		}
		
	}
}
