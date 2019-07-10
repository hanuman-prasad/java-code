package edu.elearning.list;

import java.util.List;
import java.util.ArrayList;

public class ArrayListExample {
	int a;
	private int b;
	protected int c;
	public int d;
	final int e =1;
	private final int f =2;
	static int g;
	private static int h;
	private static final int i=6;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList(3);
		
		list.add(Integer.valueOf(1));
		list.add(null);
		list.add(null);
		list.add(Integer.valueOf(2));
		list.add(Integer.valueOf(2));

		ArrayList<Integer> tempList = new ArrayList<>();
		tempList.add(Integer.valueOf(11));
		tempList.add(Integer.valueOf(12));
		tempList.add(Integer.valueOf(13));


		list.addAll(2,tempList);

		System.out.println(list.size());
    }
}
