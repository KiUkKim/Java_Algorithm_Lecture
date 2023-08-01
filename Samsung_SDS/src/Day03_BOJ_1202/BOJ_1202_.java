package Day03_BOJ_1202;

import java.io.FileInputStream;
import java.io.*;
import java.util.*;

public class BOJ_1202_ {

	static int N, K;
	static long ans;
	static Dia[] dia;
	static int[] bag; 
	static PriorityQueue<Dia> pq;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\DAY03_BOJ_1202\\input.txt"));

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		dia = new Dia[N];
		
		bag = new int[K];
		
		pq = new PriorityQueue<>();
		
		// 보석 정보 입력
		// 무게 , 가격
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			dia[i] = new Dia(w, v);
		}
		
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int w = Integer.parseInt(st.nextToken());
			
			bag[i] = w;
		}
		
		// 가방 정렬
		Arrays.sort(bag);
		
		// 보석 정렬
		Arrays.sort(dia, (o1, o2) -> o1.wei - o2.wei);
		
		// 이제부터 가능한 보석들을 pq에 넣어줄것이다.
		int BagPos = 0;
		int DiaPos = 0;
		
		while(true)
		{
			// 가방에 보석을 다 넣으면 끝
			if(BagPos >= K)
				break;
			
			int tmp_idx = -1;
			
			// 현재 넣을수 있는 다이아들을 넣는다.
			for(int i = DiaPos; i < N; i++)
			{
				// 넣을 수 있는 경우
				if(dia[i].wei <= bag[BagPos])
				{
					pq.add(new Dia(dia[i].wei, dia[i].value));
					
					tmp_idx = i;
				}
				
				// 넣을 수 없는 경우 해당 for문 종ㄹ
				else {
					break;
				}
			}
			
			if(tmp_idx != -1)
			{
				DiaPos = tmp_idx + 1; // 탐색이 끝난 다음것부터 보면 된다.		
			}
			
			// 한 개의 가방을 보고나면 최대 힙을 꺼내준다.
			if(!pq.isEmpty())
			{
				ans += pq.poll().value;
					
			}
			
			// 한번 탐색이 끝났으므로 다음 가방을 봐야한다.
			BagPos++;
		}
		
		System.out.println(ans);
	}

}

class Dia implements Comparable<Dia>{
	int wei;
	int value;
	
	Dia(int wei, int value)
	{
		this.wei = wei;
		this.value = value;
	}
	
	@Override
	public int compareTo(Dia o1)
	{
		// 오름차순 정렬
		return o1.value - this.value;
	}

	@Override
	public String toString() {
		return "Dia [wei=" + wei + ", value=" + value + "]";
	}
}
