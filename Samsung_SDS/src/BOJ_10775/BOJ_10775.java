package BOJ_10775;

import java.util.*;
import java.io.*;

public class BOJ_10775 {

	static int G, P;
	static int[] parent;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		G = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		
		parent = new int[G + 1];
		
		Arrays.fill(parent, -1);
		
		for(int i = 1; i <= P; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			int emptyGate = find(num);
			
			if(emptyGate == 0)
				break;
			
			cnt++;
			
			union(emptyGate - 1, emptyGate);
			
//			for(int x : parent)
//			{
//				System.out.print(x + " ");
//			}
//			System.out.println();
		}
		
//		for(int x : parent)
//		{
//			System.out.print(x + " ");
//		}
//		System.out.println();
		
		bw.write(cnt + " ");
		bw.flush();
		bw.close();
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		if(a != b)
		{
			parent[b] = a;
		}
	}
	
	
	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}

}
