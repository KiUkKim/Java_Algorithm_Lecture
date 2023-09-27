package BOJ_1719;

import java.util.*;
import java.io.*;

public class BOJ_1719 {

	static final int MAX = 987654321;
	static int N, M;
	static int adj[][];
	static int[][] ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		adj = new int[N + 1][N + 1];
		
		ans = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			Arrays.fill(adj[i], MAX);
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				ans[i][j] = j;	
			}
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a, b, c;
			
			a = Integer.parseInt(st.nextToken());
			
			b = Integer.parseInt(st.nextToken());
			
			c = Integer.parseInt(st.nextToken());
			
			adj[a][b] = Math.min(adj[a][b], c);
			adj[b][a] = Math.min(adj[b][a], c);
		}
		
		for(int k = 1; k <= N; k++)
		{
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					if(adj[i][j] > adj[i][k] + adj[k][j])
					{
						adj[i][j] =  adj[i][k] + adj[k][j];
						
						ans[i][j] = ans[i][k];
					}
				}
			}
		}
		
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(i == j)
				{
					System.out.print("- " );
				}
				else {
					System.out.print(ans[i][j] + " ");
				}

			}
			System.out.println();
		}
	}
	
}