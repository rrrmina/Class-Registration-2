package studentmem;

import java.util.ArrayList;

import mfilemanager.FileManager;
import studentmem.MemberDAO;
import studentmem.MemberDTO;

public class MemberDAO {
	public static MemberDAO instance = new MemberDAO();
	ArrayList<MemberDTO> loginList = new ArrayList<MemberDTO>();
	
	public void add(MemberDTO member) {
		loginList.add(member);
	}
	
	public int getLoginListSize() {
		return loginList.size();
	}
	
	public ArrayList<MemberDTO> getLoginList(){
		return loginList;
	}
	
	
	public void printLoginList() {
		for(int i=1; i<loginList.size(); i++) {
			System.out.println("[" + i  + "]" +"아이디: "+ loginList.get(i).id + " , 비밀번호 :  " + loginList.get(i).password + " , 이름 :  " + loginList.get(i).name+" , 생일 : " +loginList.get(i).birth);
		}
	}
	
	public int checkLogin(String id , String pw) {
		for(int i = 0; i < loginList.size(); i++) {
			String dbid = loginList.get(i).id;
			String dbpw = loginList.get(i).password;
			
			if(dbid.equals(id) && dbpw.equals(pw)) {
			if(dbid.equals("admin")) {
				return -2;
			}
				return i;
			}
		}		
		return -1;
	}
	
	public int idcheck(String id) {
		for(int i = 0; i < loginList.size(); i++) {
			String dbid = loginList.get(i).id;
			if(dbid.equals(id)) {
				return 1;
			}
		}		
		return -1;
	}
	
	public int findId(String name , String birth) {
		for(int i = 0; i < loginList.size(); i++) {
			String dbname = loginList.get(i).name;
			String dbbirth = loginList.get(i).birth;
			
			if(dbname.equals(name) && dbbirth.equals(birth)) {
				return i;
			}
		}		
		return -1;
	}
	
	public int findpw(String id, String name , String birth) {
		for(int i = 0; i < loginList.size(); i++) {
			String dbid = loginList.get(i).id;
			String dbname = loginList.get(i).name;
			String dbbirth = loginList.get(i).birth;
			
			if(dbid.equals(id)&&dbname.equals(name) && dbbirth.equals(birth)) {
				return i;
			}
		}		
		return -1;
	}
	
	
	public void joinMember(MemberDTO member) {
		MemberDAO.instance.add(member);
		FileManager.instance.saveTemp();
	}
	
	public void removemem (int index) {
		loginList.remove(index);
		FileManager.instance.saveTemp();
	}

	
	
}
