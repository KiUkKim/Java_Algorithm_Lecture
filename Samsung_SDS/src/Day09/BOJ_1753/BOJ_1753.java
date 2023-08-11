package Day09.BOJ_1753;

import java.util.*;
import java.io.*;

public class BOJ_1753 {

	static int V, E, S;

	static List<Pair>[] graph;
	
	static final int INF = 987654321;
	
	static int[] dist;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		V = Integer.parseInt(st.nextToken());
		
		E = Integer.parseInt(st.nextToken());
		
		S = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V + 1];
		
		
		for(int i = 0; i <= V; i++)
		{
			graph[i] = new ArrayList<>();
		}
		
		dist = new int[V + 1];
		
		Arrays.fill(dist, INF);
		
		
		for(int i = 0; i < E; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from, to, weight;
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			weight = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Pair(to, weight));
		}
		
		
		bfs();
		
		for(int i = 1; i <= V; i++)
		{		
			if(dist[i] == INF)
			{
				System.out.println("INF");
			}
			
			else {
				System.out.println(dist[i]);				
			}
		}
	}
	
	static void bfs()
	{
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(S, 0));
		
		dist[S] = 0;
		
		
		while(!pq.isEmpty())
		{
			Pair cur = pq.poll();
			
			for(Pair next : graph[cur.node])
			{
				if(next.weight > dist[next.node])
					continue;
				
				if(dist[cur.node] + next.weight < dist[next.node])
				{
					dist[next.node] = dist[cur.node] + next.weight;
					pq.add(new Pair(next.node, (next.weight + dist[cur.node])));
				}
			}
		}
		
	}

}

class Pair implements Comparable<Pair>{
	int node;
	int weight;
	
	Pair(int node, int weight)
	{
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Pair o2)
	{
		return this.weight - o2.weight;
	}
	
}
