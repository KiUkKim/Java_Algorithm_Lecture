package BOJ_11437;

import java.util.*;
import java.io.*;

public class BOJ_11437 {
	
	static int[] depth;
	static int[][] parent;
	static List<Integer>[] adj;
	static int MAX = -1;
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i *= 2)
		{
			MAX++;
		}
		
		adj = new ArrayList[N+1];
		
		depth = new int[N + 1];
		
		parent = new int[N + 1][MAX + 1];
		
		for(int i = 0; i <= N; i++)
		{
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		Arrays.fill(depth, -1);
		
		for(int i = 0; i <= N; i++)
		{
			Arrays.fill(parent[i], -1);
		}
		
		depth[1] = 0;
		
		makeTreeDFS(1);
		
//		for(int k :depth)
//		{
//			System.out.print(k + " ");
//		}
		
		// 조상을 업데이트 해준다.
		for(int j = 0; j < MAX; j++)
		{
			for(int i = 2; i <= N; i++)
			{
				int k = parent[i][j];
				
				// 부모가 있다면
				if(k != -1)
				{
					parent[i][j+1] = parent[k][j];
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			int b = Integer.parseInt(st.nextToken());
			
			// depth깊이 맞춰주기
			if(depth[a] < depth[b])
			{
				int temp = a;
				a = b;
				b = temp;
			}
			
			int diff = depth[a] - depth[b];
						
			int index = 0;
			
//			System.out.println("H! : " + a + " " + diff + " " + depth[a] + " " + depth[b] );
			
			while(diff > 0)
			{
				if(diff % 2 == 1)
				{
					a = parent[a][index];
				}
				
				diff /= 2;
				index++;
			}
			
//			System.out.println(a + " " + diff);
			
			// 아직 높이를 찾지 못했다면
			if(a != b)
			{
				for(int k = MAX; k >= 0; k--)
				{
					if(parent[a][k] != parent[b][k])
					{
						a = parent[a][k];
						b = parent[b][k];
					}
				}
				

				a = parent[a][0];
			}
			
			bw.write(a + " ");
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	static void makeTreeDFS(int cur)
	{
		for(int next : adj[cur])
		{
			if(depth[next] == -1)
			{
				depth[next] = depth[cur] + 1;
				parent[next][0] = cur;
				makeTreeDFS(next);
			}
		}
	}
}
