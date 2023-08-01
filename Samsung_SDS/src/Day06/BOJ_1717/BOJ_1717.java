package Day06.BOJ_1717;

import java.util.*;
import java.io.*;

public class BOJ_1717 {

	static int[] parent;
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		
		parent = new int[N+1];
		
		//초기에 자신의 부모로 놔두기
		Arrays.fill(parent, -1);
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd = Integer.parseInt(st.nextToken());
			
			int a = Integer.parseInt(st.nextToken());
			
			int b = Integer.parseInt(st.nextToken());
			
			
			// cmd가 0일 때는 union
			if(cmd == 0)
			{
				union(a, b);
			}
			
			// cmd가 1일 때는 find
			else {
				int a_parent = find(a);
				int b_parent = find(b);
				
				if(a_parent == b_parent)
				{
					sb.append("YES\n");
				}
				else {
					sb.append("NO\n");
				}
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void union(int a, int b)
	{
		int a_parent = find(a);
		
		int b_parent = find(b);
		
		if(a_parent != b_parent)
		{
			parent[b_parent] = a_parent;
		}
	}
	
	static int find(int x)
	{
		if(parent[x] < 0)
			return x;
		
		return parent[x] = find(parent[x]);
	}

}
