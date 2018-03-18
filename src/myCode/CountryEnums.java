package myCode;

public enum CountryEnums {

	one(1,"韩"),
	two(2,"赵"),
	three(3,"魏"),
	four(4,"楚"),
	five(5,"燕"),
	six(6,"齐");
	
	private Integer code;
	private String country;
	
	private CountryEnums(Integer code, String country) {
		this.code = code;
		this.country = country;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	//最主要的是遍历
	public static CountryEnums  ForeachCountryEnums(Integer i){
		
		for (CountryEnums element : values()) {
			if(element.getCode() == i) {
				return element;
			}
		}
		
		return null;
	}
	
	
}
