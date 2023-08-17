package Day05.BOJ_15664;

import java.util.*;
import java.io.*;

public class BOJ_15664 {

	static int N, M;
	
	static int[] arr;
	static StringBuilder sb;
	static List<Integer> list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		visited = new boolean[N];
		
		list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(0);
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int idx)
	{
		int prev = 0;
		
		if(list.size() == M)
		{
			for(int a : list)
			{
				sb.append(a + " ");
			}
			
			sb.append("\n");
			
			return;
		}
		
		for(int i = idx; i < arr.length; i++)
		{
			
			if(!visited[i] && prev != arr[i])
			{
				visited[i] = true;
				list.add(arr[i]);
				prev = arr[i];
				dfs(i);
				list.remove(list.size() - 1);
				visited[i] = false;
			}	
		}
	}

}
