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
		
		// ���� ���� �Է�
		// ���� , ����
		
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
		
		// ���� ����
		Arrays.sort(bag);
		
		// ���� ����
		Arrays.sort(dia, (o1, o2) -> o1.wei - o2.wei);
		
		// �������� ������ �������� pq�� �־��ٰ��̴�.
		int BagPos = 0;
		int DiaPos = 0;
		
		while(true)
		{
			// ���濡 ������ �� ������ ��
			if(BagPos >= K)
				break;
			
			int tmp_idx = -1;
			
			// ���� ������ �ִ� ���̾Ƶ��� �ִ´�.
			for(int i = DiaPos; i < N; i++)
			{
				// ���� �� �ִ� ���
				if(dia[i].wei <= bag[BagPos])
				{
					pq.add(new Dia(dia[i].wei, dia[i].value));
					
					tmp_idx = i;
				}
				
				// ���� �� ���� ��� �ش� for�� ����
				else {
					break;
				}
			}
			
			if(tmp_idx != -1)
			{
				DiaPos = tmp_idx + 1; // Ž���� ���� �����ͺ��� ���� �ȴ�.		
			}
			
			// �� ���� ������ ������ �ִ� ���� �����ش�.
			if(!pq.isEmpty())
			{
				ans += pq.poll().value;
					
			}
			
			// �ѹ� Ž���� �������Ƿ� ���� ������ �����Ѵ�.
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
		// �������� ����
		return o1.value - this.value;
	}

	@Override
	public String toString() {
		return "Dia [wei=" + wei + ", value=" + value + "]";
	}
}
