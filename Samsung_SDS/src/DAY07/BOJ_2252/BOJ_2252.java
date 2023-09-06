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
			
			graph[a].add(b); // a�� �׷����� ����
			
			indegree[b]++; // ���� ����
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
		// ���� ť�� ���������� 0�ΰ��� �־��ش�.
		for(int i = 1; i <= N; i++)
		{
			if(indegree[i] == 0)
			{
				q.add(i);
			}
		}
		
		
		// ���������� 0�ΰ����� �����Ѵ�.
		while(!q.isEmpty())
		{
			int cur = q.poll();
			
			ans.add(cur);
			
			// ���� ����� �׷����� ���·� ������.
			for(int next : graph[cur])
			{
				indegree[next]--;
				
				// ���� ����� ���� ����� ���������� 0�̵ƴٸ� q�� �־��ش�.
				if(indegree[next] == 0)
				{
					q.add(next);
				}
			}
		}
	}

}
