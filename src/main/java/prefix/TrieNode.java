package prefix;

import java.util.Map;
import java.util.HashMap;

public class TrieNode {
	protected Map<Character, TrieNode> map;
	protected boolean EOW;
	
	public TrieNode() {
		map = new HashMap<Character, TrieNode>();
		EOW = false;
	}
	
	public void add(String s) {
		add(s, 0);
	}
	
	protected void add(String s, int index) {
		if(index == s.length()) EOW = true;
		else {
			if(!map.containsKey(s.charAt(index))) map.put(s.charAt(index), new TrieNode());
			map.get(s.charAt(index)).add(s, index + 1);
		}
	}
	
	public boolean query(String s) {
		return query(s, 0);
	}
	
	private boolean query(String s, int index) {
		if(index == s.length()) return EOW;
		if(!map.containsKey(s.charAt(index))) return false;
		return map.get(s.charAt(index)).query(s, index + 1);
	}
}
