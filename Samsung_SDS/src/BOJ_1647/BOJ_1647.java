package BOJ_1647;

import java.util.*;
import java.io.*;

public class BOJ_1647 {

	static int N, M;
	static List<Node> adj;
	static int[] parent;
	static long ans;
	static int MAX;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		adj = new ArrayList<>();
		
		Arrays.fill(parent, -1);

		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from, to, weight;
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			weight = Integer.parseInt(st.nextToken());
			
			adj.add(new Node(from, to, weight));
		}
		
		Collections.sort(adj, (o1, o2) -> {
			return o1.weight - o2.weight;
		});
		
		for(Node cur : adj)
		{
			int parentA = find(cur.from);
			
			int parentB = find(cur.to);
			
			if(parentA != parentB)
			{
				ans += cur.weight;
				
				MAX = Math.max(MAX, cur.weight);
				
				union(cur.from, cur.to);
				
				if(parent[cur.to] == -N)
					break;
			}
		}
		
		bw.write(ans - MAX + " ");
		bw.newLine();
		bw.flush();
		bw.close();
	}
	
	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		
		b = find(b);
		
		if(a != b)
		{
			parent[b] += parent[a];
			
			parent[a] = b;
		}
	}

}

class Node{
	int from;
	int to;
	int weight;
	
	Node(int from, int to, int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
