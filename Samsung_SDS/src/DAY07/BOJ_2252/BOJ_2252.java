package DAY07.BOJ_2252;


import java.util.*;
import java.io.*;

public class BOJ_2252 {
	
	static int N, M;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	static List<Integer> ans;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int [N+1];
		
		graph = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++)
		{
			graph[i] = new ArrayList<>();
		}
		
		ans = new ArrayList<>();
		
		q = new LinkedList<>();
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			int b =  Integer.parseInt(st.nextToken());
			
			graph[a].add(b); // a의 그래프에 넣음
			
			indegree[b]++; // 차수 증가
		}
		
		topo_sort();
		
		for(int a : ans)
		{
			sb.append(a + " ");
		}
		
		System.out.println(sb.toString());
	}
	
	static void topo_sort()
	{
		// 먼저 큐에 진입차수가 0인것을 넣어준다.
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
			{
				q.add(i);
			}
		}
		
		
		// 진입차수가 0인곳부터 시작한다.
		while(!q.isEmpty())
		{
			int cur = q.poll();
			
			ans.add(cur);
			
			// 현재 연결된 그래프를 상태로 돌린다.
			for(int next : graph[cur])
			{
				indegree[next]--;
				
				// 만약 연결된 다음 노드의 진입차수가 0이됐다면 q에 넣어준다.
				if(indegree[next] == 0)
				{
					q.add(next);
				}
			}
		}
	}

}
