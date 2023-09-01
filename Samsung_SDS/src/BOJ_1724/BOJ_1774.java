package BOJ_1724;

import java.util.*;
import java.io.*;

public class BOJ_1774 {

	static int N, M;
	static int[] parent;
	static Pair[] map;
	static double ans;
	static List<Edge> EdgeList;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		Arrays.fill(parent, -1);
		
		map = new Pair[N + 1];
		
		EdgeList = new ArrayList<>();
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			
			int x = Integer.parseInt(st.nextToken());
			
			map[i] = new Pair(y, x);
		}
		
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		// 1번부터 ~ N번까지 확인해본다.
		// ed) 1 2 1 3 14 를확인했다면 21은 볼 필요 x 23 <<
		
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = i + 1; j <= N; j++)
			{
				double len = findDistance(map[i], map[j]);
				
				EdgeList.add(new Edge(i, j, len));
			}
		}

		Collections.sort(EdgeList);
		
		for(Edge e : EdgeList)
		{
			if(find(e.start) != find(e.end))
			{
				union(e.start, e.end);
				
				ans += e.wegith;
				
				if(parent[e.end] == -N)
					break;
			}
		}
		
		
		String roundAns = String.format("%.2f", ans);
		
		bw.write(roundAns);
		bw.flush();
		bw.close();

	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		if(a == b)
			return;
		
		parent[b] += parent[a];
		
		parent[a] = b;
	}
	
	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}
	
	static double findDistance(Pair p1, Pair p2)
	{
		return Math.sqrt(Math.pow(p1.y - p2.y, 2) + Math.pow(p1.x - p2.x, 2));
	}

}

class Pair
{
	int y;
	int x;
	
	Pair(int y, int x)
	{
		this.y = y;
		this.x = x;
	}
}

class Edge implements Comparable<Edge>{
	int start;
	int end;
	double wegith;
	
	Edge(int start, int end, double weight)
	{
		this.start =start;
		this.end = end;
		this.wegith = weight;
	}
	
	@Override
	public int compareTo(Edge o2)
	{
		return this.wegith < o2.wegith ? -1 : 1;
	}
}
