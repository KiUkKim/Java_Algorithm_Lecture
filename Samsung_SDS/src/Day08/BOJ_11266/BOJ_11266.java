package Day08.BOJ_11266;

import java.util.*;
import java.io.*;

public class BOJ_11266 {

	static List<Integer>[] adj;
	static int V, E;
	
	static int dfsCnt;
	
	static int visitNum[]; // 방문 번호 체크를 위함
	
//	static List<Integer> ans;
	static Set<Integer> ans; // 중복된 값이 나올 수도 있는 경우에 대해서 생각해보자
	
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
		
		
		// 방문 번호 체크
		dfsCnt = 1;
		
		// 각 노드를 정점으로 방문을 시작한다.
		for(int i = 1; i <= V; i++)
		{
			// 아직 방문을 하지 않았다면
			if(visitNum[i] == 0)
			{
				// 루트로 방문을 시작한다.
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
		
		// 올라갈 수 있는 최소 방문번호를 확인한다.
		int ret = visitNum[cur];
		
		// 자식트리 체크용
		int childTreeCount = 0;
		
		// 해당 방문 노드를 기준으로 자식을 본다.
		for(int child : adj[cur])
		{
			// 이미 방문한 자식 노드이거나, 조상노드이다.
			if(visitNum[child] != 0)
			{
				ret = Math.min(ret,  visitNum[child]);
			}
			
			// 만약 아직 방문하지 않았다면 component에 등록해줘야한다.
			else {
				childTreeCount++;
				
				// 자식 노드로 dfs를 돌린다.
				int Ctree = DFS(child, false);
				
				// root 노드가 아닐 경우, 자식트리가 자신 위로 올라가지 못할 경우
				if(Ctree >= visitNum[cur] && !isRoot)
				{
					// 해당 노드는 단절점이 된다.
					ans.add(cur);
				}
				
				
				ret = Math.min(ret,  Ctree);
			}
		}
		
		// 만약 자신이 root노드이고 , 들어온 자식 sub tree가 2개 이상일 경우 -> 단절점이 된다.
		if(isRoot && childTreeCount >= 2)
		{
			ans.add(cur);
		}
		
		return ret;
	}

}
