package DAY07.BOJ_1626;

import java.io.IOException;
import java.io.*;
import java.util.*;

public class BOJ_1626 {

	static final int MAX = 50000; // 2^16 > 500000
	static final int DMAX = 16;
	static final int INF = 123456789;
	
	static int [] parent;
	
	// MST�� ����
	static int V,E;
	static ArrayList<Edge> arr;
	
	// LCA�� ����
	static ArrayList<Pair>[] adj; 
	static int depth[];
	static int LCA_Parent[][];
	
	static Pair Biggest[][];
	
	static StringBuilder sb;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		
		E = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		
		for(int i = 0; i < E; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			
			int b = Integer.parseInt(st.nextToken());
			
			int c = Integer.parseInt(st.nextToken());
			
			arr.add(new Edge(a, b, c));
		}
		
		parent = new int[MAX + 1];
		
		adj = new ArrayList[MAX];
		
		Biggest = new Pair[MAX + 1][DMAX + 1];
		
		for(int i = 0; i < MAX; i++)
		{
			adj[i] = new ArrayList<>();
		}
		
		int MST = 0; // �ּ� ���д� Ʈ�� ����� ���
		int cnt = 0; // V-1���� Edge ������ MST�ϼ�
		
		// ������ Ʈ�� ������ ���ؼ� ����ġ�� �������� ����
		Collections.sort(arr, (o1, o2) -> o1.W - o2.W);
		
		// MST ����
		for(int i = 0; i < E; i++)
		{
			int a = arr.get(i).A;
			
			int b = arr.get(i).B;
			
			int cost = arr.get(i).W;
			
			int a_parent = find(a);
			
			int b_parent = find(b);
			
			if(a_parent != b_parent)
				union(a, b);
			
			MST += cost;
			
			arr.get(i).Used = true;
			
			adj[a].add(new Pair(b, arr.get(i).W));
			
			adj[b].add(new Pair(a, arr.get(i).W));
			
			cnt++;
			
			if(cnt == V - 1)
				break;
		}
		
		// ����ó��
		if(cnt != V-1 || E <= V - 1)
		{
			sb.append(-1 + '\n');
			
			return;
		}
		
		depth = new int[MAX + 1];
		LCA_Parent = new int[MAX + 1][DMAX + 1];
		
		// LCA����
		depth[1] = 1;
		
		// root�� 1�̶�� �����ϰ� 1���� �׾��ֱ�
		MakeDepth(1);
		
		for(int k = 0; k <= DMAX; k++)
		{
			for(int i = 1; i <= V; i++)
			{
				int Father = LCA_Parent[i][k];
				
				if(Father > 0 && LCA_Parent[Father][k] != 0)
				{
					
					int w1 = Biggest[i][k].first;
					int w2 = Biggest[i][k].second;
					
					int f1 = Biggest[Father][k].first;
					int f2 = Biggest[Father][k].second;
					
					if(w1 > f1)
					{
						Biggest[i][k+1] = new Pair(w1, Math.max(f1,  w2));
					}
					
					else if(w1 < f1)
					{
						Biggest[i][k+1] = new Pair(f1, Math.max(f2,  w1));
//						Biggest[i][k+1].first = f1;
//						
//						Biggest[i][k+1].second = Math.max(f2,  w1);
					}
					
					// w1 == f1�� ���
					else {
						Biggest[i][k+1] = new Pair(w1, Math.max(w2, f2));
					}
					
					LCA_Parent[i][k+1] = LCA_Parent[Father][k];
				}
			}
		}
		
		
		// �Ƚẻ Edge���� �Ẹ��
		int Plus = INF;
		
		for(int i = 0; i < E; i++)
		{
			if(arr.get(i).Used == true)
				continue;
			
			int t = GetPlustByLCA(arr.get(i));
			
			// ���� �� ������ ������� ��, �ش� ������ MST�� ���ų�, �Ұ����Ҷ�
			if(t == -1 || t == arr.get(i).W)
			{
				continue;
			}
			
			Plus = Math.min(Plus, t);
		}
		
		if(Plus == INF)
			sb.append(-1 + "\n");
		
