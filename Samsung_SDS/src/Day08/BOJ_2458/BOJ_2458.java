package Day08.BOJ_2458;

import java.util.*;
import java.io.*;

public class BOJ_2458 {

	static int dist[][];
	static int ans;
	static final int INF = 987654321;
	static int N, M;
	static int connect[];
	static boolean[][] check_;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1][N+1];
		
		check_ = new boolean[N+1][N+1];
		
		connect = new int[N+1];
		
		// 높은 값으로 둔다.
		for(int i = 0; i <= N; i++)
		{
			for(int j = 0; j <= N; j++)
			{
				dist[i][j] = INF;
			}
		}
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int from, to;
			
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			
			check_[from][to] = true;
		}
		
		// floyd-washall -> 한 노드에서 시작해서 다른 노드로 갈 수 있냐?에 대한 답을 할 수 있다고 생각.
		for(int k = 1; k <= N; k++) // 원하는 A - >K , k-> B 중간지점
		{
			dist[k][k] = 0; // 자기 자신은 0이다.
			
			// A - > 중간지점, 중간지점 -> B비교한다.
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					// 만약 중간 지점을 통해서 i -> K, K -> J의 경로가 있다면 우리는 I -> K -> J를 다 들려볼 수 있음을 의미한다.
					if(check_[i][k] && check_[k][j])
					{
						check_[i][j] = true;
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j <= N; j++)
			{
				// 이 의미는 앞뒤로 연결된 학생 수를 의미한다.
				if(check_[i][j] || check_[j][i])
				{
					connect[i]++;
				}
			}
		}
		
		for(int x : connect)
		{
			// 자기 자신을 제외하고 나머지 N-1과 비교가 가능하다면 -> 그 학생이 몇번쨰인지 안다는 의미이다.
			if(x == N - 1)
			{
				ans++;
			}
		}

		System.out.println(ans);
	}

}
