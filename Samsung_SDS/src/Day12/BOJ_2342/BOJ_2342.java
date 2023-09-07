package Day12.BOJ_2342;

import java.util.*;
import java.io.*;

public class BOJ_2342 {

	static int[][][] dp; // i번째 진행됐을때 왼발 오른발의 발판을 나타낸다.
	static List<Integer> num;
	static int size;
	static final int INF = 987654321;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		num = new ArrayList<>();


		num.add(0); // 초기 발판
		
		while(true)
		{
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 0)
				break;
			
			num.add(a);
		}
		
		size = num.size();

		
		dp = new int[size + 1][5][5]; // 게임 진행횟수, 방향
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				for(int k = 0; k < 5; k++)
				{
					dp[i][j][k] = INF;
				}
			}
		}
		
		dp[0][0][0] = 0; // 처음 진행상태
		
		for(int sel = 0; sel < size - 1; sel++)
		{
			int nextStep = num.get(sel + 1); // 다음에 밟아야할 발판을 본다.
			
			for(int i = 0; i < 5; i++)
			{
				for(int j = 0; j < 5; j++)
				{
					int nowDP = dp[sel][i][j]; // 현재 ( 이 구간으로 인해서 num을 하나 더 추가해줌)
					
					// 현재 구간 -> 다음구간으로 갈 예정인데  현재구간이 INF면 아직 못밟은 곳이다.
					if(nowDP == INF)
						continue;
					
			
					// 같은 발에 위치할 수 없으므로, 오른발에 j가 있는 dp의 왼발을 업데이트 시킨다.
					if(nextStep != j)
					{
						dp[sel + 1][nextStep][j] = Math.min(dp[sel+1][nextStep][j], nowDP + getScore(i, nextStep));
					}
					
					if(nextStep != i)
					{
						dp[sel + 1][i][nextStep] = Math.min(dp[sel + 1][i][nextStep], nowDP + getScore(j, nextStep)); 
					}
					
				}
			}
		}
		
		// 마지막 단계의 최소값을 찾아본다.
//		System.out.println(num.size());
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				ans = Math.min(ans, dp[size-1][i][j]);
			}
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
		
	}
	
	static int getScore(int cur, int next)
	{
		if(cur == 0)
			return 2;
		else if(cur == next)
			return 1;
		else if(Math.abs(cur - next) == 2)
		{
			return 4;
		}
		
		return 3;
	}

}
