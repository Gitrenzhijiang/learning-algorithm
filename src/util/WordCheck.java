package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCheck {

	public static void main(String[] args) {
		String filePath = "C:\\Users\\Administrator\\Desktop\\temp.txt";
		String wordPath = "C:\\Users\\Administrator\\Desktop\\word2txt.txt";
		File file = new File(filePath);
		File word = new File(wordPath);
		if(file == null || !file.exists() || !word.exists())
		{
			System.out.println("�ļ�������");
			return;
		}
		BufferedReader reader_file = null;
		BufferedReader reader_word = null;
		try {
			reader_file = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			reader_word = new BufferedReader(new InputStreamReader(new FileInputStream(word), "utf-8"));
			reader_word.mark(Integer.MAX_VALUE/6000);
			//����reader_file ��� reader_word ����û��
			String line = reader_file.readLine();
			boolean tonext = false;//
			while(line != null && !line.equals("") && !line.startsWith(" ")) {
				if(!line.startsWith("==="))
				{
					line = reader_file.readLine();
					continue;
				}
				line = line.replace("===", "");
				String tableName = line;
				//��ѯ
				String temp = null;
				boolean isFind = false;
				boolean isok = true;
				while((temp = reader_word.readLine()) != null) {
					
					if(temp.equals("���	����	��������	����	�Ƿ�Ψһ	�Ƿ�Ϊ��	��ע")) {
						continue;
					}
					if(temp.trim().equals("")) {//�������
						continue;
					}
					
					if(temp.matches("^[0-9].*") && isFind) {//���ֿ�ͷ
						
						String text_file = reader_file.readLine();
						if(text_file.startsWith("===") || text_file.trim().equals("")) {
							isok = false;
							tonext = true;//��׼�Ѿ���ת������һ����
							break;
						}
						String trueFieldName = text_file.substring(0, text_file.indexOf("\t"));
						// temp = ;3	�����  description	VARCHAR(120)	��	��	�����Զ���
						
						if(!temp.contains(trueFieldName)) {
							isok = false;
						}
						
					}else if(!isFind){//û���ҵ�
						int engIndex = 0;
						for(int i = 0;i < temp.length();i++) {
							char cchar = temp.charAt(i);
							if((cchar >= 'A' && cchar <= 'Z') || (cchar >= 'a' && cchar <= 'z')) {
								engIndex = i;
								break;
							}
						}
						String name = temp.substring(engIndex, temp.length());
						if(name.endsWith(")")){
							name = name.replace(")", "");
						}
						if(tableName.equalsIgnoreCase(name))
						{
							isFind = true;
						}
					}else if(isFind && temp.matches("[\\u4e00-\\u9fa5]*_*[\\u4e00-\\u9fa5]*\\(*[a-zA-Z]+_*[a-zA-Z]+\\)*")){//�ҵ���
						//�˳�
						break;
					}
				}
				
				//���Ƿ�����
				if(tonext == true) {//�����ֶ�
					tonext = false;
				}else if((line = reader_file.readLine()) != null && !line.startsWith("===")) {
					isok = false;
				}
				
				if(!isFind) {
					System.out.println("table:" + tableName + " notFind!!!");
				}else {
					if(!isok) {
						System.out.println(tableName + " has error");
					}
				}
				
				reader_word.reset();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(reader_file != null)
					reader_file.close();
				if(reader_word != null)
					reader_word.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
