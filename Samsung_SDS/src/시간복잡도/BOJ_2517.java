package �ð����⵵;

import java.util.*;
import java.io.*;

public class BOJ_2517 {
	
	static int[] tree;
	static long[] arr;
	static int N;
	static List<Long> list;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new long[N + 1];
		
		list = new ArrayList<>();
		
		int S = 1;
		
		while(S < N)
		{
			S *= 2;
		}
		
		tree = new int[S * 2 + 1];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			arr[i] = Long.parseLong(st.nextToken());
			
			list.add(arr[i]);
		}
		
		Collections.sort(list);
		
//		for(int i : list)
//		{
//			System.out.print(i + " ");
//		}
//				
//		System.out.println();
//		
//		for(int i : arr)
//		{
//			System.out.print(i + " ");
//		}
//		
//		System.out.println();
		
		// ���� �ش� ������ �°� 
		
		// �̷��Եȴٸ� ������ ã�µ� O(logN)
		// update, query�ϴµ� (O(logN))
		// ���� O(NlogN)�� �ð��� ���ȴ�.
		for(int i = 0; i < N; i++)
		{
			int diff = lower_bound(list, arr[i]); // �ش� ������ ���°���� Ȯ���ϱ�
			
			update(1, S - 1, 1, diff, 1); // �ش� ��ȣ�� �ε����� ��� �������ֱ�
			
			// ���� ���� �Էµ� ������� query���� ������� �ش� �ϴ��� Ȯ���Ѵ�.
			
			int now_rank = query(1, S - 1, 1, 1, diff - 1); // 0 - 2��°������ ����� Ȯ���ϱ� -> ex ) ���� 0-2�������� 2���� ��ĥ���ִٸ� 3 - 2 = 1���̴�.
			bw.write(( (i + 1) - now_rank)  + " ");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	static int lower_bound(List<Long> a, Long target)
	{
		int left = 0;
		int right = a.size() - 1;
		int mid = 0;
		while(left <= right)
		{
			mid = (left + right ) / 2;
			
			if(a.get(mid) > target)
			{
				right = mid - 1;
			}
			
			else if(a.get(mid) < target)
			{
				left = mid + 1;
			}
			
			else {
				return mid + 1;
			}
		}
		
		return mid + 1;
	}
	
	static int query(int left, int right, int node, int queryLeft, int queryRight)
	{
		if(right < queryLeft || left > queryRight)
			return 0;
		
		// left <= query <= right
		else if(queryLeft <= left && right <= queryRight)
			return tree[node];
		
		else {
			int mid = (left + right ) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}
	
	static void update(int left, int right, int node, int target, int diff)
	{
		if(target < left || right < target)
			return;
		else {
			// �ش� tree�� �Է¹��� ��� ��� �־��ֱ�
			tree[node] += diff;
			
			// �� ������ �� �ִٸ�
			if(left != right)
			{
				int mid = (left + right) / 2;
				
				update(left, mid, node * 2, target, diff);
				
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}

}
