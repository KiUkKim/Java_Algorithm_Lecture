package Day11.BOJ_1102;

import java.io.*;
import java.util.*;

public class BOJ_1102_BruteForce {

	static int N, P;
	static int[][] value;
	static List<Integer> list;
	static boolean[] initState;
	static boolean[] PlusState;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		value = new int[N + 1][N + 1];

		list = new ArrayList<>();
		
		initState = new boolean[N + 1];
		
		PlusState = new boolean[N + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				value[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		
		// �ʱ� ���� ����
		int cnt = 0;
		
		String init = st.nextToken();
		
		for(int i = 0; i < init.length(); i++)
		{
			if(init.charAt(i) == 'Y')
			{
				cnt++;
				initState[i] = true;				
			}
			else {
				initState[i] = false;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		
		if(cnt == 0)
		{
			if(P == 0)
			{
				bw.write(0);
			}
			
			else if(P != 0)
			{
				bw.write(-1);
			}
		}
		else {
			// i������ �ش� �ϴ� index �����⸦ �������� ����
			if(P > N)
			{
				bw.write(-1);
			}
			
			else {
				dfs(cnt, 0, 0);
				
				bw.write(ans + " ");
			}
		}

		bw.flush();
		bw.close();
	}
	
	static void dfs(int cnt, int idx, int sum)
	{
		// ���� ������ ���� �������� ���� �����⸦ ���ش�.
		if(cnt >= P)
		{
			ans = Math.min(ans, sum);
			
			return;
		}
		
		for(int j = idx; j < N; j++)
		{
			if(!initState[j])
				continue;
			for(int i = 0; i < N; i++)
			{
				// ���� ���̶�� �Ѱ��ش�.
				if(i == j)
					continue;
				
				// ���� �������� �Ѿ��.
				if(initState[i])
					continue;
				
				if(!initState[i])
				{
					initState[i] = true;
					dfs(cnt + 1, idx, sum + value[j][i]);
					initState[i] = false;
				}
			}
		}

	}

}
