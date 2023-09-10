package Day12.BOJ_2499;

import java.util.*;
import java.io.*;

public class BOJ_2499 {

	static int N, M;
	static final int INF = 987654321;
	
	static int[][] dp; // i ~ j번까지의 구간의 최소합을 나타낸다.
	static int[] color;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());

		color = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		dp = new int[N + 1][N + 1];
		
		for(int i = 0; i < N; i++)
		{
			color[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp 배열 초기화
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(i == j)
				{
					dp[i][j] = 0;
				}
				else {
					dp[i][j] = INF;
				}
			}
		}
		
		// bottom-up 방식
		// 이제 우리는 구간을 확인할것이다.
		// 길이는 1은 볼 필요가 없으니깐 -> 
		// 길이 2를 예시로 본다면 -> dp[1][3] = dp[1][1] + dp[2][3] + (arr[1] vs arr[2] -> 둘을 통합해주는 과정이 필요함)
		for(int len = 2; len <= N; len++)
		{
			for(int i = 0; i < (N - len) + 1; i++)
			{
				int j = (i - 1) + len;
				for(int k = i; k < j; k++)
				{
					int cnt = 0;
					
					if(color[i] != color[k + 1])
						cnt = 1;
					
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + cnt);
				}
			}
		}
		
		
		bw.write(dp[0][N-1] + " ");
		bw.flush();
		bw.close();
	}

}
