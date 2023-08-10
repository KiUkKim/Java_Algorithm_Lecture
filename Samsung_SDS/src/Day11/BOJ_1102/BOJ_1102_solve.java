package Day11.BOJ_1102;

import java.util.*;
import java.io.*;

public class BOJ_1102_solve {

	static int N, P;
	
	static int[][] dp;
	static int[][] cost;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[N+1][1 << N]; // 2^16��ŭ ���� �׷��� 0������ 15������ ��� ����� �� ���� ������ �� �ִ�.
		
		cost = new int[N+1][N+1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// 0���� �ִ�������� �ʱ�ȭ���ش�.
			for(int j = 0; j < 1 << N; j++)
			{
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		String state = st.nextToken();
		
		int bit = 0;
		int cnt = 0;
		
		for(int i = 0; i < state.length(); i++)
		{
			// ���� �����Ұ� �����ִٸ�
			if(state.charAt(i) == 'Y')
			{
				bit |= (1 << i);
				cnt++;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		
		int answer = dfs(cnt, bit);
		
		// �� ��� �ش� cnt�� ���� ���� ã�� �� ������ �ǹ��Ѵ�.
		if(answer == Integer.MAX_VALUE)
		{
			bw.write(-1 + " ");
		}
		
		else {
			bw.write(answer + " ");
		}
		
		bw.flush();
		bw.close();
	}
	
	static int dfs(int cnt, int bit)
	{
		if(cnt >= P)
			return 0;
		
		// �ּҺ���� ã���� �ǹ��Ѵ�.
		if(dp[cnt][bit] != Integer.MAX_VALUE)
		{
			return dp[cnt][bit];
		}
		
		
		for(int i = 0; i < N; i++)
		{
			// ���� ��Ʈ ���¸� Ȯ���Ѵ�.
			
			// �ǹ̴� ���� ��Ʈ�� ���ؼ� �����ִ� �����Ҹ� Ȱ���Ϸ��Ѵ�. 
			if( (bit & (1 << i)) != 0)
			{
				for(int j = 0; j < N; j++)
				{
					// �ڱ��ڽ��̰ų�, �̹� ���� ��Ʈ�� �ƴ� ��� �����ϴ�.
					
					// ���� �ڵ�� �ǹ̴� ���� �������߿��� � �����⸦ Ű���� �Ҷ�, �̹� �����ִٸ� �ѱ�� ���� �ǹ��Ѵ�.
					if(i == j || (bit & (1 << j)) != 0)
					{
						continue;
					}
					
					// �Ȳ��� �����Ҹ� Ȱ��ȭ ��Ų��.
					// ���� i���� ���ؼ� j���� �������� ���� cost[i][j]��������Ѵ�.
					dp[cnt][bit] = Math.min(dp[cnt][bit], dfs(cnt + 1, bit | (1 << j)) + cost[i][j]);
				}
			}
		}
		
		return dp[cnt][bit];
	}

}
