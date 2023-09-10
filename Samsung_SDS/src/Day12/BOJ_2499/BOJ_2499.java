package Day12.BOJ_2499;

import java.util.*;
import java.io.*;

public class BOJ_2499 {

	static int N, M;
	static final int INF = 987654321;
	
	static int[][] dp; // i ~ j�������� ������ �ּ����� ��Ÿ����.
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
		
		// dp �迭 �ʱ�ȭ
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
		
		// bottom-up ���
		// ���� �츮�� ������ Ȯ���Ұ��̴�.
		// ���̴� 1�� �� �ʿ䰡 �����ϱ� -> 
		// ���� 2�� ���÷� ���ٸ� -> dp[1][3] = dp[1][1] + dp[2][3] + (arr[1] vs arr[2] -> ���� �������ִ� ������ �ʿ���)
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
