package Day04.BOJ_5557;

import java.util.*;
import java.io.*;

public class BOJ_5557 {

	static long[][] dp;
	
	static int N;
	
	static final int MAX = 21;
	
	static List<Integer> list;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			
			list.add(num);
		}
		
		dp = new long[N+1][MAX];
		
		dp[0][list.get(0)] = 1l; // 처음에 만들 수 있는 경우는 1가지이다.
		
		// 2개를 고를 때 부터 경우의 수가 존재한다.
		for(int i = 1; i < N-1; i++)
		{
			// 0부터 만들 수 있는 수를 계산한다.
			for(int j = 0; j < MAX; j++)
			{
				// 만약 범위 내에 있는 수를 찾을 수 있다면
				if(j + list.get(i) >= 0 && j + list.get(i) <= 20)
				{
					dp[i][j] += dp[i-1][j + list.get(i)]; // 해당 범위 내에 있다면 더해준다.
				}
				
				if(j - list.get(i) >= 0 && j - list.get(i) <= 20)
				{
					dp[i][j] += dp[i-1][j - list.get(i)];
				}
			}
		}
		
		System.out.println(dp[N-2][list.get(N-1)]);
	}

}
