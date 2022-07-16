package presentation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import campus.CampusDAO;
import data.DataDAO;
import data.DataDTO;
import mfilemanager.FileManager;
import miri.JangDAO;
import miri.JangDTO;
import studentmem.MemberDAO;
import studentmem.MemberDTO;
import sugang.SugangDAO;


public class PSincheong {
	static PSincheong instance = new PSincheong();
	 
	public PSincheong() {
		FileManager.instance.loadRoot("data/root");
	}

	public void campusMain() {
		System.out.println();
		System.out.println("---학생카드---");
		MemberDTO me = MemberDAO.instance.getLoginList().get(PMenu.loginCheck);
		System.out.println("아이디 : "+me.id);
		System.out.println("이름 : "+me.name);
		System.out.println("생일 : "+me.birth);
		System.out.println("-----------");
		while(true) {
			System.out.println();
			System.out.println("메뉴");
			System.out.println("1. 미리담기");
			System.out.println("2. 수강신청");
			System.out.println("3. 신청 내역 조회");
			System.out.println("0. 이전 화면으로");

			int selection = PMenu.scan.nextInt();//메뉴 고르기
			if(selection==0) {
				break;
			}
			else if(selection==1) {//미리담기
				while(true) {

					System.out.println("< 캠퍼스  >");
					int size = CampusDAO.instance.getCampusListSize();
					CampusDAO.instance.printCampusList();
					System.out.println("[0] 이전으로");

					int choice = PMenu.scan.nextInt();//캠퍼스 선택

					if(choice == 0) { break; }
					else if(choice == 1 || choice == 2) {
						DataDAO.instance.setDepartment(CampusDAO.instance.getCampusList().get(choice - 1).campusEngName);
						while(true) {
							DataDAO.instance.printDepartmentList();
							System.out.println("[0] 이전으로");

							int choice1 = PMenu.scan.nextInt();//대학 선택

							if(choice1 == 0) { break; }
							else {	
								int number1 = 0;
								for(int i=0; i<DataDAO.instance.getDepartmentListSize(); i++) {
									if(DataDAO.instance.getDepartmentList().get(i).num.equals(choice1 + "")) {
										number1 = i;
									}
								}

								DataDAO.instance.setSubject(DataDAO.instance.getDepartmentList().get(number1).engName);
								while(true) {
									DataDAO.instance.printSubjectList();
									System.out.println("[0] 이전으로");

									int choice2 = PMenu.scan.nextInt(); //학과 선택

									if(choice2 == 0) { break; }
									else {
										int number2 = 0;
										for(int i=0; i<DataDAO.instance.getSubjectListSize(); i++) {
											if(DataDAO.instance.getSubjectList().get(i).num.equals(choice2 + "")) {
												number2 = i;
											}
										}

										DataDAO.instance.setLecture(DataDAO.instance.getSubjectList().get(number2).engName);

										while(true) {
											DataDAO.instance.printLectureList();
											System.out.println("[0] 이전으로");

											System.out.println("[메세지] 미리 담기 할 수강과목을 선택해주세요.");
											int choice3 = PMenu.scan.nextInt();

											// 미리담기--------------------------------------------------------------------------------------------------------
											if(choice3 == 0) { break; }
											else {

												if(JangDAO.instance.getJangListSize() == 0) {
													FileManager.instance.loadJang();
												}

												int index = -1;
												for(int i=0; i<DataDAO.instance.getLectureListSize(); i++) {
													if(DataDAO.instance.getLectureList().get(i).num.equals(choice3 + "")) {
														index = i;
												
													}
												}
//												int idx = -1;
//												String loginId = null;
//												String jangId = null;
//												for(int i=0; i<JangDAO.instance.getJangListSize(); i++) {
//													if(JangDAO.instance.getJangList().get(i).number.equals(choice3 + "")) {
//														idx = i;
//														loginId = MemberDAO.instance.getLoginList().get(PMenu.loginCheck).id;
//														jangId = JangDAO.instance.getJangList().get(idx).id;
//												
//													}
//												}
//												
//												
//												
//												
//												
//												
//												String strChoice3 = choice3 + "";
//												if(strChoice3.equals(JangDAO.instance.getJangList().get(idx).number) && loginId.equals(jangId)) {
//													System.out.println("이미 신청한 강의 입니다.");
//													continue;
//												}
												String id = MemberDAO.instance.getLoginList().get(PMenu.loginCheck).id;
												DataDTO lecture = DataDAO.instance.lectureList.get(index);
												JangDTO jang = new JangDTO(id, lecture.num, lecture.korName + " " + lecture.engName); 
												System.out.println(lecture.num);
												System.out.println(lecture.korName);
												System.out.println(lecture.engName);
												
												JangDAO.instance.add(jang);
												FileManager.instance.saveJang();
												
												System.out.println("[메세지] 미리 담기 성공.");
											
											// ------------------------------------------------------------------------------------------------------------
											}
									}
								}
							}
						}
					}
					}
			}
			}else if(selection==2) {//수강신청
				while(true) {
					System.out.println("미리 담기 한 강좌 수 : "+JangDAO.instance.getmyListSize());
					JangDAO.instance.previewMyJangList();
					System.out.println("[0] 이전으로");

					System.out.println("[메세지] 수강 신청 할 과목을 선택하세요.");
					String gangjwa = PMenu.scan.next();

					if(gangjwa.equals("0")) {
						break;
					}else {
						if(SugangDAO.instance.getSugangListSize() == 0) {
							FileManager.instance.loadSugang();
						}

						int index = JangDAO.instance.getJangNumber(gangjwa);

						SugangDAO.instance.add(JangDAO.instance.getJangList().get(index));
						FileManager.instance.saveSugang();

						JangDAO.instance.removeItem(index);
			
				}
			}
			}else if(selection==3) {//신청 내역 조회
				while(true) {
					
				System.out.println("1. 미리담기함 조회");
				System.out.println("2. 수강신청 내역 조회");
				System.out.println("0. 이전으로");

				int s =PMenu.scan.nextInt();

				if(s==1) {//미리담기
					JangDAO.instance.previewMyJangList();
					
				}else if(s==2) {//수강신청
					if(SugangDAO.instance.getSugangListSize() == 0) {
						FileManager.instance.loadSugang();
					}
					String id = MemberDAO.instance.getLoginList().get(PMenu.loginCheck).id;
					SugangDAO.instance.printSugangList(id);
					
				}else if(s==0) {
					break;
				}
				}
			}
	} 
}
	}

	
	
	
	
	