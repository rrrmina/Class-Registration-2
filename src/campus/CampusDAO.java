package campus;

import java.util.ArrayList;

public class CampusDAO {
	
	public static CampusDAO instance = new CampusDAO();

	ArrayList<CampusDTO> campusList = new ArrayList<>();
	
	public void add(CampusDTO campus) {
		campusList.add(campus);
	}
	
	public int getCampusListSize() {
		return campusList.size();
	}
	
	public ArrayList<CampusDTO> getCampusList(){
		return campusList;
	}
	
	public void printCampusList() {
		for(int i = 0; i < campusList.size(); i++) {
			System.out.println("[" + campusList.get(i).campusNum + "] " + campusList.get(i).campusKorName + " " + campusList.get(i).campusEngName);
		}
	}
}
