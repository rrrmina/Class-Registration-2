package mfilemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import campus.CampusDAO;
import campus.CampusDTO;
import data.DataDAO;
import data.DataDTO;
import miri.JangDAO;
import miri.JangDTO;
import studentmem.MemberDAO;
import studentmem.MemberDTO;
import sugang.SugangDAO;

public class FileManager {
	public static FileManager instance = new FileManager();
	File file;
	FileReader fr;
	BufferedReader br;
	FileWriter fw;
	
	public void loadTemp() {
		try {
			String fileName = "data/temp";
			file = new File(fileName);
			
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);				
				MemberDAO.instance.getLoginList().clear();
				
					int count = 0;
					
					while (true) {						
						String data = br.readLine();
						if (data == null) 
							break;
						String values [] = new String[4];	
						values[0] = data;
						for(int i = 1; i < 4; i++) {
							values[i] = br.readLine();
						}																															
						MemberDTO login = new MemberDTO(values[0] , values[1] , values[2], values[3]);
						MemberDAO.instance.add(login);
						count += 1;					
					}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
		}
		
	}

	public void saveTemp() {
		try {
			
			fw = new FileWriter("data/temp");
			String data = "";
			ArrayList<MemberDTO> loginList = MemberDAO.instance.getLoginList();
			System.out.println("size = " + loginList.size());
			for(int i = 0; i < loginList.size(); i++) {
				MemberDTO member = loginList.get(i);
				data += member.id;
				data += "\n";
				data += member.password;
				data += "\n";
				data += member.name;
				data += "\n";
				data += member.birth;
				if(i != loginList.size() -1) {
					data += "\r\n";
				}
			}
			
//			System.out.println(data);
			
			fw.write(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {try {fw.close();} catch (IOException e) {}}
		}		
	}
	
	public void loadRoot(String fileName) {
		try {
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				if (fileName.equals("data/root")) {
					CampusDAO.instance.getCampusList().clear();
					while (true) {
						String data = br.readLine();
						if (data == null) {
							break;
						}
						String[] value = data.split(" ");
						CampusDTO campus = new CampusDTO(value[0], value[1], value[2]);
						CampusDAO.instance.add(campus);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//혹시 try안에서 오류날까봐 빼놓음
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
		} 
	}

	public void loadDepartment(String fileName) {
		//System.out.println("부서");
		
		try {
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				DataDAO.instance.getDepartmentList().clear();
				while (true) {
					String data = br.readLine();
					if (data == null) {
						break;}
					String[] value = data.split(" ");
					DataDTO datadto = new DataDTO(value[0], value[1], value[2]);
					DataDAO.instance.addDepartment(datadto);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
			if (br != null) {try {br.close();} catch (IOException e) {}}
			
		}
	}	

	public void loadSubject(String fileName) {
		//System.out.println("학과");
		
		
		try {
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				DataDAO.instance.getSubjectList().clear();
				while (true) {
					String data = br.readLine();
					if (data == null)
						break;
					String[] value = data.split(" ");
					DataDTO datadto = new DataDTO(value[0], value[1], value[2]);
					DataDAO.instance.addSubject(datadto);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
			if (br != null) {try {br.close();} catch (IOException e) {}}
		}
	}		
	
public void loadLecture(String fileName) {
		
		try {
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				DataDAO.instance.getLectureList().clear();
				while (true) {
					String data = br.readLine();
					if (data == null)
						break;
					String[] value = data.split(" ");
					String leftValue = "";
					for(int i = 1; i < value.length; i++) {	
						leftValue += value[i];
						if(i != value.length-1) {
							leftValue += " ";
						}
						
					}
					
					DataDTO datadto = new DataDTO(value[0], leftValue, "");
					DataDAO.instance.addLecture(datadto);				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
			if (br != null) {try {br.close();} catch (IOException e) {}}
		}
	}
	
	public void saveJang() {
		try {
			
			fw = new FileWriter("data/jang");
			String data = "";
			ArrayList<JangDTO> jangList = JangDAO.instance.getJangList();
			for(int i = 0; i < jangList.size(); i++) {
				JangDTO jang = jangList.get(i);
				data += jang.id;
				data += " ";
				data += jang.number;
				data += " ";
				data += jang.data;
				if(i != jangList.size() -1) {
					data += "\r\n";
				}
			}
			
			fw.write(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {try {fw.close();} catch (IOException e) {}}
		}
	}
	
	public void loadJang() {
		try {
			String fileName = "data/jang";
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
			//	DataDAO.instance.getDataList().clear();
				JangDAO.instance.getJangList().clear();
				while (true) {
					String data = br.readLine();
					if (data == null)
						break;
					String[] value = data.split(" ");
					
					String lastData = "";
					for(int i=2; i<value.length; i++) {
						lastData += value[i];
						lastData += " ";
					}
					
					JangDTO jang = new JangDTO(value[0], value[1], lastData);
					JangDAO.instance.add(jang);		
					
				}
			}
			//DataDAO.instance.printDataList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
			if (br != null) {try {br.close();} catch (IOException e) {}}
		}
	}

	public void saveSugang() {
		try {
			
			fw = new FileWriter("data/sugang");
			String data = "";
			ArrayList<JangDTO> sugangList = SugangDAO.instance.getSugangList();
			for(int i = 0; i < sugangList.size(); i++) {
				JangDTO sugang = sugangList.get(i);
				data += sugang.id;
				data += " ";
				data += sugang.number;
				data += " ";
				data += sugang.data;
				if(i != sugangList.size() -1) {
					data += "\r\n";
				}
			}
			
			fw.write(data);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {try {fw.close();} catch (IOException e) {}}
		}
	}
	
	public void loadSugang() {
		try {
			String fileName = "data/sugang";
			file = new File(fileName);
			if (file.exists()) {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				SugangDAO.instance.getSugangList().clear();
				while (true) {
					String data = br.readLine();
					if (data == null)
						break;
					String[] value = data.split(" ");
					
					String lastData = "";
					for(int i=2; i<value.length; i++) {
						lastData += value[i];
						lastData += " ";
					}
					
					JangDTO sugang = new JangDTO(value[0], value[1], lastData);
					SugangDAO.instance.add(sugang);		
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {try {fr.close();} catch (IOException e) {}}
			if (br != null) {try {br.close();} catch (IOException e) {}}
		}
	}
}
