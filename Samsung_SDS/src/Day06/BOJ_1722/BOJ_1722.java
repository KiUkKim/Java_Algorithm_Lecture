package Day06.BOJ_1722;

import java.util.*;
import java.io.*;

public class BOJ_1722 {

	static long[] arr;
	static int[] ans;
	static int N;
	static int K;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		visited = new boolean[N+1];
		
		// �ش� �ڸ��� ��� ���� ������ �˾ƾ��Ѵ�.
		// ���� 1234���� ù°�ڸ��� 1�� �ִ� ��� ���� �� �ִ� ���� 3!�̴�.
		// �̷������� ���� ����
		// �׷��ٸ� ���⿡��
		// !�� ���� �����ϰ� �ȴٸ�
		// �ᱹ [N-idx] = ����Ǽ��� ������ �ȴ�.
		// ���� ��� �ش� �ڸ��� ù°�ڸ���� ���� �� �ִ� ����Ǽ��� 6 * 4�̴�
		// 1���ڸ� 6���� ���Դٸ� arr[3] = 6�� ���� ���;��Ѵ�.
		ans = new int[N+1];
		
		arr = new long[N+1];
		
		arr[0] = 1;
		
		for(int i = 1; i <= N; i++)
		{
			arr[i] = arr[i-1] * i;
		}

		K = Integer.parseInt(st.nextToken());
		
		if(K == 1)
		{
			long count = Long.parseLong(st.nextToken());
			cnt_check(count);
			System.out.println(sb.toString());
		}
		
		else {
			
			for(int i = 1; i <= N; i++)
			{
				ans[i] = Integer.parseInt(st.nextToken());
			}
			
			num_check();
			
			System.out.println(sb.toString());
		}
	}
	
	static void cnt_check(long count)
	{
		// ù°�ڸ����� �־��.
		for(int i = 1; i <= N; i++)
		{
			// �ش� �ڸ��� 1������ �־��.
			for(int j = 1; j <= N; j++)
			{
				// �̹� ���� �ֶ�� ����
				if(visited[j] == true)
					continue;
				
				// �ش� �������� ���� ���� ũ�ٸ�
				if(arr[N-i] < count)
				{
					count -= arr[N-i];
				}
				
				// ������ ���°��
				// �ش� �ڸ��� ���� �־��ش�.
				else {
					ans[i] = j;
					visited[j] = true;
					break;
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			sb.append(ans[i] + " ");
		}
	}
	
	static void num_check()
	{
		long count = 1;
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j < ans[i]; j++)
			{
				// ���� �̹� ���� ���ڶ�� �ȵȴ�.
				if(visited[j] == true)
					continue;
				
				count += arr[N-i]; // ���� �ش� �ڸ������� ���� �� �ִ� ����Ǽ��� �ְ� �����Ų��.
			}
			
			// �ڸ��� ���� ����Ǽ��� ���� ó�����ش�.
			visited[ans[i]] = true;
		}
		
		sb.append(count);
	}

}
