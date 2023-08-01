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
			
			// 사탕을 빼는 경우
			if(a == 1)
			{
				long idx = query(1, S, 1, b);
				sb.append(idx + "\n");
				update(1, S, 1, idx, -1); // 해당 사탕을 줄여준다.
			}
			// 사탕을 넣는 경우
			else {

				long c = Long.parseLong(st.nextToken());
				
				update(1, S, 1, b, c);
			}
		}
		
		System.out.println(sb.toString());
		
	}
		
	
	static long query(long left, long right, int node, long target)
	{
		// 리프노드일 경우
		// 이때가 몇번째 사탕을 알고 있을 때 이다.
		if(left == right)
		{
			return left;
		}
		
		// 만약 다 찾은 경우
		long mid = (left + right) / 2;
		long leftChildValue = tree[node * 2];
		
		// 만약 왼쪽 자식에서 해결이된다면
		if(target <= leftChildValue)
		{
			return query(left, mid, node * 2, target);
		}
		
		// 해결이 되지 않는 다면 오른쪽을 봐준다.
		else {
			return query(mid + 1, right, node * 2 + 1, target - leftChildValue);
		}
		
	}
	
	static void update(long left, long right, int node, long target, long diff)
	{		
		// 조건을 만족하지 않는 경우
		if(right < target || left > target)
		{
			return;
		}
		
		// 리프노드일 경우
		if(left == right)
		{
			tree[node] += diff;
			return;
		}
		// 조건 만족할 경우
		else {
			tree[node] += diff;
			
			long mid = (left + right) / 2;
			// 왼쪽과 오른쪽을 탐색해주어야한다.
			update(left, mid, node * 2, target, diff);
			
			update(mid + 1, right, node * 2 + 1, target, diff);
		}
	}

}
