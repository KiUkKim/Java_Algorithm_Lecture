package Day06.BOJ_1722;

import java.util.*;
import java.io.*;

public class BOJ_1722 {

	static long[] arr;
	static int[] ans;
	static int N;
	static int K;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		visited = new boolean[N+1];
		
		// 해당 자리에 몇개의 수가 오는지 알아야한다.
		// 만약 1234에서 첫째자리에 1이 있는 경우 나올 수 있는 수는 3!이다.
		// 이런식으로 접근 생각
		// 그렇다면 여기에서
		// !로 값을 저장하게 된다면
		// 결국 [N-idx] = 경우의수가 나오게 된다.
		// 예를 들어 해당 자리가 첫째자리라면 나올 수 있는 경우의수는 6 * 4이다
		// 1번자리 6개가 들어왔다면 arr[3] = 6의 값이 나와야한다.
		ans = new int[N+1];
		
		arr = new long[N+1];
		
		arr[0] = 1;
		
		for(int i = 1; i <= N; i++)
		{
			arr[i] = arr[i-1] * i;
		}

		K = Integer.parseInt(st.nextToken());
		
		if(K == 1)
		{
			long count = Long.parseLong(st.nextToken());
			cnt_check(count);
			System.out.println(sb.toString());
		}
		
		else {
			
			for(int i = 1; i <= N; i++)
			{
				ans[i] = Integer.parseInt(st.nextToken());
			}
			
			num_check();
			
			System.out.println(sb.toString());
		}
	}
	
	static void cnt_check(long count)
	{
		// 첫째자리부터 넣어본다.
		for(int i = 1; i <= N; i++)
		{
			// 해당 자리에 1번부터 넣어본다.
			for(int j = 1; j <= N; j++)
			{
				// 이미 넣은 애라면 진행
				if(visited[j] == true)
					continue;
				
				// 해당 범위보다 현재 값이 크다면
				if(arr[N-i] < count)
				{
					count -= arr[N-i];
				}
				
				// 범위에 들어온경우
				// 해당 자리에 값을 넣어준다.
				else {
					ans[i] = j;
					visited[j] = true;
					break;
				}
			}
		}
		
		for(int i = 1; i <= N; i++)
		{
			sb.append(ans[i] + " ");
		}
	}
	
	static void num_check()
	{
		long count = 1;
		
		for(int i = 1; i <= N; i++)
		{
			for(int j = 1; j < ans[i]; j++)
			{
				// 만약 이미 넣은 숫자라면 안된다.
				if(visited[j] == true)
					continue;
				
				count += arr[N-i]; // 이제 해당 자리수에서 나올 수 있는 경우의수를 넣고 진행시킨다.
			}
			
			// 자리에 넣은 경우의수를 보고 처리해준다.
			visited[ans[i]] = true;
		}
		
		sb.append(count);
	}

}
