package BOJ_14728;

import java.util.*;
import java.io.*;

public class BOJ_14728 {

	static int ans;
	static int N, M;
	static List<Score> scores;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		scores = new ArrayList<>();
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N + 1][M + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int time, score;
			
			time = Integer.parseInt(st.nextToken());
			
			score = Integer.parseInt(st.nextToken());
						
			scores.add(new Score(time, score));
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 0; j <= M; j++)
			{
				if(scores.get(i-1).time <= j)
				{
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - scores.get(i-1).time] + scores.get(i-1).score);
				}
				
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		bw.write(dp[N][M] + " ");
		bw.flush();
		bw.close();
				
	}

}

class Score{
	int time;
	int score;
	
	Score(int time, int score)
	{
		this.time = time;
		this.score = score;
	}
}
