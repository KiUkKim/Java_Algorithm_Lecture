package Day12.BOJ_2342;

import java.util.*;
import java.io.*;

public class BOJ_2342 {

	static int[][][] dp; // i��° ��������� �޹� �������� ������ ��Ÿ����.
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


		num.add(0); // �ʱ� ����
		
		while(true)
		{
			int a = Integer.parseInt(st.nextToken());
			
			if(a == 0)
				break;
			
			num.add(a);
		}
		
		size = num.size();

		
		dp = new int[size + 1][5][5]; // ���� ����Ƚ��, ����
		
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
		
		dp[0][0][0] = 0; // ó�� �������
		
		for(int sel = 0; sel < size - 1; sel++)
		{
			int nextStep = num.get(sel + 1); // ������ ��ƾ��� ������ ����.
			
			for(int i = 0; i < 5; i++)
			{
				for(int j = 0; j < 5; j++)
				{
					int nowDP = dp[sel][i][j]; // ���� ( �� �������� ���ؼ� num�� �ϳ� �� �߰�����)
					
					// ���� ���� -> ������������ �� �����ε�  ���籸���� INF�� ���� ������ ���̴�.
					if(nowDP == INF)
						continue;
					
			
					// ���� �߿� ��ġ�� �� �����Ƿ�, �����߿� j�� �ִ� dp�� �޹��� ������Ʈ ��Ų��.
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
		
		// ������ �ܰ��� �ּҰ��� ã�ƺ���.
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
