package Day03_BOJ_1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, K;
	static Jewel[] jewels;
	static int[] bags;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\DAY03_BOJ_1202\\input.txt"));

				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		StringTokenizer st = new StringTokenizer(br.readLine());
				
		N = Integer.parseInt(st.nextToken());
				
		K = Integer.parseInt(st.nextToken());
		
		jewels = new Jewel[N];
		bags = new int[K];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			jewels[i] = new Jewel(w, v);
		}
		
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			
			bags[i] = w;
		}
		
		// 가방 오름차순 정렬
		Arrays.sort(bags);
		
		// 보석 무게순 정렬
		Arrays.sort(jewels, Comparator.comparingInt(Jewel::getWeight));
		
		// 보석 높은 값 기준 힙
		PriorityQueue<Jewel> pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getValue).reversed());
		
		long result = 0;
		int jIndex = 0;
		// 1. 남은 가방 중 제일 작은 가방을 선택.
		
		for(int i = 0; i < bags.length; i++)
		{
			while(jIndex < N && jewels[jIndex].weight <= bags[i])
			{
				pq.add(jewels[jIndex++]);
			}
			
			if(!pq.isEmpty())
			{
				result += pq.poll().value;
			}
		}
		
		System.out.println(result);
	}

}

class Jewel{
	int weight;
	int value;
	
	public Jewel(int weight, int value)
	{
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
	
}