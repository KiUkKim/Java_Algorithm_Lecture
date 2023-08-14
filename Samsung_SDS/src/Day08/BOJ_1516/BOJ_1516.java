package Day08.BOJ_1516;

import java.util.*;
import java.io.*;

public class BOJ_1516 {

	static List<Integer>[] graph; 
	static int[] indegree;// indegree가 0인 애들을 넣을 예정이다.
	static int N;
	static int[] BuildCost, TotalCost; // Build cost와 그 건물을 짓기 위한 수행에 필요한것
	static int[] ans;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		
		BuildCost = new int[N+1];
		
		TotalCost = new int[N+1];
		
		q = new LinkedList<>();
		
		indegree = new int[N+1];
		
		for(int i = 1; i <= N; i++)
		{
			graph[i] = new ArrayList<>();
		}
		
		ans = new int[N+1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int w;
			
			w = Integer.parseInt(st.nextToken());
			
			BuildCost[i] = w;
			
			while(true)
			{
				int pre;
				
				pre = Integer.parseInt(st.nextToken());
				
				if(pre == -1)
					break;
				
				graph[pre].add(i);
				
				indegree[i]++;
			}
		}
		
		topo_sort();
		
		
		for(int i = 1; i <= N; i++)
		{
			System.out.println(ans[i]);
		}
		
	}
	
	static void topo_sort()
	{
		// indegree가 0인 것부터 넣어주기
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
			{
				ans[i] = BuildCost[i];
				q.add(i);
			}
		}
		
		while(!q.isEmpty())
		{
			int cur = q.poll();
			int preW = ans[cur];
			
			// 아직 진입차수가 0이 아닌부분을 탐색한다.
			for(int next : graph[cur])
			{
				indegree[next]--;
			
				ans[next] = Math.max(ans[next], preW + BuildCost[next]);
				
				if(indegree[next] == 0)
					q.add(next);
			}
		}
	}
	
}


class Pair
{
	int cur;
	int wei;
	
	Pair(int cur, int wei)
	{
		this.cur = cur;
		this.wei = wei;
	}
}
