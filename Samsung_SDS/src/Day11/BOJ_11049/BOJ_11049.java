package Day11.BOJ_11049;

import java.util.*;
import java.io.*;

public class BOJ_11049 {

	static int N;
	static Matrix[] matrix;
	static int[][] dp; // i번쨰부터 ~ j번째 곱한 연산 최소횟수를 넣는다.
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		matrix = new Matrix[N+1];
		
		dp = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int row = Integer.parseInt(st.nextToken());
			
			int col = Integer.parseInt(st.nextToken());
			
			matrix[i] = new Matrix(row, col);
		}
		
		
		// 기존 dp배열 초기화
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				if(i == j)
				{
					dp[i][j] = 0;
				}
				
				else {
					dp[i][j] = Integer.MAX_VALUE;
				}

			}
		}
		
		// Bottom-Up 방식 사용
		// 해당 길이는 몇번째 구간을 사용할까에 대한 for문
		for(int sec = 2; sec <= N; sec++)
		{
			// 우리는 1번째부터 범위를 확인할것이다. 
			for(int i = 1; i <= (N - sec + 1); i++)
			{
				// 다음 영역의 끝 구간을 확인한다.
				int j = i - 1 + sec;
				
				for(int k = i; k < j; k++)
				{
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + matrix[i].row * matrix[k+1].row * matrix[j].col);
				}
			}
		}
		
		bw.write(dp[1][N] + " ");
		
		bw.flush();
		bw.close();

	}

}

class Matrix{
	int row;
	int col;
	
	Matrix(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}
