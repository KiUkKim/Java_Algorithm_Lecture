package BOJ_10159;

import java.util.*;
import java.io.*;

public class BOJ_10159 {

	static int N, M;
	static final int INF = 1_000_000;
	static int dist[][];
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];
		
		for(int i = 1; i<= N; i++)
		{
			dist[i][i] = 1;
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int from, to;
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			dist[from][to] = 1;	
			
			dist[to][from] = -1;
		}
		
//		for(int i = 1; i <= N; i++)
//		{
//			for(int j = 1; j <= N; j++)
//			{
//				System.out.print(dist[i][j] + " ");
//			}
//			System.out.println();
//		}

		
		for(int k = 1; k <= N; k++)
		{
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					if(dist[i][k] == 1 && dist[k][j] == 1)
						dist[i][j] = 1;
					
					if(dist[i][k] == -1 && dist[k][j] == -1)
						dist[i][j] = -1;
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			int cnt = 0;
			for(int j = 1; j <= N; j++)
			{
				if(dist[i][j] != 0)
					cnt++;
//				System.out.print(dist[i][j] + " ");
			}
			bw.write(N - cnt + "\n");
		}
		
		bw.flush();
		bw.close();

	}

}
