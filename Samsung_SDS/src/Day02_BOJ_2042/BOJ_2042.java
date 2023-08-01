package Day02_BOJ_2042;

import java.io.*;
import java.util.*;

public class BOJ_2042 {

	static int N, K, M;
	static int S = 1;
	static long tree[];
	static long num[];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day02_BOJ_2042\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		num = new long[N];
		
		// S의 값 잡아주기
		while(S < N)
		{
			S *= 2;
		}
		
		for(int i = 0; i < N; i++)
		{
			num[i] = Long.parseLong(br.readLine());
		}
		
		tree = new long[S * 2];
		
		init();
		
		for(int i = 0; i < M + K; i++)
		{
			// 1일 경우 -> b번째 수를 c로 바꿈
			
			// 2일 경우 -> b번째 수부터 c까지 구함
			// 변경이 일어나는 구간
			// 초기에는 root node - 2, 3, 1, 
			
			st = new StringTokenizer(br.readLine());
			
			long a = Long.parseLong(st.nextToken());
			
			long b = Long.parseLong(st.nextToken());
			
			long c = Long.parseLong(st.nextToken());
			
			
			if(a == 1)
			{
				// b번째 수가 target
				long diff = c - tree[S + (int)b - 1]; 
						
				update(1, S, 1, b, diff);
			}
			
			else {
				sb.append(query(1, S, 1, b, c) + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	
	// bottomup방식8
	static void init()
	{
		// 가장 마지막 레벨 초기화
		for(int i = 0; i < N; i++)
		{
			tree[S + i] = num[i];
		}
		
		// bottom-up 방식
		for(int i = S - 1; i > 0; i--)
		{
			// 좌우의 값 더해주기
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void update(int left, int right, int node, long target, long diff)
	{
		// 만약 범위를 벗어난경우
		if(node >= tree.length)
			return;
		
		// 조건이 만약 연관이 없을 경우
		if(right < target || left > target)
		{
			return;
		}
		else {
			// 해당 하는 구간을 차이만큼 바꿔준다.
			tree[node] += diff;
			
			int mid = (left + right) / 2;
			
			// 왼쪽 구간을 탐색해준다.
			update(left, mid, node * 2, target, diff);
			
			// 오른쪽 구간을 탐색해준다.
			update(mid + 1, right, node * 2 + 1, target, diff);
		}		
	}
	
	static long query(int left, int right, int node, long queryLeft, long queryRight)
	{
		if(node >= tree.length)
			return 0;
		
		// 조건이 만약 연관이 없을 경우
		if(right < queryLeft || left > queryRight)
		{
			return 0;
		}
		// 조건이 연관이 있을 경우
		else if(left >= queryLeft && right <= queryRight) {
			
			return tree[node];
		}
		
		// 조건이 탐색해야된다면
		else {
			int mid = (left + right) / 2;
			// 좌우 탐색을 합친값
			return query(left, mid, node * 2, queryLeft, queryRight) + query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
		}
	}

}
