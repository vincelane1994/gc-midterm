
public class SingleClubMember extends Members {
	private String club;
	private int id;
	private String name;


	public SingleClubMember() {

	}

	public SingleClubMember(int id, String name, String club) {
		super();
		this.club = club;
	}

	public String getClub() {
		return club;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SingleClubMember [club=" + club + ", id=" + id + ", name=" + name + "]";
	}
}
