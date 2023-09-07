package Day12.BOJ_2098;

import java.util.*;
import java.io.*;

public class BOJ_2098 {

	static int N;
	static int[][] dp;
	static int[][] W;
	static final int MAX = 20000000;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		W = new int[N+1][N+1];
		
		dp = new int[N][(1 << N)]; // ���� i�� ���ÿ� �湮 -> �湮�� ������ ���¸� Ȯ��
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				W[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// �湮���� �ʾ��� ��, �ʱ�ȭ
			
			// �湮�� �� �ϰ� INF�� ���Դ���, �湮�� ���� �ʾҴµ� INF�� ���Ӵ��� ������ �ȵż� �������ش�.
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(dfs(0, 1));
		
		for(int i = 0; i < N; i++)
		{
			for(int x : dp[i])
			{
				System.out.print(x + " ");
			}
			System.out.println();
		}
		
//		
//		bw.write(ans + " ");
//		bw.flush();
//		bw.close();
		
	}
	
	// ���� �湮�� ����, ������� �湮�� ����
	static int dfs(int now, int visit)
	{
		 // Ż�� ����
		// ��� ���ø� �� �湮���� ���
		if(visit == ((1 << N) - 1) )
		{
			// �ش� ������ ����� 0
			if(W[now][0] == 0)
			{
				return MAX;
			}
			return W[now][0];	
		}
		
		// �湮�� �� ���� �� ��� (dp �迭�� ������Ʈ�� �� ��� )
		if(dp[now][visit] != -1)
			return dp[now][visit];
		
		
		dp[now][visit] = MAX;
		
		for(int i = 0; i < N; i++)
		{
			// ���絵�ÿ��� �������÷� ���� ���� ���ų� �ƴϸ� �湮�� ������ ���
			if(W[now][i] == 0 || (visit & (1 << i)) != 0 )
			{
				continue;
			}
			
			// ���� ���ÿ��� ���� ���õ��� �湮�ϴ� ��� vs ���� ���÷� �� ������ ���� ���ÿ��� ���� ���
			int next_bit = (visit | (1 << i));
			
			
			System.out.println(now +  " " + visit + " " + dp[now][visit]);
			for(int k = 0; k < N; k++)
			{
				for(int x : dp[k])
				{
					System.out.print(x + " ");
				}
				System.out.println();
			}
			
			System.out.println();
			
			dp[now][visit] = Math.min(dp[now][visit], dfs(i, next_bit) + W[now][i]);
		}

		return dp[now][visit];
	}

}
