
public class Members {
	
	private int id;
	private String name;
	

	public Members(){

	}
	
	public Members(int id, String name) {
		this.id = id;
		this.name = name;
		
	}

	@Override
	public String toString() {
		return "Members [id=" + id + ", name=" + name + "]";
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

}