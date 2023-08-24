package BOJ_1005;

import java.util.*;
import java.io.*;

public class BOJ_1005 {

	static final int MAX = 987654321;
	static int TC, N, M, ans;
	static int[] indegree, value, sum;
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TC = Integer.parseInt(st.nextToken());
		
		while(TC -- > 0 )
		{
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			graph = new ArrayList[N + 1];
			
			for(int i = 0; i <= N; i++)
			{
				graph[i] = new ArrayList<>();
			}
			
			indegree = new int[N + 1];
			
			value = new int[N + 1];
			
			sum = new int[N + 1];
			
			
			for(int i = 1; i <= N; i++)
			{
				value[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < M; i++)
			{
				st = new StringTokenizer(br.readLine());

				int from;
				int to;
				
				from = Integer.parseInt(st.nextToken());
				
				to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				
				indegree[to]++;
			}
			
			st = new StringTokenizer(br.readLine());
			
			ans = Integer.parseInt(st.nextToken());
			
			topo_sort();
			
//			for(int x : sum)
//			{
//				System.out.print(x + " ");
//			}
			
//			System.out.println();
			
			bw.write(sum[ans] + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();

	}
	
	static void topo_sort()
	{
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++)
		{
			// 진입 차수가 0이라면 q에 넣어준다.
			if(indegree[i] == 0)
			{
				q.add(i);
				
				sum[i] = value[i];
			}
		}
		
		while(!q.isEmpty())
		{
			int cur = q.poll();
			
			for(int next : graph[cur])
			{
				indegree[next]--;
				
				if(sum[next] < sum[cur] + value[next])
				{
					sum[next] = sum[cur] + value[next];
				}
				
				if(indegree[next] == 0)
				{						
					q.add(next);
				}
			}
		}
	}

}
