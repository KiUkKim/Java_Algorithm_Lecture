package BOJ_1766;

import java.util.*;
import java.io.*;

public class BOJ_1766 {

	static int N, M;
	static List<Integer> ans;
	static List<Integer>[] list;
	static int[] indegree;	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N + 1];
		
		ans = new ArrayList<>();
		
		list = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			
			int second = Integer.parseInt(st.nextToken());
			
			list[first].add(second);
			
			indegree[second]++;
		}
		
		topoSort();
		
		for(int x : ans)
		{
			bw.write(x + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void topoSort() throws IOException
	{
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
		
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
				pq.add(i);
		}
		
		while(!pq.isEmpty())
		{
			int cur = pq.poll();
			
			if(indegree[cur] == 0)
			{
				ans.add(cur);
			}
			
			for(int next : list[cur])
			{
				indegree[next]--;
				
				if(indegree[next] == 0)
				{
					pq.add(next);
				}
			}
		}
	}
}

class Pair
{
	int first;
	int second;
	
	Pair(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
}
