
public class LinkedNumber {
	
	private int base;
	private DLNode<Digit> front;
	private DLNode<Digit> rear;
	
	//constructor for linkednumber class
	public LinkedNumber(String num, int baseNum) throws LinkedNumberException {
		base = baseNum;
		if (num == "") {
			throw new LinkedNumberException("no digits given");
		}
		char[] numArray = num.toCharArray();
		front = null;
		rear = null;
		
		//iterates through array of values, appends them to linked list
		for (int i = 0; i < numArray.length; i++) {
			DLNode<Digit> digit = new DLNode(new Digit(numArray[i]));
			if (front == null) {
				front = digit;
				rear = digit;
			}
			else {
				rear.setNext(digit);
				digit.setPrev(rear);
				rear = digit;
			}
		}
		
	}
	
	//Overload constructor, if base is not provided we assume 10
	public LinkedNumber(int num) throws LinkedNumberException {
		base = 10;
		String numStr = String.valueOf(num);
		if (numStr == null) {
			throw new LinkedNumberException("no digits given");
		}
		char[] numArray = numStr.toCharArray();
		front = null;
		rear = null;
		
		
		for (int i = 0; i < numArray.length; i++) {
			DLNode<Digit> digit = new DLNode(new Digit(numArray[i]));
			if (front == null) {
				front = digit;
				rear = digit;
			}
			else {
				rear.setNext(digit);
				digit.setPrev(rear);
				rear = digit;
			}
		}
	}
	
	//checks if number is valid by seeing if each number is less than the base
	public boolean isValidNumber() {
		DLNode<Digit> curNode = front;
		while (curNode != null) {
			int val = curNode.getElement().getValue();
			if (val >= 0 && val < base) {
				curNode = curNode.getNext();
			}
			else return false;
		}
		return true;
	}
	
	
	//returns base
	public int getBase() {
		return base;
	}
	
	
	//returns front
	public DLNode<Digit> getFront() {
		return front;
	}
	
	//returns rear
	public DLNode<Digit> getRear() {
		return rear;
	}
	
	//returns number of digits in linked list
	public int getNumDigits() {
		DLNode<Digit> curNode = front;
		int count = 0;
		while (curNode != null) {
			curNode = curNode.getNext();
			count++;
		}
		return count;
	}
	
	//Override toString method to convert linked list to string
	@Override
	public String toString() {
		String strBuilder = "";
		DLNode<Digit> curNode = front;
		while (curNode != null) {
			int val = curNode.getElement().getValue();
			String strVal = String.valueOf(val);
			if (val == 10) {
				strVal = "A";
			}
			else if (val == 11) {
				strVal = "B";
			}
			else if (val == 12) {
				strVal = "C";
			}
			else if (val == 13) {
				strVal = "D";
			}
			else if (val == 14) {
				strVal = "E";
			}
			else if (val == 15) {
				strVal = "F";
			}
			strBuilder += strVal;
			curNode = curNode.getNext();
		}
		return strBuilder; 
		
	}
	
	//checks if 2 lists are identical (base, # vals)
	public boolean equals(LinkedNumber other) {
		if (this.base != other.base) return false;
		if (this.getNumDigits() != other.getNumDigits()) return false;
		DLNode<Digit> thisCurNode = this.front;
		DLNode<Digit> otherCurNode = other.front;
		while (thisCurNode != null && otherCurNode != null) {
			if (!thisCurNode.getElement().equals(otherCurNode.getElement())) return false;
			thisCurNode = thisCurNode.getNext();
			otherCurNode = otherCurNode.getNext();
		}
		if (thisCurNode != null || otherCurNode != null) return false;
		return true;
	}
	
	//private helper method to convert decimal to non decimal
	private LinkedNumber decToNonDec(int newBase) {
		DLNode<Digit> curNode = rear;
		int val = 0;
		int index = 0;
		String stringBuild = "";
		//Set variable val equal to the whole decimal number
		while (curNode != null) {
			int nodeVal = curNode.getElement().getValue();
			int multiplier = (int) Math.pow(10, index);
			val += nodeVal * multiplier;
			index++;
			curNode = curNode.getPrev();
		}
		//Convert to newBase using divisions and remainders to determine the new number
		while (val > 0) {
			int remainder = val % newBase;
			val = val / newBase;
			String strRemainder = String.valueOf(remainder);
			if (strRemainder.equals("10")) strRemainder = "A";
			else if (strRemainder.equals("11")) strRemainder = "B";
			else if (strRemainder.equals("12")) strRemainder = "C";
			else if (strRemainder.equals("13")) strRemainder = "D";
			else if (strRemainder.equals("14")) strRemainder = "E";
			else if (strRemainder.equals("15")) strRemainder = "F";
			stringBuild = strRemainder + stringBuild;
		}
		if (stringBuild.isEmpty()) {
	        stringBuild = "0";
	    }
		//Initialize a new LinkedNumber object with this completed String and the newBase as its parameters
		return new LinkedNumber(stringBuild, newBase);
	}
	
