package Day08.BOJ_11266;

import java.util.*;
import java.io.*;

public class BOJ_11266 {

	static List<Integer>[] adj;
	static int V, E;
	
	static int dfsCnt;
	
	static int visitNum[]; // �湮 ��ȣ üũ�� ����
	
//	static List<Integer> ans;
	static Set<Integer> ans; // �ߺ��� ���� ���� ���� �ִ� ��쿡 ���ؼ� �����غ���
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		
		E = Integer.parseInt(st.nextToken());
		
		visitNum = new int[V + 2];
		
		adj = new ArrayList[V + 1];
		
		ans = new HashSet<>();
		
		for(int i = 0; i <= V; i++)
		{
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			adj[to].add(from);
		}
		
		
		// �湮 ��ȣ üũ
		dfsCnt = 1;
		
		// �� ��带 �������� �湮�� �����Ѵ�.
		for(int i = 1; i <= V; i++)
		{
			// ���� �湮�� ���� �ʾҴٸ�
			if(visitNum[i] == 0)
			{
				// ��Ʈ�� �湮�� �����Ѵ�.
				DFS(i, true);
			}
		}
		
//		sb.append(ans.size() + "\n");

		List<Integer> list = new ArrayList<>(ans);
		
		System.out.println(ans.size());
		
		Collections.sort(list);
		
		for(int x : list)
		{
			System.out.print(x + " ");
		}
		
	}
	
	static int DFS(int cur, boolean isRoot)
	{
		visitNum[cur] = dfsCnt;
		
		dfsCnt++;
		
		// �ö� �� �ִ� �ּ� �湮��ȣ�� Ȯ���Ѵ�.
		int ret = visitNum[cur];
		
		// �ڽ�Ʈ�� üũ��
		int childTreeCount = 0;
		
		// �ش� �湮 ��带 �������� �ڽ��� ����.
		for(int child : adj[cur])
		{
			// �̹� �湮�� �ڽ� ����̰ų�, �������̴�.
			if(visitNum[child] != 0)
			{
				ret = Math.min(ret,  visitNum[child]);
			}
			
			// ���� ���� �湮���� �ʾҴٸ� component�� ���������Ѵ�.
			else {
				childTreeCount++;
				
				// �ڽ� ���� dfs�� ������.
				int Ctree = DFS(child, false);
				
				// root ��尡 �ƴ� ���, �ڽ�Ʈ���� �ڽ� ���� �ö��� ���� ���
				if(Ctree >= visitNum[cur] && !isRoot)
				{
					// �ش� ���� �������� �ȴ�.
					ans.add(cur);
				}
				
				
				ret = Math.min(ret,  Ctree);
			}
		}
		
		// ���� �ڽ��� root����̰� , ���� �ڽ� sub tree�� 2�� �̻��� ��� -> �������� �ȴ�.
		if(isRoot && childTreeCount >= 2)
		{
			ans.add(cur);
		}
		
		return ret;
	}

}
