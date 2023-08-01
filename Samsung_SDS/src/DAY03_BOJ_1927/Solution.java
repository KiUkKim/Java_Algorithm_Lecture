package DAY03_BOJ_1927;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.*;
import java.io.*;

public class Solution {

	static int N;
	static int idx;
	static MinHeap mh;
	
	public static void main(String[] args) throws IOException{

		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\DAY03_BOJ_1927\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		MinHeap mh = new MinHeap();
		
		for(int i = 0; i < N; i++)
		{
			st= new StringTokenizer(br.readLine());
			
			int input = Integer.parseInt(st.nextToken());
			
			if(input == 0)
			{
				System.out.println(mh.delete());
			}
			
			else {
				mh.insert(input);
			}
		}
	}

}

class MinHeap{
	List<Integer> list;
	
	public MinHeap()
	{
		list = new ArrayList<>();
		list.add(0);
	}
	
	public void insert(int val)
	{
		// 1. Ʈ���� �������� ���� ����
		list.add(val);
		
		// 2. �θ�� ���� ����� ������ �����ϴ��� Ȯ��
		int current = list.size() - 1;
		int parent = current / 2;
		
		while(true)
		{
			// 3. �����ϸ� stop , root�� �����߰ų�
			if(parent == 0 || list.get(parent) <= list.get(current))
				break;
			
			// 3-1. �������� ������ ��ü�� 2������	
			int temp = list.get(parent);
			list.set(parent, list.get(current));
			list.set(current, temp);
			
			current = parent;
			
			parent = current / 2;
		}
	}
	
	public int delete()
	{
		
		if(list.size() == 1)
			return 0;
		
		int top = list.get(1);
		
		list.set(1, list.get(list.size() - 1));
		
		list.remove(list.size() - 1);
		
		int curPos = 1;
		
		while(true)
		{
			int leftPos = (curPos * 2);
			int rightPos = (curPos *  2) + 1;
			
			// 1. Ż�� ( Leaf�� �����߰ų�)
			if(leftPos >= list.size())
				break;
			
			// 2. �ڽ� �� ������ ������Ű�� ���� �ڽ��� ����
			int minValue = list.get(leftPos);
			
			int minPos = leftPos;
			
			if(rightPos < list.size() && minValue > list.get(rightPos))
			{
				minValue = list.get(rightPos);
				
				minPos = rightPos;
			}
			

			// ������ �����ϸ�
			// 3. ����
			if(list.get(curPos) > minValue)
			{
				int temp = list.get(curPos);
				
				list.set(curPos, list.get(minPos));
				list.set(minPos, temp);
				
				curPos = minPos;
			}
			else {
				break;
			}
		}
		
		return top;
	}
}
