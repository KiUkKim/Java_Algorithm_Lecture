package BOJ_1275;

import java.util.*;
import java.io.*;

public class BOJ_1275 {

	static int N, Q, S;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		S = 1;
				
		N = Integer.parseInt(st.nextToken());
		
		while(S < N)
		{
			S *= 2;
		}

		tree = new long[S * 2 + 1];
		
		Q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			int num = Integer.parseInt(st.nextToken());
			
			update(1, S, 1, i + 1, num);
		}
		
//		for(long x : tree)
//		{
//			System.out.print(x + " ");
//		}
		
		for(int i = 0; i < Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(x > y)
			{
				int tmp = x;
				x = y;
				y = tmp;
			}
			
			int updateNode = Integer.parseInt(st.nextToken());
			int updateValue = Integer.parseInt(st.nextToken());
			
			bw.write(query(1, S, 1, x, y) + " ");
			bw.newLine();
			
			long diff = (long)updateValue - tree[(S + updateNode) - 1];
			
			update(1, S, 1, updateNode, diff);
		}
		
		bw.flush();
		bw.close();
	}
	
	static void update(int left, int right, int node, int target, long diff)
	{
		if(right < target || target < left)
			return;
		else {
			tree[node] += (long) diff;
			
			if(left != right)
			{
				int mid = (left + right) / 2;
				
				update(left, mid, node * 2, target, diff);
				
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	static long query(int left, int right, int node, int queryLeft, int queryRight)
	{
		if(queryRight < left || right < queryLeft)
		{
			return 0;
		}
		else if(queryLeft <= left && right <= queryRight)
		{
			return tree[node];
		}
		else
		{
			int mid = (left + right) / 2;
			
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
