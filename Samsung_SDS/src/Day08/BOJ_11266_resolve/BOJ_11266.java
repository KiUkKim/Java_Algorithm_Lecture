package Day08.BOJ_11266_resolve;

import java.util.*;
import java.io.*;

public class BOJ_11266 {

	static int V, E;
	
	static int dfsCnt; // 방문번호를 체크하기 위함
	
	static int visitNum[];//방문번호를 체크하기 위함
	
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
			// 아직 해당 노드에 방문하지 않았다면
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
		visitNum[cur] = dfsCnt++; // 현재 방문번호를 기록해준다.
		
		// 이제 해당 노드에서 올라 갈 수 있는 가장 빠른 방문번호를 등록해줄것이다.
		int ret = visitNum[cur];
		
		// 자식트리 체크용 ( 루트일 경우 필요함)
		int childTreeCount = 0;
		
		// 해당 노드에서 탐색가능한 자식들을 확인한다.
		for(int child : adj[cur])
		{
			// 만약 탐색된 정점이라면,
			// 현재 노드에서 갈 수 있는 정점번호와, 해당 정점에서 갈 수 있는 번호를 비교한다.
			if(visitNum[child] != 0)
			{
				ret = Math.min(ret,  visitNum[child]);
				continue;
			}

			childTreeCount++;
			// 만약 탐색이 되지 않았다면
			// 자식 노드에서 갈 수 있는 곳을 확인 해줘야한다.
			int prev = dfs(child, false);
			

			// 만약 해당 자식이 가지고 있는 정점이 해당 정점을 방문하지 않고 더 빠른 곳으로 갈 수 없다면, 단절점이다.
			if(prev >= visitNum[cur] && !isRoot)
			{
				ans.add(cur);
			}
			
			ret = Math.min(ret, prev); // 해당 정점에서 갈 수 있는 정점을 나타낸다.
		}
		
		if(isRoot && childTreeCount >= 2)
		{
			ans.add(cur);
		}
		
		return ret;
	}

}
