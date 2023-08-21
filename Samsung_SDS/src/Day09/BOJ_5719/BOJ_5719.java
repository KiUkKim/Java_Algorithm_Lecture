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
	
	// ���ͽ�Ʈ�� ���� �ִ� ��� Ʈ��ŷ ���ش�. > �ִ� ��θ� �������ֱ� ���ؼ��� ������ ����Ʈ�� �ϳ� �� ���� ����.
	// ���� ���
	// 1. ���� ������ ��������Ʈ�� �����Ѵ�.
	// 2. ù�� ° �ִ� ��θ� 1�� ���ͽ�Ʈ�� ���� �����ش�. > �ִ� ��ο� �ش�Ǵ� ������ �����ؼ� ����
	// 3. ������ ������ ���� ������ Ȱ���ؼ� boolean �迭 Ž�� �Ұ��ϵ��� true ó��
	// 4. ���ͽ�Ʈ�� �����ؼ� �ִ� ��θ� ��Ž���Ѵ�.
	// 5. dist[] ���ϴ� Ư�� ���������� ���� �ִ� ��ο� ���� -> ���
	
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
			
			// �ִ� ��� Ž��
			djkstra(S);
			
			// �ִ� ��� ����
			removeShortestPath(S, D);
			
			// �ִ� ��� ��Ž��
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
			
				
				// ���� ��带 ���ļ� �ٸ� ���� �̵��ϴ� �Ÿ��� �� ª�� ���
				if(dist[next.node] > dist[cur.node] + next.weight)
				{
					dist[next.node] = dist[cur.node] + next.weight;
					shortestPath[next.node] = new ArrayList<>();
					shortestPath[next.node].add(cur.node);
					pq.add(new Node(next.node, dist[next.node]));
							
				}
				// �ִ� ��ΰ� 2�� �̻��� ���� �����Ƿ�
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
