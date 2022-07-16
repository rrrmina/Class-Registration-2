package studentmem;

public class MemberDTO {
	public String id;
	public String password;
	public String name;
	public String birth;

	public MemberDTO() {}
	
	public MemberDTO(String id, String password, String name, String birth) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.birth = birth;
	}
}
