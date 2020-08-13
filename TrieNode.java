package quick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
	private Map<Character, TrieNode> map;
	private boolean EOW;
	
	public TrieNode() {
		map = new HashMap<Character, TrieNode>();
		EOW = false;
	}
	
	private void add(String s, int index) {
		if(index == s.length()) EOW = true;
		else {
			if(!map.containsKey(s.charAt(index))) map.put(s.charAt(index), new TrieNode());
			map.get(s.charAt(index)).add(s, index + 1);
		}
	}
	
	public void add(String s) {
		add(s, 0);
	}
	
	private boolean query(String s, int index) {
		if(index == s.length()) return EOW;
		if(!map.containsKey(s.charAt(index))) return false;
		return map.get(s.charAt(index)).query(s, index + 1);
	}
	
	public boolean query(String s) {
		return query(s, 0);
	}
	
	private List<String> prefixQuery(String pre, int index) {
		List<String> ans = new ArrayList<String>();
		if(index < pre.length()) {
			if(!map.containsKey(pre.charAt(index))) return ans;
			return map.get(pre.charAt(index)).prefixQuery(pre, index + 1);
		}
		for(char c: map.keySet()) {
			ans.addAll(map.get(c).prefixQuery(pre + c, index + 1));
		}
		if(EOW) ans.add(pre);
		Collections.sort(ans);
		return ans;
	}
	
	public List<String> prefixQuery(String pre) {
		return prefixQuery(pre, 0);
	}
}
