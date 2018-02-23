package waheeda.helloworld.emp;

public class Employee {
	private Long id;
	private String name;
	private byte[] file;
	
	public Employee(long id, String name, byte[] file) {
		super();
		this.id = id;
		this.name = name;
		this.file = file;
	}

	public Employee(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	

}
