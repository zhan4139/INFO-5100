public class Assignment4 {
	public static void main(String[] args) {
		//2.
		Scissors s = new Scissors(5);
		Paper p = new Paper(7);
		Rock r = new Rock(15);

		//System.out.println(r.fight(p));
		System.out.println(s.fight(p) + " , " + p.fight(s));
		System.out.println(p.fight(r) + " , " + r.fight(p));
		System.out.println(r.fight(s) + " , " + s.fight(r));
	}

	//1. software license key modification
	public static String formatLicenseKey(String s, int k) {
		//StringBuilder sb = new StringBuilder();
		String res = "";
		int count = 0;
		for (int i = 0;i < s.length(); i++) {
			if (s.charAt(i) != '-') {
				count ++;
				res += Character.toUpperCase(s.charAt(i));
			}
		} 

		if (count <= k) return null;

		int hit = 0;
		for (int i = res.length() - 1; i >=0; i--) {
			if (hit != k) {
				//sb.append(Character.toUpperCase(s.charAt(i)));
				hit ++;
			} 
		}
		return res;
	}

	//4. 


	//5. 
	public String intToRoman(int num) {

	}

	//Extra Credit
}

//2.
class Tool {
	private int strength;
	private char type;

	public Tool(int s) {
		strength = s;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setType(char type) {
		this.type = type;
	}

	public int getStrength() {
		return this.strength;
	}

	public char getType() {
		return this.type;
	}

}

class Rock extends Tool {
	public Rock(int s) {
		super(s);
		this.setType('r');
	}

	public boolean fight(Tool t) {
		if (t.getType() == 's') {
			return this.getStrength() * 2 > t.getStrength();
		} else if (t.getType() == 'p') {
			return this.getStrength() > t.getStrength() * 2;
		} else {
			return this.getStrength() > t.getStrength();
		}
	}
}

class Paper extends Tool {
	public Paper(int s) {
		super(s);
		this.setType('p');
	}

	public boolean fight(Tool t) {
		if (t.getType() == 'r') {
			return this.getStrength() * 2 > t.getStrength();
		} else if (t.getType() == 's') {
			return this.getStrength() > t.getStrength() * 2;
		} else {
			return this.getStrength() > t.getStrength();
		}
	}
}

class Scissors extends Tool {
	public Scissors(int s) {
		super(s);
		this.setType('s');
	}

	public boolean fight(Tool t) {
		if (t.getType() == 'p') {
			return this.getStrength() * 2 > t.getStrength();
		} else if (t.getType() == 'r') {
			return this.getStrength() > t.getStrength() * 2;
		} else {
			return this.getStrength() > t.getStrength();
		}
	}
}

//3. 
class IpAddress {

}



