package Day09.BOJ_11404;

import java.util.*;
import java.io.*;

public class BOJ_11404 {

	static int N, M;
	static int[][] dist;
	static final int INF = 987654321;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(i == j)
				{
					dist[i][j] = 0;
				}
				
				else {
					dist[i][j] = INF;
				}
			}
		}
		
		
		for(int i = 0; i < M; i++)
		{
			// 최소 비용이므로 경로가 2개 이상일 경우, 최단거리로 초기화하는 것이 좋음
			st = new StringTokenizer(br.readLine());
			
			int from, to, wei;
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			wei = Integer.parseInt(st.nextToken());
			
			dist[from][to] = Math.min(dist[from][to], wei);
		}
		
		for(int k = 1; k <= N; k++)
		{
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(dist[i][j] == INF)
					dist[i][j] = 0;
				bw.write(dist[i][j] + " ");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}

}
