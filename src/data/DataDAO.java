package data;

import java.util.ArrayList;

import javax.swing.JButton;

import mfilemanager.FileManager;
import miri.JangDTO;

public class DataDAO {
	public static DataDAO instance = new DataDAO();

	public ArrayList<DataDTO> departmentList = new ArrayList<DataDTO>();		// 부서
	public ArrayList<DataDTO> subjectList = new ArrayList<DataDTO>();			// 학부
	public ArrayList<DataDTO> lectureList = new ArrayList<DataDTO>();			// 수강과목
	public ArrayList<JangDTO> jangList = new ArrayList<JangDTO>();				// 미리담기(수강신청목록)
	public ArrayList<JangDTO> sincheongList = new ArrayList<JangDTO>();
	
	int size = 0;
	String path = "data/";

	public void addDepartment(DataDTO department) {
		departmentList.add(department);
	}
	
	public void addSubject(DataDTO subject) {
		subjectList.add(subject);
	}
	
	public void addLecture(DataDTO lecture) {
		lectureList.add(lecture);
	}	
	
	public ArrayList<DataDTO> getDepartmentList(){
		return departmentList;
	}
	
	public ArrayList<DataDTO> getSubjectList(){
		return subjectList;
	}

	public ArrayList<DataDTO> getLectureList(){
		return lectureList;
	}	
	
	public int getDepartmentListSize() {
		return departmentList.size();
	}

	public int getSubjectListSize() {
		return subjectList.size();
	}
	
	public int getLectureListSize() {
		return lectureList.size();
	}	
	
	public void printDepartmentList() {
		for(int i = 0; i < departmentList.size(); i++) {
			System.out.println("[" + departmentList.get(i).num + "] " + departmentList.get(i).korName + " " + departmentList.get(i).engName);
		}
	}
	
	public void printSubjectList() {
		for(int i = 0; i < subjectList.size(); i++) {
			System.out.println("[" + subjectList.get(i).num + "] " + subjectList.get(i).korName + " " + subjectList.get(i).engName);
		}
	}
	
	public void printLectureList() {
		for(int i = 0; i < lectureList.size(); i++) {
			System.out.println("[" + lectureList.get(i).num + "] " + lectureList.get(i).korName + " " + lectureList.get(i).engName);
		}
	}	
	
	public void setDepartment(String engName) {						
		FileManager.instance.loadDepartment(path + engName);
		size = departmentList.size();
	}

	public void setSubject(String engName) {						
		FileManager.instance.loadSubject(path + engName);
		size = subjectList.size();
	}
	
	public void setLecture(String engName) {						
		FileManager.instance.loadLecture(path + engName);
		size = lectureList.size();
	}
	
}
