package miri;

import java.util.ArrayList;

import data.DataDTO;
import mfilemanager.FileManager;
import miri.JangDAO;
import miri.JangDTO;
import presentation.PMenu;
import studentmem.MemberDAO;

public class JangDAO {
	public static JangDAO instance = new JangDAO();
	ArrayList<JangDTO> jangList = new ArrayList<>();
	public void add(JangDTO jang) {
		jangList.add(jang);
	}
	public int getJangListSize() {
		return jangList.size();
	}
	public ArrayList<JangDTO> getJangList(){
		return jangList;
	}
	public void printJangList() {
		System.out.println("jang size : " + jangList.size());
		for(int i = 0; i < jangList.size(); i++) {
			System.out.println(jangList.get(i).id);
			System.out.println(jangList.get(i).number);
			System.out.println(jangList.get(i).data);
		}
	}
	public boolean checkItem(String loginId , String number) {
		for(int i = 0; i < jangList.size(); i++) {
			JangDTO jang = jangList.get(i);
			if(jang.number.equals(number) && jang.id.equals(loginId)) {
				return true;
			}
		}
		return false;
	}
	public void removeItem(int index) {
		jangList.remove(index);
		
		FileManager.instance.saveJang();
	}
	
	public ArrayList<JangDTO> getMyJangList(String loginId) {
		
		ArrayList<JangDTO> myList = new ArrayList<>();
		
		for(int i=0; i<jangList.size(); i++) {
			if(jangList.get(i).id.equals(loginId)) {
				myList.add(jangList.get(i));
			}
		}
		
		return myList;
	}
	
	public void previewMyJangList() {
		if(JangDAO.instance.getJangListSize() == 0) {
			FileManager.instance.loadJang();
		}
		
		String loginId = MemberDAO.instance.getLoginList().get(PMenu.loginCheck).id;
		
		ArrayList<JangDTO> myList = JangDAO.instance.getMyJangList(loginId);
		System.out.println("== 미리담은 목록 ==");
		for(int i=0; i<myList.size(); i++) {
			System.out.println("[" + myList.get(i).number + "] " + myList.get(i).data);
		}
		
	}
	public int getmyListSize() {
		if(JangDAO.instance.getJangListSize() == 0) {
			FileManager.instance.loadJang();
		}
		
		String loginId = MemberDAO.instance.getLoginList().get(PMenu.loginCheck).id;
		
		ArrayList<JangDTO> myList = JangDAO.instance.getMyJangList(loginId);
		return myList.size();
	}
	
	public int getJangNumber(String num) {
		int index = 0;
		
		for(int i=0; i<jangList.size(); i++) {
			if(jangList.get(i).number.equals(num)) {
				index = i;
			}
		}
		return index;
	}
	
	
}