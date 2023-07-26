package DAY02.Sort;

import java.util.*;
import java.io.*;

public class SortTest {

	public static void main(String[] args) {

//		Integer[] nums = {4, 3, 2, 1, 5};
//		
//		System.out.println(Arrays.toString(nums));
//		
//		Arrays.sort(nums);
//		
//		System.out.println(Arrays.toString(nums));
//		
//		Arrays.sort(nums, new Comparator<Integer>()
//		{
//			@Override
//			public int compare(Integer o1, Integer o2)
//			{
//				return o2 - o1;
//			}
//		});
		
		Item[] items = new Item[5];
		
		items[0] = new Item(1, 5);
		items[1] = new Item(2, 2);
		items[2] = new Item(3, 3);
		items[3] = new Item(1, 5);
		items[4] = new Item(1, 5);
		
//		Arrays.sort(null); // 에러 -> comparable하지 못함 왜냐? 값이 2가지 이상이다.
		
		Arrays.sort(items, (o1, o2) -> {
			int result1 = o1.a - o2.a;
			
			if(result1 == 0)
			{
				return o1.b - o2.b;
			}
			
			else {
				return result1;
			}
		});
		
		System.out.println(Arrays.toString(items));
	}
}

class Item implements Comparable<Item>{
	int a;
	int b;
	
	
	public Item(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	
	@Override
	public String toString() {
		return "Item [a=" + a + ", b=" + b + "]";
	}


	@Override
	public int compareTo(Item o2) {
		return b - o2.b;
	}	
}