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
	static int check; //로그인 상태 -1 : 아웃, 1 : 인, -2 : 관리자
	static int idfind;
	static int pwfind;
	static int checkid;
	static int count; //학생수 정원 체크 학부생: 19,608명_나무위키, 명지대학교 학생수, https://namu.wiki/w/%EB%AA%85%EC%A7%80%EB%8C%80%ED%95%99%EA%B5%90


	public PMenu() {
		loginCheck = -1;
		check = -1;
		count = 0;
		FileManager.instance.loadTemp();
		
		
		boolean run = true;
		while(run) {
			System.out.println("명지대학교  수강신청");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 아이디,비밀번호 찾기");
			System.out.println("4. 로그아웃");
			System.out.println("5. 탈퇴");
			System.out.println("0. 종료");

			int sel = scan.nextInt();

			if(sel == 1) {
				if(count>19609) {
					System.out.println("[메세지] 회원 가입 가능 인원수가 초과되었습니다.");
				}else {
				System.out.println("< 회원가입  >");
				System.out.println("아이디를 입력하세요.");
				String id = scan.next();
				checkid= MemberDAO.instance.idcheck(id);
				if(checkid==1) {
					System.out.println("중복된 아이디 입니다. 다시 입력해주세요.");
					id = scan.next();
				}
				System.out.println("패스워드를 입력하세요.");
				String password = scan.next();

				if(password.length()>8){ //비밀번호 자리수 제한
					System.out.println("[메세지] 8자리 이하 비밀번호를 입력해주세요.");
					password = scan.next();
				}else if(password.length()<2){
					System.out.println("[메세지] 2자리 이상 비밀번호를 입력해주세요.");
					password = scan.next();
				}

				System.out.println("이름을 입력하세요.");
				String name = scan.next();
				System.out.println("주민번호 앞 6자리를 입력하세요.");
				String birth = scan.next();
				
				System.out.println("< 입력 정보 확인 창  >");
				System.out.println("이름 : "+name);
				System.out.println("아이디 : "+id);
				System.out.println("비밀번호 : "+password);
				System.out.println("생년월일 : "+birth);

				System.out.println("입력하신 정보가 맞다면 1을 눌러주세요.");
				int r = scan.nextInt();
				if(r==1) {
				System.out.println("[메세지] 회원가입을 축하드립니다.");
				MemberDTO member = new MemberDTO(id, password, name, birth);
				MemberDAO.instance.joinMember(member);
				count+=1;
				System.out.println(count);
				}else {
					System.out.println("[메세지] 처음으로 돌아갑니다.");
				}
			}
			}
			else if(sel == 2) {
				if(check==-1) {
				System.out.println("< 로그인  >");
				System.out.println("아이디를 입력하세요.");
				String id = scan.next();
				System.out.println("패스워드를 입력하세요.");
				String pw = scan.next();

				loginCheck = MemberDAO.instance.checkLogin(id, pw);
				if(loginCheck == -1) {
					System.out.println("[메세지] 아이디와 패스워드를 확인해주세요.");
				}else if(loginCheck == -2) {
					
					FileManager.instance.loadJang();
					FileManager.instance.loadSugang();
					
					System.out.println("< 관리자 창  >");
					System.out.println("가입 학생 수 ");
					System.out.println(MemberDAO.instance.getLoginListSize()-1);
					System.out.println("가입 학생 목록");
					MemberDAO.instance.printLoginList();
					System.out.println("모든 학생이 수강 신청 한 강좌 수 : "+SugangDAO.instance.getSugangListSize());
					System.out.println("모든 학생이 미리 담은 강좌 수 : "+JangDAO.instance.getJangListSize());

					check = 1;
				}
				else {
					System.out.println("[메세지] 로그인 성공!");	
					check = 1;
					PSincheong.instance.campusMain();//?
				}
				}else {
					System.out.println("[메세지] 로그인 중 입니다.");
				}
			}
			else if(sel == 3) {
				if(check==-1) {
				System.out.println("아이디, 비밀번호 찾기");
				System.out.println("1. 아이디 찾기");
				System.out.println("2. 비밀번호 찾기");

				int choice = scan.nextInt();

				if(choice == 1) {
					System.out.println("< 아이디 찾기  >");

					System.out.println("이름을 입력하세요.");
					String name = scan.next();
					System.out.println("주민번호 앞 6자리를 입력하세요.");
					String birth = scan.next();

					idfind = MemberDAO.instance.findId(name , birth) ;
					if(idfind == -1) {
						System.out.println("[메세지] 가입되지않은 정보입니다..");
					}else {
						System.out.println(name+"님의 아이디는 "+ MemberDAO.instance.getLoginList().get(idfind).id);	

					}
				}
				else if(choice == 2) {
					System.out.println("< 비밀번호 찾기  >");

					System.out.println("아이디를 입력하세요.");
					String id = scan.next();
					System.out.println("이름을 입력하세요.");
					String name = scan.next();
					System.out.println("주민번호 앞 6자리를 입력하세요.");
					String birth = scan.next();

					pwfind = MemberDAO.instance.findpw(id, name , birth) ;
					if(idfind == -1) {
						System.out.println("[메세지] 가입되지않은 정보입니다.");
					}else {
						System.out.println(name+"님의 비밀번호는 "+MemberDAO.instance.getLoginList().get(pwfind).password);	

					}
				}
				}else {
					System.out.println("[메세지] 로그인 중 입니다.");
				}
			}else if(sel == 4) {
				if(check==1) {
					System.out.println("[메세지] 로그아웃 성공");
					check=-1;
				}else {
					System.out.println("[메세지] 로그인 중이 아닙니다.");
				}
			}else if(sel==5) {
				if(check==1) {
					System.out.print("탈퇴할 ID : ");
					String did = scan.next();
					System.out.print("pw : ");
					String dpw = scan.next();
					loginCheck = MemberDAO.instance.checkLogin(did, dpw);
					
					int index = loginCheck;
					MemberDAO.instance.removemem(index);
					FileManager.instance.saveTemp();
					System.out.println("탈퇴 성공.");
					
			}else {
				System.out.println("[메세지] 로그인 중이 아닙니다.");
			}
				
			}
			else if(sel == 0) {
				System.out.println("감사합니다.");
				scan.close();
				run = false;
			}
		}
	}
}
