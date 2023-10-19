package BOJ_10835;

import java.util.*;
import java.io.*;

public class BOJ_10835 {

	static int N;
	static int[] leftArr, rightArr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		leftArr = new int[N + 1];
		
		rightArr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			leftArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			rightArr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N + 1][N + 1];
		
		for(int[] d : dp)
		{
			Arrays.fill(d, -1);
		}
		
		// 가능한 경우의 수 // 왼쪽카드 빼기 - 왼쪽 오른쪽 다 빼기, 오른쪽 카드만 빼기
		bw.write(recursive(0, 0) + "\n");
		bw.flush();
		bw.close();
		
	}
	
	static int recursive(int left, int right)
	{
		if(left == N || right == N)
			return 0;
		
		if(dp[left][right] != -1)
			return dp[left][right];
		
		dp[left][right] = 0;
		
		int recur1 = recursive(left + 1, right);
		
		int recur2 = recursive(left + 1, right + 1);
		
		int recur3 = 0;
		
		if(leftArr[left] > rightArr[right])
		{
			recur3 = rightArr[right] + recursive(left, right + 1);
		}
		
		return dp[left][right] = Math.max(recur1, Math.max(recur2, recur3));
		
	}

}
