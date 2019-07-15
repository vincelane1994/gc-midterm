
public class Club {
	private String name;
	private String adress;
	
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Club() {}
	
	public Club(String name, String address) {
		this.name = name;
		this.adress = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
