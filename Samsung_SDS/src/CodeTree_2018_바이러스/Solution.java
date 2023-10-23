package CodeTree_2018_���̷���;

import java.util.*;
import java.io.*;

public class Solution {
	
	static List<Virus> virus;
	static final int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int N, M, K;
	static int[][] tmpMap;
	static int[][] initMap, virusMap;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(st.nextToken());
		
		initMap = new int[N+1][N+1];
		
		virus = new ArrayList<>();
		
		virusMap = new int[N][N];
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				virusMap[i][j] = 5;
			}
		}
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++)
			{
				initMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			
			int x = Integer.parseInt(st.nextToken());
			
			int age = Integer.parseInt(st.nextToken());
			
			y--;
			x--;
			
			virus.add(new Virus(y, x, age));
		}
		
		for(int i = 0; i < K; i++)
		{
			tmpMap = new int[N][N];
			
			// ����� �Դ´�.
			Eating();
			
			// ���� ��е��� �����ϰԵȴ�.
			ConvertToMap();
			
			// ���� 5�� ����� �������� 5ĭ�� ���̰�1�� ���̷����� ���Ľ�Ų��.
			BornVirus();
			
			// ����� �߰��� �ش�.
			PlusEat();
		}		
		
		bw.write(check() + " ");
		bw.newLine();
		bw.flush();
		bw.close();
	}
	
	static int check()
	{	
		return virus.size();
	}
	
	static void PlusEat()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				virusMap[i][j] += initMap[i][j];
			}
		}
	}
	
	static void Eating()
	{	
		int index = 0;
		
		Collections.sort(virus, new Comparator<Virus>() {
			@Override
			public int compare(Virus o1, Virus o2)
			{
				if(o1.y == o2.y && o1.x == o2.x)
					return o1.age - o2.age;
				else {
					if(o1.y == o2.y)
						return o1.x - o2.x;
					
					return o1.y - o2.y;
				}				
			}
		});
		
		while(!virus.isEmpty() && index < virus.size())
		{
			Virus cur = virus.get(index);
			
			// ���̷����� ���� �����ϴٸ�
			if(cur.age <= virusMap[cur.y][cur.x])
			{
				virusMap[cur.y][cur.x] -= cur.age;
				
				cur.age++;
				
				index++;
			}
			
			// ���̷����� ���� �Ұ� -> �״´�.
			else {
				int value = (cur.age) / 2;
				
				tmpMap[cur.y][cur.x] += value;
				
				virus.remove(index);
			}
		}
	}
	
	static void ConvertToMap()
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				virusMap[i][j] += tmpMap[i][j];
			}
		}
	}
	
	static void BornVirus()
	{
		int index = 0;
		
		int init_size = virus.size();
		
		while(index < init_size)
		{
			Virus cur = virus.get(index);
			
			index++;
			
			if(cur.age % 5 != 0)
				continue;
			
			for(int k = 0; k < 8; k++)
			{
				int ny = dy[k] + cur.y;
				int nx = dx[k] + cur.x;
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N)
					continue;
				
				virus.add(new Virus(ny, nx, 1));
			}
		}
	}

}

class Virus{
	int y;
	int x;
	int age;
	
	Virus(int y, int x, int age)
	{
		this.y = y;
		this.x = x;
		this.age = age;
	}
}
