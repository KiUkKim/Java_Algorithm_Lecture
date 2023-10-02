package BOJ_2623;

import java.util.*;
import java.io.*;

public class BOJ_2623 {

	static int N, M;
	static int[] indegree;
	static List<Integer>[] adj;
	
	static List<Integer> ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		ans = new ArrayList<>();
		
		adj = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++)
		{
			adj[i] = new ArrayList<>();
		}
		
		indegree = new int[N + 1];
		
		for(int i = 1; i <= M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int idx = Integer.parseInt(st.nextToken());
			
			int prev = 0;
			
			for(int j = 0; j < idx; j++)
			{
				int num = Integer.parseInt(st.nextToken());
				
				if(prev == 0)
				{
					prev = num;
					continue;
				}
				
				else {
					adj[prev].add(num);
					
					indegree[num]++;
					
					prev = num;
				}
			}
			
		}
		
		topoSort();
		
		if(ans.size() != N)
		{
			bw.write(0 + "\n");
		}
		
		else {
			for(int x : ans)
			{
				bw.write(x + "\n");
			}	
		}
		
		bw.flush();
		bw.close();
	}
	
	static void topoSort()
	{
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
			{
				q.add(i);
			}
		}
		
		while(!q.isEmpty())
		{
			int cur = q.poll();
			
			ans.add(cur);
			
			for(int next : adj[cur])
			{
				indegree[next]--;
				
				if(indegree[next] == 0)
				{
					q.add(next);
				}
			}
		}
		

	}

}
