package BOJ_1761;

import java.util.*;
import java.io.*;

public class BOJ_1761 {

	static int parent[][]; 
	static int parentSum[][]; // i의 j번째 값을 저장
	static int[] depth;
	static int N, M;
	static int MAX = -1;
	static List<Node>[] adj;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i *= 2)
		{
			MAX++;
		}
		
		parent = new int[N + 1][MAX + 1];
		depth = new int[N + 1];
		parentSum = new int[N + 1][MAX + 1];
		adj = new ArrayList[N + 1];
		
		for(int i = 0; i <= N; i++)
		{
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			
			int to = Integer.parseInt(st.nextToken());
			
			int weight = Integer.parseInt(st.nextToken());
			
			adj[from].add(new Node(to, weight));
			
			adj[to].add(new Node(from, weight));
		}
		
		Arrays.fill(depth, -1);
		
		for(int i = 0; i <= N; i++)
		{
			Arrays.fill(parent[i], -1);
			Arrays.fill(parentSum[i], -1);
		}
		
		depth[1] = 0;
		
		makeTreeDFS(1);
		
		// 이제 조상들을 보면서 누적합의 형태로 쌓아준다.
		for(int i = MAX - 1; i >= 0; i--)
		{
			for(int j = 2; j <= N; j++)
			{
				int k = parent[j][i];
				
//				System.out.println(k + " ");
				
				if(k != -1)
				{
					parent[j][i + 1] = parent[k][i];
					
					parentSum[j][i+1] = parentSum[j][i] + parentSum[k][i]; 
				}
			}
		}
		
//		for(int i = 1; i <= N; i++)
//		{
//			for(int j = 0; j <= MAX; j++)
//			{
//				System.out.print(parentSum[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++)
		{
			int ans = 0;
			
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			
			int v = Integer.parseInt(st.nextToken());
			
			// 높이 맞춰주기
			if(depth[u] < depth[v])
			{
				int temp = u;
				u = v;
				v = temp;
			}
			
			int diff = depth[u] - depth[v];
			
			int idx = 0;
			
			while(diff > 0)
			{
				if(diff % 2 == 1)
				{
					u = parent[u][idx];
					ans += parentSum[u][idx];
					System.out.println(u + " " + i + " : " + ans + " ");
				}
				
				diff /= 2;
				idx++;
			}
			
			if(u != v)
			{
				for(int k = MAX; k >= 0; k--)
				{
					if(parent[u][k] != parent[v][k])
					{
						u = parent[u][k];
						v = parent[v][k];
						
						ans += (parentSum[u][k] + parentSum[v][k]);
					}
				}
				
				u = parent[u][0];
				
				ans += parentSum[u][0];
				
				System.out.println(i + " 2  : " + ans + " ");
			}
			
			bw.write(ans + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	static void makeTreeDFS(int cur)
	{
		for(Node next : adj[cur])
		{
			if(depth[next.to] == -1)
			{
				depth[next.to] = depth[cur] + 1;
				parent[next.to][0] = cur;
				parentSum[next.to][0] = next.weight;
				makeTreeDFS(next.to);
			}
		}
	}
}

class Node{
	int to;
	int weight;
	
	Node(int to, int weight)
	{
		this.to = to;
		this.weight = weight;
	}
}
