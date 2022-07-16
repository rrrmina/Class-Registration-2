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
		System.out.println("---�л�ī��---");
		MemberDTO me = MemberDAO.instance.getLoginList().get(PMenu.loginCheck);
		System.out.println("���̵� : "+me.id);
		System.out.println("�̸� : "+me.name);
		System.out.println("���� : "+me.birth);
		System.out.println("-----------");
		while(true) {
			System.out.println();
			System.out.println("�޴�");
			System.out.println("1. �̸����");
			System.out.println("2. ������û");
			System.out.println("3. ��û ���� ��ȸ");
			System.out.println("0. ���� ȭ������");

			int selection = PMenu.scan.nextInt();//�޴� ����
			if(selection==0) {
				break;
			}
			else if(selection==1) {//�̸����
				while(true) {

					System.out.println("< ķ�۽�  >");
					int size = CampusDAO.instance.getCampusListSize();
					CampusDAO.instance.printCampusList();
					System.out.println("[0] ��������");

					int choice = PMenu.scan.nextInt();//ķ�۽� ����

					if(choice == 0) { break; }
					else if(choice == 1 || choice == 2) {
						DataDAO.instance.setDepartment(CampusDAO.instance.getCampusList().get(choice - 1).campusEngName);
						while(true) {
							DataDAO.instance.printDepartmentList();
							System.out.println("[0] ��������");

							int choice1 = PMenu.scan.nextInt();//���� ����

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
									System.out.println("[0] ��������");

									int choice2 = PMenu.scan.nextInt(); //�а� ����

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
											System.out.println("[0] ��������");

											System.out.println("[�޼���] �̸� ��� �� ���������� �������ּ���.");
											int choice3 = PMenu.scan.nextInt();

											// �̸����--------------------------------------------------------------------------------------------------------
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
//													System.out.println("�̹� ��û�� ���� �Դϴ�.");
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
												
												System.out.println("[�޼���] �̸� ��� ����.");
											
											// ------------------------------------------------------------------------------------------------------------
											}
									}
								}
							}
						}
					}
					}
			}
			}else if(selection==2) {//������û
				while(true) {
					System.out.println("�̸� ��� �� ���� �� : "+JangDAO.instance.getmyListSize());
					JangDAO.instance.previewMyJangList();
					System.out.println("[0] ��������");

					System.out.println("[�޼���] ���� ��û �� ������ �����ϼ���.");
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
			}else if(selection==3) {//��û ���� ��ȸ
				while(true) {
					
				System.out.println("1. �̸������ ��ȸ");
				System.out.println("2. ������û ���� ��ȸ");
				System.out.println("0. ��������");

				int s =PMenu.scan.nextInt();

				if(s==1) {//�̸����
					JangDAO.instance.previewMyJangList();
					
				}else if(s==2) {//������û
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

	
	
	
	
	