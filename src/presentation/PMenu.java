package presentation;

import java.util.Scanner;

import mfilemanager.FileManager;
import miri.JangDAO;
import studentmem.MemberDAO;
import studentmem.MemberDTO;
import sugang.SugangDAO;

public class PMenu {
	
	
	static Scanner scan = new Scanner(System.in);
	public static int loginCheck;
	static int check; //�α��� ���� -1 : �ƿ�, 1 : ��, -2 : ������
	static int idfind;
	static int pwfind;
	static int checkid;
	static int count; //�л��� ���� üũ �кλ�: 19,608��_������Ű, �������б� �л���, https://namu.wiki/w/%EB%AA%85%EC%A7%80%EB%8C%80%ED%95%99%EA%B5%90


	public PMenu() {
		loginCheck = -1;
		check = -1;
		count = 0;
		FileManager.instance.loadTemp();
		
		
		boolean run = true;
		while(run) {
			System.out.println("�������б�  ������û");
			System.out.println("1. ȸ������");
			System.out.println("2. �α���");
			System.out.println("3. ���̵�,��й�ȣ ã��");
			System.out.println("4. �α׾ƿ�");
			System.out.println("5. Ż��");
			System.out.println("0. ����");

			int sel = scan.nextInt();

			if(sel == 1) {
				if(count>19609) {
					System.out.println("[�޼���] ȸ�� ���� ���� �ο����� �ʰ��Ǿ����ϴ�.");
				}else {
				System.out.println("< ȸ������  >");
				System.out.println("���̵� �Է��ϼ���.");
				String id = scan.next();
				checkid= MemberDAO.instance.idcheck(id);
				if(checkid==1) {
					System.out.println("�ߺ��� ���̵� �Դϴ�. �ٽ� �Է����ּ���.");
					id = scan.next();
				}
				System.out.println("�н����带 �Է��ϼ���.");
				String password = scan.next();

				if(password.length()>8){ //��й�ȣ �ڸ��� ����
					System.out.println("[�޼���] 8�ڸ� ���� ��й�ȣ�� �Է����ּ���.");
					password = scan.next();
				}else if(password.length()<2){
					System.out.println("[�޼���] 2�ڸ� �̻� ��й�ȣ�� �Է����ּ���.");
					password = scan.next();
				}

				System.out.println("�̸��� �Է��ϼ���.");
				String name = scan.next();
				System.out.println("�ֹι�ȣ �� 6�ڸ��� �Է��ϼ���.");
				String birth = scan.next();
				
				System.out.println("< �Է� ���� Ȯ�� â  >");
				System.out.println("�̸� : "+name);
				System.out.println("���̵� : "+id);
				System.out.println("��й�ȣ : "+password);
				System.out.println("������� : "+birth);

				System.out.println("�Է��Ͻ� ������ �´ٸ� 1�� �����ּ���.");
				int r = scan.nextInt();
				if(r==1) {
				System.out.println("[�޼���] ȸ�������� ���ϵ帳�ϴ�.");
				MemberDTO member = new MemberDTO(id, password, name, birth);
				MemberDAO.instance.joinMember(member);
				count+=1;
				System.out.println(count);
				}else {
					System.out.println("[�޼���] ó������ ���ư��ϴ�.");
				}
			}
			}
			else if(sel == 2) {
				if(check==-1) {
				System.out.println("< �α���  >");
				System.out.println("���̵� �Է��ϼ���.");
				String id = scan.next();
				System.out.println("�н����带 �Է��ϼ���.");
				String pw = scan.next();

				loginCheck = MemberDAO.instance.checkLogin(id, pw);
				if(loginCheck == -1) {
					System.out.println("[�޼���] ���̵�� �н����带 Ȯ�����ּ���.");
				}else if(loginCheck == -2) {
					
					FileManager.instance.loadJang();
					FileManager.instance.loadSugang();
					
					System.out.println("< ������ â  >");
					System.out.println("���� �л� �� ");
					System.out.println(MemberDAO.instance.getLoginListSize()-1);
					System.out.println("���� �л� ���");
					MemberDAO.instance.printLoginList();
					System.out.println("��� �л��� ���� ��û �� ���� �� : "+SugangDAO.instance.getSugangListSize());
					System.out.println("��� �л��� �̸� ���� ���� �� : "+JangDAO.instance.getJangListSize());

					check = 1;
				}
				else {
					System.out.println("[�޼���] �α��� ����!");	
					check = 1;
					PSincheong.instance.campusMain();//?
				}
				}else {
					System.out.println("[�޼���] �α��� �� �Դϴ�.");
				}
			}
			else if(sel == 3) {
				if(check==-1) {
				System.out.println("���̵�, ��й�ȣ ã��");
				System.out.println("1. ���̵� ã��");
				System.out.println("2. ��й�ȣ ã��");

				int choice = scan.nextInt();

				if(choice == 1) {
					System.out.println("< ���̵� ã��  >");

					System.out.println("�̸��� �Է��ϼ���.");
					String name = scan.next();
					System.out.println("�ֹι�ȣ �� 6�ڸ��� �Է��ϼ���.");
					String birth = scan.next();

					idfind = MemberDAO.instance.findId(name , birth) ;
					if(idfind == -1) {
						System.out.println("[�޼���] ���Ե������� �����Դϴ�..");
					}else {
						System.out.println(name+"���� ���̵�� "+ MemberDAO.instance.getLoginList().get(idfind).id);	

					}
				}
				else if(choice == 2) {
					System.out.println("< ��й�ȣ ã��  >");

					System.out.println("���̵� �Է��ϼ���.");
					String id = scan.next();
					System.out.println("�̸��� �Է��ϼ���.");
					String name = scan.next();
					System.out.println("�ֹι�ȣ �� 6�ڸ��� �Է��ϼ���.");
					String birth = scan.next();

					pwfind = MemberDAO.instance.findpw(id, name , birth) ;
					if(idfind == -1) {
						System.out.println("[�޼���] ���Ե������� �����Դϴ�.");
					}else {
						System.out.println(name+"���� ��й�ȣ�� "+MemberDAO.instance.getLoginList().get(pwfind).password);	

					}
				}
				}else {
					System.out.println("[�޼���] �α��� �� �Դϴ�.");
				}
			}else if(sel == 4) {
				if(check==1) {
					System.out.println("[�޼���] �α׾ƿ� ����");
					check=-1;
				}else {
					System.out.println("[�޼���] �α��� ���� �ƴմϴ�.");
				}
			}else if(sel==5) {
				if(check==1) {
					System.out.print("Ż���� ID : ");
					String did = scan.next();
					System.out.print("pw : ");
					String dpw = scan.next();
					loginCheck = MemberDAO.instance.checkLogin(did, dpw);
					
					int index = loginCheck;
					MemberDAO.instance.removemem(index);
					FileManager.instance.saveTemp();
					System.out.println("Ż�� ����.");
					
			}else {
				System.out.println("[�޼���] �α��� ���� �ƴմϴ�.");
			}
				
			}
			else if(sel == 0) {
				System.out.println("�����մϴ�.");
				scan.close();
				run = false;
			}
		}
	}
}
