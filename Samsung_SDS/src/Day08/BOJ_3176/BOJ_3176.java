package Day08.BOJ_3176;

import java.util.*;
import java.io.*;

public class BOJ_3176 {

	static int N, K;
	static int parent[][];
	static List<Pair>[] arr;
	static final int TMAX = 100000;
	static final int DMAX = 17;
	static int Depth[];
	static Pair value[][];
	static final int VALUE_INF = 1111111;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		Depth = new int[TMAX + 1];
		
		arr = new ArrayList[TMAX + 1];
		
		value = new Pair[TMAX + 1][DMAX + 1];
		
		for(int i = 0; i <= TMAX; i++)
		{
			arr[i] = new ArrayList<>();
		}
		
		parent = new int[TMAX + 1][DMAX + 1];
		
		for(int i = 0; i < N - 1; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a, b, w;
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			arr[a].add(new Pair(b, w));
			arr[b].add(new Pair(a, w));
		}
		
		Depth[1] = 1;
		
		MakeTree(1);
		
		for(int k = 0; k <= DMAX; k++)
		{
			for(int i = 2; i <= N; i++)
			{
				int Father = parent[i][k];
				
				if(Father > 0)
				{
					parent[i][k+1] = parent[Father][k];
					
					// 작은길이 업데이트
					value[i][k+1].ba = Math.min(value[i][k].ba, value[Father][k].ba);
					
					// 큰길이 업데이트
					value[i][k + 1].bb = Math.min(value[i][k].bb, value[Father][k].bb);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int a, b;
			
			a = Integer.parseInt(st.nextToken());
			
			b = Integer.parseInt(st.nextToken());
			
			int mini = VALUE_INF;
			
			int maxi = 0;
			
			if(Depth[a] < Depth[b])
			{
				int temp = b;
				b = a;
				a = temp;
			}
			
			int Diff = Depth[a] - Depth[b];
			
			int cnt = 0;
			
			while(Diff > 0)
			{
				if(Diff % 2 == 1)
				{
					mini = Math.min(mini,  value[a][cnt].ba);
					
					maxi = Math.max(maxi, value[a][cnt].bb);
					
					a = parent[a][cnt];
				}
				
				Diff /= 2;
				cnt++;
			}
			
			
			if(a != b)
			{
				for(int k = DMAX; k >= 0; k--)
				{
					if(parent[a][k] != parent[b][k])
					{
						// a점프
						mini = Math.min(mini, value[a][k].ba);
						
						maxi = Math.max(maxi, value[a][k].bb);
						
						a = parent[a][k];
						
						// b점프
						mini = Math.min(mini, value[b][k].ba);
						
						maxi = Math.max(maxi, value[b][k].bb);
						
						b = parent[b][k];
					}
				}
				
				mini = Math.min(mini, value[a][0].ba);
				maxi = Math.max(maxi, value[a][0].bb);
				maxi = Math.min(mini, value[b][0].ba);
				maxi = Math.max(maxi, value[b][0].bb);
			}
			bw.write(mini + " " + maxi + " ");
		}		
		
		bw.flush();
		bw.close();
	}
	
	static void MakeTree(int cur)
	{
		for(Pair child : arr[cur])
		{
			int town = child.ba;
			int BaseV = child.bb;
			
			if(Depth[town] != 0)
			{
				continue;
			}
			
			Depth[town] = Depth[cur] + 1;
			parent[town][0] = cur; // 2^0 부모 저장
			value[town][0] = new Pair(BaseV, BaseV); // 바로 부모까지 작은길이 = 큰 길이
			
			MakeTree(town);
		}
	}

}

class Pair{
	int ba;
	int bb;
	
	Pair(int ba, int bb)
	{
		this.ba = ba;
		this.bb = bb;
	}
}
