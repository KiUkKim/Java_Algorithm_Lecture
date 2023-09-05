package Day08.BOJ_11266_resolve;

import java.util.*;
import java.io.*;

public class BOJ_11266 {

	static int V, E;
	
	static int dfsCnt; // �湮��ȣ�� üũ�ϱ� ����
	
	static int visitNum[];//�湮��ȣ�� üũ�ϱ� ����
	
	static List<Integer>[] adj;
	
	static Set<Integer> ans;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
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
		
		dfsCnt = 1;
		
		for(int i = 1; i <= V; i++)
		{
			// ���� �ش� ��忡 �湮���� �ʾҴٸ�
			if(visitNum[i] == 0)
			{
				dfs(i, true);
			}
		}
		
		List<Integer> ANS = new ArrayList<>(ans);
		
		Collections.sort(ANS);
		
		bw.write(ANS.size() + " ");
		bw.newLine();
		
		for(int x : ANS)
		{
			bw.write(x + " ");
		}
		
		
		bw.flush();
		bw.close();
	}
	
	static int dfs(int cur, boolean isRoot)
	{
		visitNum[cur] = dfsCnt++; // ���� �湮��ȣ�� ������ش�.
		
		// ���� �ش� ��忡�� �ö� �� �� �ִ� ���� ���� �湮��ȣ�� ������ٰ��̴�.
		int ret = visitNum[cur];
		
		// �ڽ�Ʈ�� üũ�� ( ��Ʈ�� ��� �ʿ���)
		int childTreeCount = 0;
		
		// �ش� ��忡�� Ž�������� �ڽĵ��� Ȯ���Ѵ�.
		for(int child : adj[cur])
		{
			// ���� Ž���� �����̶��,
			// ���� ��忡�� �� �� �ִ� ������ȣ��, �ش� �������� �� �� �ִ� ��ȣ�� ���Ѵ�.
			if(visitNum[child] != 0)
			{
				ret = Math.min(ret,  visitNum[child]);
				continue;
			}

			childTreeCount++;
			// ���� Ž���� ���� �ʾҴٸ�
			// �ڽ� ��忡�� �� �� �ִ� ���� Ȯ�� ������Ѵ�.
			int prev = dfs(child, false);
			

			// ���� �ش� �ڽ��� ������ �ִ� ������ �ش� ������ �湮���� �ʰ� �� ���� ������ �� �� ���ٸ�, �������̴�.
			if(prev >= visitNum[cur] && !isRoot)
			{
				ans.add(cur);
			}
			
			ret = Math.min(ret, prev); // �ش� �������� �� �� �ִ� ������ ��Ÿ����.
		}
		
		if(isRoot && childTreeCount >= 2)
		{
			ans.add(cur);
		}
		
		return ret;
	}

}
