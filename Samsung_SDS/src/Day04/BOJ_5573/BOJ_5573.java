package Day04.BOJ_5573;

import java.util.*;
import java.io.*;

public class BOJ_5573 {

	static int[][] dp;
	static int[][] arr;
	static final int MAX = 1111;
	
	static int H, W, N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dp = new int[MAX][MAX];
		
		arr = new int[MAX][MAX];
		
		H = Integer.parseInt(st.nextToken());
		
		W = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= H; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= W; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// ù��° ������ 1~N-1������ N-1�� ��� ��´�
		dp[1][1] = N - 1;
		
		for(int i = 1; i <= H; i++)
		{
			for(int j = 1; j <= W; j++)
			{
				int value = dp[i][j];
				
				// ���� �����ΰ� '��'���
				if(arr[i][j] == 1)
				{
					dp[i][j+1] += (value / 2) + (value % 2);
					
					dp[i+1][j] += (value / 2);
				}
				
				else {
					dp[i+1][j] += (value / 2) + (value % 2);
					
					dp[i][j+1] += (value / 2);
				}
			}
		}
		
		// ���� N-1�� ������ N���� ��ƺ���.
		for(int i = 1; i <= H; i++)
		{
			for(int j = 1; j <= W; j++)
			{
				arr[i][j] = (arr[i][j] + (dp[i][j] % 2)) % 2;
			}
		}
		
		
		GetPath(1, 1);
	}
	
	static void GetPath(int y, int x)
	{
		if(y > H || x > W)
		{			
			System.out.println(y + " " + x);
			return;
		}
		
		// ���� '��'���
		if(arr[y][x] == 0)
		{
			GetPath(y+1, x);
		}
		
		else {
			GetPath(y, x+1);
		}

	}

}
