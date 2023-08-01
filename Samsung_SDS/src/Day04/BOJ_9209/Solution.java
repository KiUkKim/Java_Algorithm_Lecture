package Day04.BOJ_9209;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int dy[] = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int score[] = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	static int dx[] = {-1, 0, 1, 1, 1, 0, -1, -1};
	static StringBuilder sb;
	static int W, N;
	static char[][] map;
	static boolean[][] visited;
	
	static int cnt, ans_score;
	static String str;
	
	static TrieNode root = new TrieNode();
			
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C:\\Users\\KIUK\\Desktop\\Java_\\Samsung_SDS\\src\\Day04\\BOJ_9209\\input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		sb = new StringBuilder();
		
		W = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < W; i++)
		{
			insert(br.readLine());
		}
		
		st = new StringTokenizer(br.readLine());
		
		StringBuilder resultSb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		for(int n = 0; n < N; n++)
		{
			map = new char[4][4];
			visited = new boolean[4][4];
			
			cnt = 0;
			ans_score = 0;
			str = "";
			
			root.clearHit();
			
			for(int i = 0; i < 4; i++)
			{
				st = new StringTokenizer(br.readLine());
				
				String tmp = st.nextToken();

				System.out.println(tmp);
				
//				for(int j = 0; j < tmp.length(); j++)
//				{
//					map[i][j] = tmp.charAt(j);
//				}
			}
			
//			for(int i = 0; i < 4; i++)
//			{
//				for(int j = 0; j < 4; j++)
//				{
//					search(i, j, root);
//				}
//			}
//			
//			resultSb.append(ans_score + " " + str + " " + cnt + "\n");
//			
			if(n != 3)
			{
				st = new StringTokenizer(br.readLine());	
			}
		}
		
//		System.out.println(resultSb.toString());
	}
	
	static void search(int y, int x, TrieNode node)
	{
		// 1. 체크인
		visited[y][x] = true;
		sb.append(map[y][x]);
		
		// 2. 목적지에 도달하였는가? : 트라이에 단어끝, 새로운 단어
		if(node.isEnd && node.isHit == false)
		{
			node.isHit = true;
			
			// 정답 처리
			cnt++;
			
			ans_score += score[sb.toString().length() - 1];
			
			if(str.length() < sb.toString().length())
			{
				str = sb.toString();
			}
			
		}
		
		// 3. 연결된 곳을 순회 : 8방 // 멈추면 안된다 -> 뒤에 다른 단어가 존재할 수 있기 때문에
		for(int i = 0; i < 8; i++)
		{
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			// 4. 갈 수 있는가? : 맵영역 체크, 방문하지 않은 곳, 트라이 노드 체크
			if(ny < 0 || ny >= 4 || nx < 0 || nx >= 4)
				continue;
			
			if(visited[y][x] == true && node.hasChild(map[ny][nx]))
			{
				// 5. 간다.
				search(ny, nx, node.getChild(map[ny][nx]));
			}
		}
		

		// 6. 체크아웃
		visited[y][x] = false;
		sb.deleteCharAt(sb.length() - 1);
	}
	
	// print로 안찍혀서 디버깅 툴 활용하기!
	static void insert(String word)
	{
		TrieNode current = root;
		
		for(int i = 0; i < word.length(); i++)
		{
			int charIndex = word.charAt(i) - 'A';
			
			if(current.children[charIndex] == null)
			{
				current.children[charIndex] = new TrieNode();
			}
			
			current = current.children[charIndex];
		}
		
		current.isEnd = true;
	}
}

class TrieNode{
	TrieNode[] children = new TrieNode[26];
	
	boolean isEnd;
	boolean isHit;
	
	void clearHit()
	{
		isHit = false;
		
		for(int i = 0; i < children.length; i++)
		{
			if(children[i] != null)
				children[i].clearHit();
		}
	}
	
	boolean hasChild(char c)
	{
		return children[c - 'A'] != null;
	}
	
	TrieNode getChild(char c)
	{
		return children[c - 'A'];
	}
}
