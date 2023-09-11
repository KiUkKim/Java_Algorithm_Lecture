package Day10.BOJ_2579;

import java.util.*;
import java.io.*;

public class BOJ_2579 {

	static int N;
	static int[] dp, step;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		step = new int[N + 2];
		
		dp = new int[N + 2];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			step[i] = Integer.parseInt(st.nextToken());
		}
		
		// �ʱ�����
		dp[1] = step[0];
		dp[2] = dp[1] + step[1];
		
		for(int i = 3; i <= N; i++)
		{
			// ���� �ش� ������� ���������
			// ��ĭ������ ��ĭ�̵�, ��ĭ������ ��ĭ�̵����� ������ �� �ִ�.
			dp[i] = Math.max(dp[i-2] + step[i-1], dp[i-3] + step[i-2] + step[i - 1]);
		}
		
		bw.write(dp[N] + " ");
		bw.flush();
		bw.close();
	}

}
