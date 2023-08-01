package Day02_BOJ_2042;

import java.io.*;
import java.util.*;

public class BOJ_2042 {

	static int N, K, M;
	static int S = 1;
	static long tree[];
	static long num[];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day02_BOJ_2042\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		num = new long[N];
		
		// S�� �� ����ֱ�
		while(S < N)
		{
			S *= 2;
		}
		
		for(int i = 0; i < N; i++)
		{
			num[i] = Long.parseLong(br.readLine());
		}
		
		tree = new long[S * 2];
		
		init();
		
		for(int i = 0; i < M + K; i++)
		{
			// 1�� ��� -> b��° ���� c�� �ٲ�
			
			// 2�� ��� -> b��° ������ c���� ����
			// ������ �Ͼ�� ����
			// �ʱ⿡�� root node - 2, 3, 1, 
			
			st = new StringTokenizer(br.readLine());
			
			long a = Long.parseLong(st.nextToken());
			
			long b = Long.parseLong(st.nextToken());
			
			long c = Long.parseLong(st.nextToken());
			
			
			if(a == 1)
			{
				// b��° ���� target
				long diff = c - tree[S + (int)b - 1]; 
						
				update(1, S, 1, b, diff);
			}
			
			else {
				sb.append(query(1, S, 1, b, c) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	
	// bottomup���8
	static void init()
	{
		// ���� ������ ���� �ʱ�ȭ
		for(int i = 0; i < N; i++)
		{
			tree[S + i] = num[i];
		}
		
		// bottom-up ���
		for(int i = S - 1; i > 0; i--)
		{
			// �¿��� �� �����ֱ�
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update(int left, int right, int node, long target, long diff)
	{
		// ���� ������ ������
		if(node >= tree.length)
			return;
		
		// ������ ���� ������ ���� ���
		if(right < target || left > target)
		{
			return;
		}
		else {
			// �ش� �ϴ� ������ ���̸�ŭ �ٲ��ش�.
			tree[node] += diff;
			
			int mid = (left + right) / 2;
			
			// ���� ������ Ž�����ش�.
			update(left, mid, node * 2, target, diff);
			
			// ������ ������ Ž�����ش�.
			update(mid + 1, right, node * 2 + 1, target, diff);
		}		
	}
	
	static long query(int left, int right, int node, long queryLeft, long queryRight)
	{
		if(node >= tree.length)
			return 0;
		
		// ������ ���� ������ ���� ���
		if(right < queryLeft || left > queryRight)
		{
			return 0;
		}
		// ������ ������ ���� ���
		else if(left >= queryLeft && right <= queryRight) {
			
			return tree[node];
		}
		
		// ������ Ž���ؾߵȴٸ�
		else {
			int mid = (left + right) / 2;
			// �¿� Ž���� ��ģ��
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
