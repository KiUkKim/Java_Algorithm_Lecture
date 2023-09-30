package Day10.BOJ_7578;

import java.util.*;
import java.io.*;

public class BOJ_7578 {

	static int N;
	static int[] machine;
	static Map<Integer, Integer> machineBNum; // B�� �ӽ��� ������� �����ϱ� ����
	static int S = 1;
	static int[] tree;
	static long ans;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		machineBNum = new HashMap<>();
		
		while(S < N)
		{
			S *= 2;
		}

		machine = new int[N + 1];
		
		tree = new int[S * 2 + 1];
				
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i ++)
		{
			int a = Integer.parseInt(st.nextToken());
			
			machine[i] = a;
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++)
		{
			int a = Integer.parseInt(st.nextToken());
			
			// �� ��ȣ�� ���ؼ� machine�� �������̴�.
			machineBNum.put(a, i);
		}
		
		// tree�� �������� machineBNum[machineNum] + S�� �ɰ��̴�.
		
		// ���� �ش� ������ ������Ʈ�� ������ ���ؼ� ������� �ִ��� �����Ѵ�.
		for(int i = 0; i < N; i++)
		{
			// �� ��ȣ�� B���� ���� ������� �����ִ��� �ľ��� �� �ִ�.
			int machineNum = machineBNum.get(machine[i]) + 1;
			
			// ������Ʈ�� �����ش�.
			update(1, S, 1, machineNum, 1);
			
			// �츮�� ���� �ش� index + 1 ~ ������ ã�ƾ��Ѵ�. 
			ans += query(1, S, 1, machineNum + 1, S);
		}
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
	}
	
	static void update(int left, int right, int node, int target, int diff)
	{
		// �ش� ���� ���� ����
		if(right < target ||  target < left)
		{
			return;
		}
		else {
			// �ش� Ʈ���� ������Ʈ �����ش�.
			tree[node] += diff;
			
			// ���� ������Ʈ �� ������ �����ִٸ�
			if(left != right)
			{
				int mid = (left + right) / 2;
				
				// �����ڽ� ������Ʈ
				update(left, mid, node * 2, target, diff);
				
				update(mid + 1, right, node * 2 + 1, target, diff);
			}
		}
	}
	
	// �ִ�� 10^5 * 10^5�� ������.
	static long query(int left, int right, int node, int queryLeft, int queryRight)
	{
		if(queryRight < left || right < queryLeft)
		{
			return 0;
		}
		else if(queryLeft <= left && queryRight >= right)
		{
			return tree[node];
		}
		
		else {
			// �� ��� ��ȿ���� ���� ������ �ִ�.
			// Ȯ��������
			int mid = (left + right) / 2;
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
