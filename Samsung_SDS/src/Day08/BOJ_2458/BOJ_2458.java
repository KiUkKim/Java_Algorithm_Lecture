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
		
		// ���� ������ �д�.
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
		
		// floyd-washall -> �� ��忡�� �����ؼ� �ٸ� ���� �� �� �ֳ�?�� ���� ���� �� �� �ִٰ� ����.
		for(int k = 1; k <= N; k++) // ���ϴ� A - >K , k-> B �߰�����
		{
			dist[k][k] = 0; // �ڱ� �ڽ��� 0�̴�.
			
			// A - > �߰�����, �߰����� -> B���Ѵ�.
			for(int i = 1; i <= N; i++)
			{
				for(int j = 1; j <= N; j++)
				{
					// ���� �߰� ������ ���ؼ� i -> K, K -> J�� ��ΰ� �ִٸ� �츮�� I -> K -> J�� �� ����� �� ������ �ǹ��Ѵ�.
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
				// �� �ǹ̴� �յڷ� ����� �л� ���� �ǹ��Ѵ�.
				if(check_[i][j] || check_[j][i])
				{
					connect[i]++;
				}
			}
		}
		
		for(int x : connect)
		{
			// �ڱ� �ڽ��� �����ϰ� ������ N-1�� �񱳰� �����ϴٸ� -> �� �л��� ��������� �ȴٴ� �ǹ��̴�.
			if(x == N - 1)
			{
				ans++;
			}
		}

		System.out.println(ans);
	}

}
