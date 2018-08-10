package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SqlFileScanner {
	
	public static void main(String[] args)  {
		String filePath = "C:\\Users\\Administrator\\Desktop\\pig_house.sql";
		File file = new File(filePath);
		if(file == null || !file.exists())
		{
			System.out.println("�ļ�������");
			return;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String line = null;
			while((line = reader.readLine()) != null) {
				if(line.startsWith("CREATE TABLE")) {
					//CREATE TABLE `backup` (
					line = line.replace("CREATE TABLE `", "").replace("` (", "");
					System.out.println("==="+line+"===");
					
				}else if(line.startsWith("  `")) {
					//  `description` varchar(100) NOT NULL COMMENT '���������ļ�����Ϣ',
					line = line.replace("  `", "");
					String fieldName = line.substring(0, line.indexOf("`"));
					System.out.print(fieldName + "\t");
					line = line.replace(fieldName + "` ", "");
					String typeName = line.substring(0, line.indexOf(" "));
					System.out.print(typeName + "\t");
					if(line.contains("COMMENT '")) {
						System.out.println(line.substring(line.indexOf("'")+1, line.indexOf("',")));
					}else {
						System.out.println(" ");
					}
					
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
				try {
					if(reader != null)
						reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	

}
