
public class MultiClubMembers extends Members {

	private int membershipPoints;

	public MultiClubMembers() {

	}

	public MultiClubMembers(int id, String name, int membershipPoints) {
		super();
		this.membershipPoints = membershipPoints;
	}

	@Override
	public String toString() {
		return "MultiClubMembers [membershipPoints=" + membershipPoints + ", getId()=" + getId() + ", getName()="
				+ getName() + "]";
	}

}
