package Day10.BOJ_1932;

import java.util.*;
import java.io.*;

public class BOJ_1932 {

	static int[][] dp;
	static int N;
	static int[][] arr;
	static int ans;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][N + 1];
		
		arr = new int[N + 1][N + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < i + 1; j++)
			{
				int n = Integer.parseInt(st.nextToken());
				
				arr[i][j] = n;
			}
		}
		
		// top-down������� �������ֱ�
		dp[0][0] = arr[0][0];
		
		ans = dp[0][0];
		
		for(int i = 1; i < N; i++)
		{
			for(int j = 0; j < i + 1; j++)
			{
				// ���� �����̶�� �׳� �������� �����´�.
				if(j == 0)
				{
					dp[i][j] = dp[i-1][j] + arr[i][j];
				}
				// ������ ����
				else if(j == i)
				{
					dp[i][j] = dp[i-1][j-1] + arr[i][j];
				}
				
				else {
					dp[i][j] = Math.max(dp[i-1][j-1] + arr[i][j], dp[i-1][j] + arr[i][j]);
				}
				
				ans = Math.max(ans, dp[i][j]);
			}
		}
		
		bw.write(ans +  " ");
		bw.flush();
		bw.close();
	}

}
