package sugang;

import java.util.ArrayList;

import mfilemanager.FileManager;
import miri.JangDAO;
import miri.JangDTO;
import presentation.PMenu;
import studentmem.MemberDAO;

public class SugangDAO {
	public static SugangDAO instance = new SugangDAO();
	ArrayList<JangDTO> sugangList = new ArrayList<JangDTO>();
	
	public void add(JangDTO sugang) {
		sugangList.add(sugang);
	}
	public int getSugangListSize() {
		return sugangList.size();
	}
	public ArrayList<JangDTO> getSugangList(){
		return sugangList;
	}
	public void printSugangList(String id) {
		for(int i = 0; i < sugangList.size(); i++) {
			if(sugangList.get(i).id.equals(id)) {
				System.out.println("[" + sugangList.get(i).number + "] " + sugangList.get(i).data);
			}
		}
	}
	
		}

