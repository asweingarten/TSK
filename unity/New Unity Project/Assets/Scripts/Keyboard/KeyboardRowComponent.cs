using UnityEngine;
using System.Collections.Generic;
using System;

public class KeyboardRowComponent : MonoBehaviour {
	
	private float rowHeight;
	private float rowSpaceHeight;
	
	private float totalRowWidth;
	private IList<String> keys = new List<String>();
	private int keyCount = 0;
	
	public KeyboardRowComponent(){
		this.rowHeight = 10;
		this.rowSpaceHeight = 0;
		this.totalRowWidth = 0;
	}
	
	public KeyboardRowComponent(float rowHeight, float rowSpaceHeight){
		this.rowHeight = rowHeight;
		this.rowSpaceHeight = rowSpaceHeight;
		this.totalRowWidth = 0;
	}
	
	public void setKeys(String[] keySet) {
		keys = keySet;
	}
	
	public IList<String> getKeys() {
		return keys;
	}
	
	//		public void addNewKey(GameObject parent, string id){
	//			keyCount++;
	//			Key newKey = new Key(parent, id, keyCount);
	//			keys.Add (newKey);
	//			totalRowWidth = totalRowWidth + newKey.getKeyWidth() + newKey.getKeySpaceWidth();
	//		}
}

