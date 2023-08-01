package Day03.Index_Tree_TC;

import java.util.*;
import java.io.*;

public class index_tree {

	static int N, M, K;
	static long[] tree;
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		long S = 1;
		
		while(S < N)
		{
			S *= 2;
		}
		
		// init은 쉽게 bottom-up으로 구현하기
	}

	static long query(int left, int right, int node, int queryLeft, int queryRight)
	{
		if(queryRight < left || right < queryLeft)
			return 0;
		else if(queryRight <= left && right <= queryRight)
		{
			return tree[node];
		}
		
		else {
			int mid = (left + right) / 2;
			
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}
	
	
	static void update(int left, int right, int node, int target, long diff)
	{
		if(target < left || right < target)
		{
			return;
		}
		
		else {
			tree[node] += diff;
			if(left != right)
			{
				int mid = (left + right) / 2;
				update(left, mid, node * 2, target, diff);
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	static long queryBu(int queryLeft, int queryRight)
	{
		int left = S + queryLeft - 1;
		int right = (int) (S + queryRight - 1);
		
		long sum = 0;
		
		while(left <= right)
		{
			if(left % 2 == 1)
				sum += tree[left++];
			
			if(right % 2 == 0)
				sum += tree[right--];
			
			left /= 2;
			
			right /= 2;
		}
		
		return sum;
	}
	
	static void updateBU(int target, long value)
	{
		int index = S + target - 1;
		tree[index] = value;
		index /= 2;
		
		while(index > 0)
		{
			tree[index] = tree[index * 2] + tree[index * 2 + 1];
			
			index /= 2;
		}
	}
}
