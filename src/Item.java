

public class Item implements Comparable<Item> {

	private String description;
	private int value;
	private int weight;
	
	
	
	public Item() {
	
	}


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}



	public void setWeight(int weight) {
		this.weight = weight;
	}


	@Override
	public int compareTo(Item arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


}
