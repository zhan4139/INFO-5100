public class Assignment4 {
	public static void main(String[] args) {
		//1.
		System.out.println("P1.----------------------------");
		String s1 = "2-4A0r7-4k";
		String res = formatLicenseKey(s1, 3);
		System.out.println(res);

		//2.
		System.out.println("P2.----------------------------");
		Scissors s = new Scissors(5);
		Paper p = new Paper(7);
		Rock r = new Rock(15);

		System.out.println(s.fight(p) + " , " + p.fight(s));
		System.out.println(p.fight(r) + " , " + r.fight(p));
		System.out.println(r.fight(s) + " , " + s.fight(r));

		//3.
		System.out.println("P3.----------------------------");
		IpAddress ip = new IpAddress("216.27.6.136");
		System.out.println(ip.getDottedDecimal());
		System.out.println(ip.getOctet(4));
		System.out.println(ip.getOctet(1));
		System.out.println(ip.getOctet(3));
		System.out.println(ip.getOctet(2));

		//4.
		System.out.println("P4.----------------------------");
		Course c = new Course("INFO 5100");
		for (int i = 0; i < 10; i++) {
			Student stu = new Student("s", String.valueOf(i));
			c.registerStudent(stu);
		}

		System.out.println(c.getTitle());
		System.out.println(c.getNumberOfStudent());
		System.out.print("Student's ID are: " );
		for (int i = 0; i < 10; i++)
			System.out.print(c.getStudents()[i].getId() + " ");
		System.out.println();		
		c.registerStudent(new Student("s10", "10")); //can't register one more student

		//5.
		System.out.println("P5.----------------------------");
		int p5 = 2560;
		System.out.println(intToRoman(p5));

		//Extra Point
		System.out.println("ExtraCredit.-------------------");
		int[] nums1 = {1, 2, 5, 6}, nums2 = {4};
		double median = findMedianSortedArrays(nums1, nums2);
		System.out.println(median);

	}

	//1. software license key modification
	public static String formatLicenseKey(String s, int k) {
		//use StringBuilder is faster than do string addition,etc
		if (s.length() == 0 || s == null) return "";
		String res = "";
		int count = 0;
		for (int i = 0;i < s.length(); i++) {
			if (s.charAt(i) != '-') {
				count ++;
				res += Character.toUpperCase(s.charAt(i));
			}
		} 

		if (count <= k) return "";

		for (int i = count - k - 1; i >=0 ; i-=k) {
			if (i >= 0) {
				res = res.substring(0, i + 1) + "-" + res.substring(i + 1);
			}
		}
		return res;
	}

	//5. 
	public static String intToRoman(int num) {
		String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
	    int[] value = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
	    int base = -1;
	    StringBuilder result = new StringBuilder();

	    for(int i = 0; i < romans.length; i++){
	        if ((num / value[i]) != 0) {
				base = num / value[i];
	            while (base-- != 0) 
	            	result.append(romans[i]);
	            num %= value[i];
	        }
	    }
	    return result.toString();
	}

	//Extra Credit
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		//merge two sorted array, we have to allocate a new memory
		//T = O(a + b), S = O(a + b)
		//Could be solved in T = O(lgn) with S = O(1)
		if (nums1 == null && nums2 == null 
			|| nums1.length == 0 && nums2.length == 0) 
			return Double.MIN_VALUE;

		int a = nums1.length, b = nums2.length;
		int[] total = new int[a + b];

		int first = 0, second = 0, pos = 0;
		while (first < a && second < b) {
			if (nums1[first] <= nums2[second]) {
				total[pos] = nums1[first];
				first++;
			} else if (nums1[first] > nums2[second]) {
				total[pos] = nums2[second];
				second++;
			}
			pos++;
		}
		//check pos is median pos of array could save some time but still O(n)
		if (first >= a)
			for (; second < b; second++) {
				total[pos++] = nums2[second];
			}

		if (second >= b)
			for (; first < a; first++) {
				total[pos++] = nums1[first];
			}
		
		if (total.length % 2 == 0) 
			return (double) (total[total.length/2] + total[(total.length - 1)/2]) / 2;
		return total[total.length/2];
	}

	//Extra Cresit 2 O(lgn)
	public double findMedianSortedArrays2(int[] A, int[] B) {
	    int m = A.length, n = B.length;
	    int l = (m + n + 1) / 2;
	    int r = (m + n + 2) / 2;
	    return (getkth(A, 0, B, 0, l) + getkth(A, 0, B, 0, r)) / 2.0;
	}

	public double getkth(int[] A, int aStart, int[] B, int bStart, int k) {
		if (aStart > A.length - 1) return B[bStart + k - 1];            
		if (bStart > B.length - 1) return A[aStart + k - 1];                
		if (k == 1) return Math.min(A[aStart], B[bStart]);
		
		int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
		if (aStart + k/2 - 1 < A.length) aMid = A[aStart + k/2 - 1]; 
		if (bStart + k/2 - 1 < B.length) bMid = B[bStart + k/2 - 1];        
		
		if (aMid < bMid) 
		    return getkth(A, aStart + k/2, B, bStart,       k - k/2);// Check: aRight + bLeft 
		else 
		    return getkth(A, aStart,       B, bStart + k/2, k - k/2);// Check: bRight + aLeft
	}
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
	private String dottedDecimal;

	public IpAddress(String s) {
		dottedDecimal = s;
	}

	public String getDottedDecimal() {
		return this.dottedDecimal;
	}

	public int getOctet(int pos) {
		String ip = this.dottedDecimal;
		String res = "";
		for (int i = 0; i <= pos - 1; i++) {
			if (i == 3) res = ip.substring(0);
			else {
				int index = ip.indexOf(".");
				res = ip.substring(0, index);
				ip = ip.substring(index + 1);
			}
		}
		return Integer.parseInt(res);
	}
}

//4. 
class Student {
	private String name;
	private String id;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

class Course {
	private static final int MAX_OF_STUDENT = 10;
	private String title;
	private Student[] students;
	private int numberOfStudent;

	public Course(String title) {
		this.title = title;
		numberOfStudent = 0;
	}

	public String getTitle() {
		return title;
	}

	public Student[] getStudents() {
		return students;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public boolean isFull() {
		return numberOfStudent >= MAX_OF_STUDENT;
	}

	public void registerStudent(Student s) {
		if (!isFull()) {
			if (numberOfStudent == 0) {
				students = new Student[]{s};
				numberOfStudent++;
			} else {
				numberOfStudent++;
				Student[] ns = new Student[numberOfStudent];
				for (int i = 0; i < students.length; i++) {
					ns[i] = students[i];
				}
				ns[numberOfStudent - 1] = s;
				students = ns;
			}
		} else {
			System.out.println("Course is full, you can not register!");
		}
	}
}

