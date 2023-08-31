package BOJ_11600;

import java.util.*;
import java.io.*;

public class BOJ_11660 {

	static int N, M;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++)
			{
				arr[i][j] = arr[i][j-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			
			int sx = Integer.parseInt(st.nextToken());
			
			int gy = Integer.parseInt(st.nextToken());
			
			int gx = Integer.parseInt(st.nextToken());
			
			// 같을 행일 경우
			int ans = 0;
			
			for(int j = sy; j <= gy; j++)
			{
				ans += arr[j][gx] - arr[j][sx - 1];
			}
			
			bw.write(ans + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}

}
