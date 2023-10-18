package BOJ_10999;

import java.util.*;
import java.io.*;

public class BOJ_10999 {

	static int N, M, K;
	static long[] tree, lazy;
	static int S = 1;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		
		while(S < N)
		{
			S *= 2;
		}
		
		tree = new long[S * 2 + 1];
		lazy = new long[S * 2 + 1];
		
		M = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		init();
		
		
		for(int i = 0; i < M+K; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd, a, b;
			long sum;
			
			cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1)
			{
				a = Integer.parseInt(st.nextToken());
				
				b = Integer.parseInt(st.nextToken());
				
				sum = Long.parseLong(st.nextToken());
				
				update(1, S, 1, a, b, sum);
			}
			
			else {
				a = Integer.parseInt(st.nextToken());
				
				b = Integer.parseInt(st.nextToken());
				
				long subSum = query(1, S, 1, a, b);
				
				bw.write(subSum + " ");
				bw.newLine();
			}
			
		}
		
		bw.flush();
		bw.close();
	}
	
	// bottom-up���
	static void init()
	{
		for(int i = S; i < S + N; i++)
		{
			tree[i] = arr[(i - S) + 1];
		}
		
		for(int i = S - 1; i > 0; i--)
		{
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update_lazy(int left, int right, int node)
	{
		// ���� �ش� ��忡 lazy�� �ִٸ�
		if(lazy[node] != 0)
		{
			tree[node] += lazy[node] * (long)(right - left  + 1);
			
			// �ڽ� ���鿡�� lazy�� �����ش�.
			if(left != right)
			{
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			lazy[node] = 0;
		}
	}
	
	static long query(int left, int right, int node, int queryLeft, int queryRight)
	{
		update_lazy(left, right, node);
		
		if(right < queryLeft || queryRight < left)
			return 0;
		else if(queryLeft <= left && right <= queryRight)
		{
			return tree[node];
		}
		else {
			int mid = (left + right) / 2;
			
			return query(left, mid, node * 2 , queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

	static void update(int left, int right, int node, int updateLeft, int updateRight, long diff)
	{
		update_lazy(left, right, node);
		
		if(right < updateLeft || updateRight < left)
		{
			return;
		}
		// ���� ������ ���ԵǴ� ������ ���´ٸ� -> �迭 ���� ��ŭ �����ְ�, �ļտ� lazy�� �����ش�.
		if(updateLeft <= left && right <= updateRight)
		{
			tree[node] += (long)(right - left + 1) * diff;
			
			if(left != right)
			{
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			
			return;
		}
			
		int mid = (left + right) / 2;
			
		update(left, mid, node * 2, updateLeft, updateRight, diff);
			
		update(mid + 1, right, node * 2 + 1, updateLeft, updateRight, diff);
			
		// ������Ʈ�� �Ͼ ��, �θ� ��忡 �ݿ�
		
//		System.out.println(left + " " + right +  " " + node + " " + tree[node * 2] + " " + tree[node * 2 + 1]);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];	
	}
}
