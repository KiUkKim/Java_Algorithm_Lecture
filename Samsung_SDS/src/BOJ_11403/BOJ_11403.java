package BOJ_11403;

import java.util.*;
import java.io.*;

public class BOJ_11403 {

	static int N;
	static int[][] dist;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++)
			{
				int num = Integer.parseInt(st.nextToken());
				
				dist[i][j] = num;
			}
		}
		
		
		for(int k = 1; k <= N; k++)
		{
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					if(dist[i][k] == 1 && dist[k][j] == 1)
					{
						dist[i][j] = 1;
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		

	}

}
