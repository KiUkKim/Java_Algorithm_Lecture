package DP_EX.BOJ_1890;

import java.util.*;
import java.io.*;

public class BOJ_1890 {

	static int N;
	static int[][] map;
	static long[][] dp;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		dp = new long[N + 1][N + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				int num = Integer.parseInt(st.nextToken());
				
				map[i][j] = num;
			}
		}
		
		dp[0][0] = 1;
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				int dirValue = map[i][j];
				
				if(dirValue == 0)
					break;
				
				// 아래로 이동 가능할 경우
				if(i + dirValue < N)
				{
					dp[i+dirValue][j] += dp[i][j];
				}
				
				// 오른쪽으로 이동 가능 할 경우
				if(j + dirValue < N){
					dp[i][j+dirValue] += dp[i][j];
				}
			}
		}
		
		bw.write(dp[N-1][N-1] + " ");
		bw.newLine();
		bw.flush();
		bw.close();

	}

}
