package DAY07.BOJ_1922;

import java.util.*;
import java.io.*;

public class BOJ_1922_resolve {

	static PriorityQueue<Node2> pq;
	static int N, M;
	static int[] parent;
	static int ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		parent = new int[N + 1];
		
		Arrays.fill(parent, -1);
		
		pq = new PriorityQueue<>();
		
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from,to,weight;
			
			from = Integer.parseInt(st.nextToken());
			
			to = Integer.parseInt(st.nextToken());
			
			weight = Integer.parseInt(st.nextToken());
			
			pq.add(new Node2(from, to, weight));
		}
		
		while(true)
		{
			if(pq.isEmpty())
				break;
			
			Node2 cur = pq.poll();
			
			int na = cur.from;
			int nb = cur.to;
			int weight = cur.weight;
			
			// ���� �θ� ���ٸ� ����
			// ����Ŭ �߻�
			if(find(na) == find(nb))
				continue;
			
			ans += weight;
			
			union(na, nb);
			
			// MST�� �ִ� ������ ����
			if(parent[find(na)] == N-1)
			{
				break;
			}
		}
		
		for(int x : parent)
		{
			System.out.print(x + " ");
		}
		System.out.println();
		
		bw.write(ans + " ");
		bw.flush();
		bw.close();
		
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		// b���Դ� �� ���� ��Ŵ
		parent[b] += parent[a];
		
		// a�� �θ�� b�� �ִ´�.
		parent[a] = b;
	}

	static int find(int a)
	{
		if(parent[a] < 0)
			return a;
		
		return parent[a] = find(parent[a]);
	}
}

class Node2 implements Comparable<Node2>{
	int from;
	int to;
	int weight;
	
	Node2(int from, int to, int weight)
	{
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node2 o2)
	{
		// ������ġ �ּ�ȭ ��������
		return this.weight - o2.weight;
	}
}
