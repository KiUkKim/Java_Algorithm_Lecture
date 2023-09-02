import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] parent;
	static int ans;
	static ArrayList<Node> graph;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		Arrays.fill(parent, -1);
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a, b, weight;
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			
			graph.add(new Node(a, b, weight));
		}
		
        Collections.sort(graph, new Comparator<Node>() {
            @Override
            public int compare(Node c1, Node c2) {
                return c1.weight - c2.weight;
            }
        });
        
        for(int i = 0; i < M; i++)
        {
        	int u = graph.get(i).nA;
        	int v = graph.get(i).nB;
        	
        	int cost = graph.get(i).weight;
        	
        	if(find(u) == find(v))
        		continue;
        	
        	union(u, v);
        	
        	ans += cost;
        	
        	if(parent[find(v)] == -N)
        		break;
        }
        
        System.out.println(ans);
		
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		if(a== b)
			return;
		
		parent[b] += parent[a]; // 크기 갖고가는 스킬
		parent[a] = b;
	}
	
	static int find(int x)
	{
		if(parent[x] < 0 )
			return x;
		
		return parent[x] = find(parent[x]);
	}

}

class Node{
	int nA;
	int nB;
	int weight;
	
	Node(int nA, int nB, int weight)
	{
		this.nA = nA;
		this.nB = nB;
		this.weight = weight;
	}
	
//	@Override
//	public int compareTo(Node o1)
//	{
//		return this.weight - o1.weight;
//	}
}