		else {
			sb.append(MST + Plus + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void union(int a, int b)
	{
		a = find(a);
		b = find(b);
		
		
		if(a != b)
		{
			parent[b] = a;
		}
	}
	
	static int find(int x)
	{
		if(parent[x] == 0)
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static void MakeDepth(int cur)
	{
		for(Pair child : adj[cur])
		{
			if(depth[child.first] != 0)
				continue;
			
			depth[child.first] = depth[cur] + 1;
			LCA_Parent[child.first][0] = cur;
			
			// MST�� �����ϴ� edge ���� �ִ밪�� ����ϸ鼭 ����
			Biggest[child.first][0] = new Pair(child.second, - 1);
			
			MakeDepth(child.first);
		}
	}
	
	// �߰��� Edge(a->b) �� ����� cycle�� LCA�� �̿��ؼ� ã��, cycle �ȿ� ��ü ������ ���� ū �� ã�� �Լ�
	static int GetPlustByLCA(Edge EE)
	{
		int x = EE.A;
		int y = EE.B;
		int w = EE.W;
		
		// ��ü ������ ������ ���� ū ����ġ w�� �޶�� �Ѵ�.
		// -> ��ü ������ ������ ũ�Ⱑ ���ٸ� �� cycle���� ��ü�� MST�� �ι�°�� ���� MST�� �ƴ϶�, �ٸ� ������ ���� ���� MST�� �����ϰ� �ȴ�.
		
		int ret = - 1;
		
		// ���� ���� �������� �ϱ� ����
		if(depth[x] < depth[y])
		{
			int temp = y;
			
			y = x;
			
			x = temp;
		}
		
		int diff = depth[x] = depth[y];
		
		int cnt = 0;
		
		while(diff > 0)
		{
			if(diff % 2 == 1)
			{
				// �ش� ���� ��� �� ���� ū ��
				if(Biggest[x][cnt].first != w)
				{
					ret = Math.max(ret, Biggest[x][cnt].first);
				}
				
				// ��� �� �ι�° ū ����ġ
				else if(Biggest[x][cnt].second != -1)
				{
					ret = Math.max(ret,  Biggest[x][cnt].second);
				}
				
				x = LCA_Parent[x][cnt];
			}
			
			diff /= 2;
			cnt++;
		}
		
		if(x != y)
		{
			// ���� �� �Ÿ����� �����ش�.
			for(int i = DMAX; i>= 0; i--)
			{
				// ���� ������ �ٸ��ٸ�
				// ����ġ�� ����� ���ִ� ������ �ʿ��ϴ�.
				if(LCA_Parent[x][i] != LCA_Parent[y][i])
				{
					// ���� �ش� ��η� ���� �� �� �ִ밪
					if(Biggest[x][i].first != w)
					{
						ret = Math.max(ret, Biggest[x][i].first);
					}
					
					else if(Biggest[x][i].second != -1)
						ret = Math.max(ret, Biggest[x][i].second);
					
					
					if(Biggest[y][i].first != w)
					{
						ret = Math.max(ret,  Biggest[y][i].first);
					}
					
					else if(Biggest[y][i].second != w)
					{
						ret = Math.max(ret,  Biggest[y][i].second);
					}
					
					
					x = LCA_Parent[x][i];
					y = LCA_Parent[y][i];
				}
			}
			

			// LCA�� update��Ų ��λ��� ���� ū ������ Ȯ���Ѵ�.
			if(Biggest[x][0].first != w)
			{
				ret = Math.max(ret, Biggest[x][0].first);
			}
			
			else if(Biggest[x][0].second != -1)
				ret = Math.max(ret, Biggest[x][0].second);
			
			
			if(Biggest[y][0].first != w)
			{
				ret = Math.max(ret,  Biggest[y][0].first);
			}
			
			else if(Biggest[y][0].second != w)
			{
				ret = Math.max(ret,  Biggest[y][0].second);
			}
			
			x = LCA_Parent[x][0];
		}
		
		
		return ret;
	}
	

}

class Edge{
	int A;
	int B;
	int W;
	boolean Used = false;
	
	Edge(int A, int B, int W)
	{
		this.A = A;
		this.B = B;
		this.W = W;
		this.Used = false;
	}
}

class Pair{
	int first;
	int second;
	
	Pair(int first, int second)
	{
		this.first = first;
		this.second = second;
	}
}