	//private helper method to convert non decimal to decimal
	private LinkedNumber nonDecToDec() {
		DLNode<Digit> curNode = rear;
		int val = 0;
		int index = 0;
		//Set variable val equal to the whole number
		while (curNode != null) {
			int nodeVal = curNode.getElement().getValue();
			int multiplier = (int) Math.pow(base, index);
			val += nodeVal * multiplier;
			index++;
			curNode = curNode.getPrev();
		}
		String strVal = String.valueOf(val);
		//Initialize a new LinkedNumber object with this variable val and the number 10 as its parameters
		return new LinkedNumber(strVal, 10);
	}
	
	//private helper method to convert non decimal to non decimal
	private LinkedNumber nonDecToNonDec(int newBase) {
		//Use the steps in Case 2 above to convert the current non-decimal number into a decimal LinkedNumber object called temp
		LinkedNumber temp = nonDecToDec();
		//Use the steps in Case 1 above to convert temp into a new LinkedNumber object in the newBase number system
		temp = temp.decToNonDec(newBase);
		return temp;
	}
	
	
	
	
	//converts number in linked list to new number system given desired base
	public LinkedNumber convert(int newBase) {
		if (!this.isValidNumber()) {
			throw new LinkedNumberException("cannot convert invalid number");
		}
		
		LinkedNumber result;
		//if it is decimal value, convert to non decimal with new base
		if (base == 10) {
			result = this.decToNonDec(newBase);
		}
		else {
			if (newBase == 10) {
				result = this.nonDecToDec();
			}
			else {
				result = this.nonDecToNonDec(newBase);
			}
		}
		return result;
	}
	//adds digit to LinkedNumbers List
	public void addDigit(Digit digit, int position) {
		if (position < 0 || position > this.getNumDigits()) {
			throw new LinkedNumberException("invalid position");
		}
		DLNode<Digit> newNode = new DLNode<>(digit);
		//add to end of list
		if (position == 0) {
			if (rear == null) {
				front = newNode;
				rear = newNode;
			}
			else {
				rear.setNext(newNode);
				newNode.setPrev(rear);
				newNode.setNext(null);
				rear = newNode;
			}
		}
		//add to head of list
		else if (position == this.getNumDigits()) {
			if (front == null) {
				front = newNode;
				rear = newNode;
			}
			else {
				front.setPrev(newNode);
				newNode.setNext(front);
				newNode.setPrev(null);
				front = newNode;
			}
		}
		//add within list
		else {
			DLNode<Digit> curNode = rear;
			int count = 0;
			while (count < position) {
				curNode = curNode.getPrev();
				count++;
			}
			DLNode<Digit> sucNode = curNode.getNext();
			newNode.setNext(sucNode);
			newNode.setPrev(curNode);
			curNode.setNext(newNode);
			sucNode.setPrev(newNode);
			
		}
		
		
		
	}
	//removes digit from list and returns its decimal value in terms of its contribution to the entire number
	public int removeDigit(int position) {
		if (position < 0 || position >= this.getNumDigits()) {
			throw new LinkedNumberException("invalid position");
		}
		
		DLNode<Digit> curNode = rear;
		int count = 0;
		while (count < position) {
			curNode = curNode.getPrev();
			count++;
		}
		
	    int val = curNode.getElement().getValue();

	    //calculate decimal value of digit by multiplying by base to exponent of position
	    int basePow = 1;
	    for (int i = 0; i < position; i++) {
	        basePow *= base;
	    }
	    val *= basePow;
		
	    //remove value from linked list
		DLNode<Digit> sucNode = curNode.getNext();
		DLNode<Digit> predNode = curNode.getPrev();
		
		if (sucNode != null) {
			sucNode.setPrev(predNode);
		}
		
		if (predNode != null) {
			predNode.setNext(sucNode);
		}
		
		if (predNode == null) {
			front = sucNode;
		}
		
		if (sucNode == null) {
			rear = predNode;
		}
		
		return val;
	}

}

