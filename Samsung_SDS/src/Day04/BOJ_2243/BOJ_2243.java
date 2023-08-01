package Day04.BOJ_2243;

import java.util.*;
import java.io.*;


public class BOJ_2243{

	static int N, S;
	static long[] tree;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_2243\\input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		S = 1;
		
		while(S < 1000000)
		{
			S *= 2;
		}
		
		tree = new long[S * 2];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			long b = Long.parseLong(st.nextToken());
			
			// ������ ���� ���
			if(a == 1)
			{
				long idx = query(1, S, 1, b);
				sb.append(idx + "\n");
				update(1, S, 1, idx, -1); // �ش� ������ �ٿ��ش�.
			}
			// ������ �ִ� ���
			else {

				long c = Long.parseLong(st.nextToken());
				
				update(1, S, 1, b, c);
			}
		}
		
		System.out.println(sb.toString());
		
	}
		
	
	static long query(long left, long right, int node, long target)
	{
		// ��������� ���
		// �̶��� ���° ������ �˰� ���� �� �̴�.
		if(left == right)
		{
			return left;
		}
		
		// ���� �� ã�� ���
		long mid = (left + right) / 2;
		long leftChildValue = tree[node * 2];
		
		// ���� ���� �ڽĿ��� �ذ��̵ȴٸ�
		if(target <= leftChildValue)
		{
			return query(left, mid, node * 2, target);
		}
		
		// �ذ��� ���� �ʴ� �ٸ� �������� ���ش�.
		else {
			return query(mid + 1, right, node * 2 + 1, target - leftChildValue);
		}
		
	}
	
	static void update(long left, long right, int node, long target, long diff)
	{		
		// ������ �������� �ʴ� ���
		if(right < target || left > target)
		{
			return;
		}
		
		// ��������� ���
		if(left == right)
		{
			tree[node] += diff;
			return;
		}
		// ���� ������ ���
		else {
			tree[node] += diff;
			
			long mid = (left + right) / 2;
			// ���ʰ� �������� Ž�����־���Ѵ�.
			update(left, mid, node * 2, target, diff);
			
			update(mid + 1, right, node * 2 + 1, target, diff);
		}
	}

}
