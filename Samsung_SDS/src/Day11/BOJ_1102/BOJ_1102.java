package Day11.BOJ_1102;

import java.util.*;
import java.io.*;

public class BOJ_1102 {

	static int N, P;
	static int[][] cost, dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		cost = new int[N][N];
		
		dp = new int[N][1<<N]; // 2^16�� ���� ������
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j < 1 << N; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
			
		}
		
		String state = br.readLine();
		
		P = Integer.parseInt(br.readLine());
		
		// �������� ���¸� ��Ʈ����ũ ���·� ����
		// OR ������ ���� i��° �ش��ϴ� �����Ҹ� on
		int cnt = 0;
		int bit = 0;
		
		for(int i = 0; i < N; i++)
		{
			// �����ִ� ���
			if(state.charAt(i) == 'Y')
			{
				bit = bit | (1 << i);
				cnt++;
			}
		}
		
		int answer = dfs(cnt, bit);
		
		if(answer == Integer.MAX_VALUE)
		{
			System.out.println("-1");
		}
		else {
			System.out.println(answer);
		}
		
		

	}
	
	static int dfs(int cnt, int bit)
	{
		// ������ ������ �� �̻� �ʿ����
		if(cnt >= P)
		{
			return 0;
		}
		
		// �ּ� ����� ����
		if(dp[cnt][bit] != Integer.MAX_VALUE)
		{
			return dp[cnt][bit];
		}
		
		for(int i = 0; i < N; i++)
		{
			// ���� �ش� ��ȣ�� �����Ⱑ ����������
			if((bit & (1 << i)) != 0)
			{
				for(int j  = 0; j < N; j++)
				{
					// ���� �ش� �����Ұ� �����ְų� ( ���������� �ٽ� �۵���ų ������ ����.)
					// 
					if(i == j || ((bit & (1 << j)) != 0))
					{
						continue;
					}
					
					// �ּڰ� ���ϱ�
					// Top-Down ���
					dp[cnt][bit] = Math.min(dp[cnt][bit], dfs(cnt + 1, bit | (1 << j )) + cost[i][j]);
				}
			}
		}	
	
		return dp[cnt][bit];
	}

}
