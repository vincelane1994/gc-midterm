
public class MultiClubMembers extends Members {

	private int membershipPoints;

	private final String CLUB = "all";

	public String getCLUB() {
		return CLUB;
	}

	public MultiClubMembers() {

	}

	public MultiClubMembers(int id, String name, int membershipPoints) {
		super();

		this.id = id;
		this.name = name;
		this.membershipPoints = membershipPoints;
	}

	@Override
	public String toString() {
		return "MultiClubMembers [Name: " + getName() + ", ID:" + getId() + ", Loyalty Points=" + membershipPoints + "]";
	}

	public int getMembershipPoints() {
		return membershipPoints;
	}

	public void setMembershipPoints(int membershipPoints) {
		this.membershipPoints = membershipPoints;
	}

}
