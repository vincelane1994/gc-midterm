
public class SingleClubMember extends Members {
	private String club;



	public SingleClubMember() {

	}

	public SingleClubMember(int id, String name, String club) {
		super();
		this.name = name;
		this.id = id;
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
		return "SingleClubMember [name=" + name + ", id=" + id + ", club=" + club + "]";
	}
}
