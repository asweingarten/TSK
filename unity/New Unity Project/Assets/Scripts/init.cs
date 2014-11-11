using UnityEngine;
using UnityEditor;
using System.Collections;
using System;
using System.Collections.Generic;

class KeyboardComponent : Component {
	
	private IList rows = new List<KeyboardRowComponent>();
	private int rowCount;
	
	public KeyboardComponent(){
		this.rowCount = 0;
	}
	
	public void addRow(KeyboardRowComponent row){
		this.rows.Add(row);
		this.rowCount++;
	}
	
}

class KeyboardRowComponent : Component {
	
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

class Key {
	private TextMesh tm;
	private string id;
	
	private float keyWidth;
	private float keySpaceWidth;
	
	private GameObject cube;
	private TextMesh textMesh;
	
	public Key(GameObject parent, string id, float positionOffset){
		Debug.Log("CREATING OBJECT " + id);
		cube = GameObject.CreatePrimitive(PrimitiveType.Cube);
		cube.name = ("cube_new_" + id);
		cube.renderer.material.color = Color.red;
		
		float positionX = parent.transform.position.x + 3;
		float positionY = 11;
		float positionZ = parent.transform.position.z + 6 - (positionOffset);
		
		cube.transform.localPosition = new Vector3(positionX, positionY, positionZ);
		cube.renderer.material.color = Color.cyan;
		cube.transform.localScale = new Vector3(0.5f,0.5f,0.5f);
		cube.transform.parent = parent.transform;
		
		Font ArialFont = (Font)Resources.GetBuiltinResource (typeof(Font), "Arial.ttf");
		GameObject text = new GameObject();
		text.transform.parent = cube.transform;
		text.transform.localPosition = new Vector3(0.5f,0.3f,0.3f);
		text.transform.localRotation = Quaternion.Euler (90,90,0);
		text.transform.localScale = new Vector3(1, 1, 1);
		TextMesh textMesh = (TextMesh)text.AddComponent("TextMesh");
		textMesh.text = id;
		textMesh.color = Color.red;
		textMesh.font = ArialFont;
		textMesh.renderer.sharedMaterial = ArialFont.material;
		textMesh.fontSize = 12;
		
		this.tm = textMesh;
		this.id = id;
		this.keyWidth = 10;
		this.keySpaceWidth = 0;
	}
	
	public Key(TextMesh tm, string id, float keyWidth, float keySpaceWidth){
		this.tm = tm;
		this.id = id;
		this.keyWidth = keyWidth;
		this.keySpaceWidth = keySpaceWidth;
	}
	
	public void setColorBlue(){
		tm.color = new Color(0,0,100);
	}
	
	public void setColorGreen(){
		tm.color = new Color(0,100,0);
	}
	
	public void setColorRed(){
		tm.color = new Color(100,0,0);
	}
	
	public float getKeyWidth(){
		return this.keyWidth;
	}
	
	public float getKeySpaceWidth(){
		return this.keySpaceWidth;
	}
}

[InitializeOnLoad]
public class Init {
	static Init() {
		Debug.Log ("up and running");

		string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
			"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
			"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
			"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
			"space"};
		
		string[] row1Keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "="};
		string[] row2Keys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\"};
		
		GameObject user = GameObject.Find ("User");
		GameObject keyboardObj = new GameObject ();
		keyboardObj.name = "ScriptKeyboard";
		keyboardObj.AddComponent ("Keyboard");
		keyboardObj.transform.parent = user.transform;
		keyboardObj.transform.localPosition = new Vector3 (0, -1.8f, 0);
		
		GameObject keyboardRow1 = new GameObject ();
		keyboardRow1.transform.parent = keyboardObj.transform;
		keyboardRow1.transform.localPosition = new Vector3 (0, 0, 0);
		keyboardRow1.AddComponent ("KeyboardRowComponent");
		KeyboardRowComponent rowComponent = keyboardRow1.GetComponent<KeyboardRowComponent> ();
		rowComponent.setKeys (row1Keys);
		
		KeyboardComponent keyboard = keyboardObj.GetComponent<KeyboardComponent> ();
		
		int offset = 0;
		foreach (string s in rowComponent.getKeys()){
			new Key(keyboardRow1, s, offset);
			offset += 1;
		}
	}
}
