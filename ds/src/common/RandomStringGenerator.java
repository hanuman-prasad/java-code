package common;


import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
public class RandomStringGenerator {

	private static final int MIN_ASCII = 97;
	private static final int MAX_ASCII = 122;

	private  final int STRING_MINIMUM_LENGTH;
	private  final int STRING_MAXIMUM_LENGTH;
	
	public static void main(String[] ss){
		RandomStringGenerator rs = new RandomStringGenerator(2,5);
		
		System.out.println(Arrays.toString(rs.getArrayOfRandomString(20)));
	}

	public RandomStringGenerator(){
		STRING_MINIMUM_LENGTH = 3;
		STRING_MAXIMUM_LENGTH = 3;
	}

	public RandomStringGenerator(int string_minimum_length, int string_maximum_length) {
		STRING_MINIMUM_LENGTH = string_minimum_length;
		STRING_MAXIMUM_LENGTH = string_maximum_length;
	}


	public String[] getArrayOfRandomString(int size){
		String[] strings = new String[size];
		
		for(int i =0 ;i<size;i++){
		
		int strLen = ThreadLocalRandom.current().nextInt(STRING_MINIMUM_LENGTH, STRING_MAXIMUM_LENGTH+1);
		String tempStr = "";
		for(int j=0;j<strLen;j++){
		int randomNum = ThreadLocalRandom.current().nextInt(MIN_ASCII, MAX_ASCII + 1);
		char randomChar = (char) randomNum;
		tempStr+=randomChar;
		}
		strings[i] = tempStr;
		}
		return strings;
	}

}