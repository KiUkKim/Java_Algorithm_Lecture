package Day09.BOJ_5719;

import java.io.*;
import java.util.*;

public class BOJ_5719 {

	static int V, E;
	static List<Node>[] graph;
	static StringTokenizer st;
	static List<Integer> ans;
	static int[] dist;
	static boolean[][] exPath;
	static List<Integer>[] shortestPath;
	
	// 다익스트라를 통해 최단 경로 트래킹 해준다. > 최단 경로를 추적해주기 위해서는 별도의 리스트를 하나 더 만들어서 저장.
	// 접근 방법
	// 1. 간선 정보를 인접리스트에 저장한다.
	// 2. 첫번 째 최단 경로를 1차 다익스트라를 통해 구해준다. > 최단 경로에 해당되는 간선을 추적해서 저장
	// 3. 추적한 간선에 대한 정보를 활용해서 boolean 배열 탐색 불가하도록 true 처리
	// 4. 다익스트라 수행해서 최단 경로를 재탐색한다.
	// 5. dist[] 원하는 특정 정점으로의 거의 최단 경로에 저장 -> 출력
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			
			E = Integer.parseInt(st.nextToken());
			
			if(V == 0 && E == 0)
				break;
			
			dist = new int[V + 1];
			
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			graph = new ArrayList[V + 1];
			
			shortestPath = new ArrayList[V + 1];
			
			for(int i = 0; i <= V; i++)
			{
				graph[i] = new ArrayList<>();
				shortestPath[i] = new ArrayList<>();
			}
			
			exPath = new boolean[V + 1][V + 1];
			
			st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			
			int D = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < E; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				int from, to, weight;
				
				from = Integer.parseInt(st.nextToken());
				
				to = Integer.parseInt(st.nextToken());
				
				weight = Integer.parseInt(st.nextToken());
	
				graph[from].add(new Node(to, weight));
			}
			
			// 최단 경로 탐색
			djkstra(S);
			
			// 최단 경로 제외
			removeShortestPath(S, D);
			
			// 최단 경로 재탐색
			Arrays.fill(dist, Integer.MAX_VALUE);
			djkstra(S);
			
			if(dist[D] == Integer.MAX_VALUE)
				dist[D] = -1;
			
			bw.write(dist[D] + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	static void djkstra(int S)
	{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		dist[S] = 0;
		
		pq.add(new Node(S, 0));
		
		while(!pq.isEmpty())
		{
			Node cur = pq.poll();
			
			if(dist[cur.node] < cur.weight)
				continue;
			
			for(Node next : graph[cur.node])
			{
				if(exPath[cur.node][next.node] == true)
					continue;
			
				
				// 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
				if(dist[next.node] > dist[cur.node] + next.weight)
				{
					dist[next.node] = dist[cur.node] + next.weight;
					shortestPath[next.node] = new ArrayList<>();
					shortestPath[next.node].add(cur.node);
					pq.add(new Node(next.node, dist[next.node]));
							
				}
				// 최단 경로가 2개 이상일 수도 있으므로
				else if(dist[next.node] == dist[cur.node] + next.weight)
				{
					shortestPath[next.node].add(cur.node);
				}
			}
		}
		
	}
	
	static void removeShortestPath(int start, int end)
	{
		if(start == end)
		{
			return;
		}
		
		for(int next : shortestPath[end])
		{
//			System.out.println(start + " " + next + " " + end);
			if(!exPath[next][end])
			{
				exPath[next][end] = true;
				removeShortestPath(start, next);
			}
		}
	}

}

class Node implements Comparable<Node>{
	int node;
	int weight;
	
	Node(int node, int weight)
	{
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o2)
	{
		return this.weight - o2.weight;
	}
}
