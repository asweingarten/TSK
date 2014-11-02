using UnityEngine;
using System.Collections;
using System;
using System.Collections.Generic;
//using System.Collections.Generic.List<T>;

public class textInput : MonoBehaviour {

	private float mouseX;
	private float mouseY;
	
	private float mouseXDelta;
	private float mouseYDelta;
	
	public float rotationY = 0f;
	public float rotationX = 0f;
	public float viewRange = 90.0f;
	
	public float mouseSensitivity = 10.0f;


	class Keyboard {
		public Keyboard(){

		}

	}

	class KeyboardRow {

		private int rowHeight;
		private int rowSpaceHeight;

		private int totalRowWidth;
		private IList keys = new List<Key>();

		public KeyboardRow(){
			this.rowHeight = 10;
			this.rowSpaceHeight = 0;
			this.totalRowWidth = 0;
		}

		public KeyboardRow(int rowHeight, int rowSpaceHeight){
			this.rowHeight = rowHeight;
			this.rowSpaceHeight = rowSpaceHeight;
			this.totalRowWidth = 0;
		}

		public void addNewKey(Key newKey){
			keys.Add (newKey);
			totalRowWidth = totalRowWidth + newKey.keyWidth + newKey.keySpaceWidth;
		}
	}

	class Key {
		private TextMesh tm;
		private string id;

		public int keyWidth;
		public int keySpaceWidth;

		public Key(TextMesh tm, string id){
			this.tm = tm;
			this.id = id;
			this.keyWidth = 10;
			this.keySpaceWidth = 0;
		}

		public Key(TextMesh tm, string id, int keyWidth, int keySpaceWidth){
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
	}

	Keyboard keyboard;

	// Use this for initialization
	void Start () {
		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;

		keyboard = new Keyboard();

		string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
						"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
						"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
						"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
						"space"};

		KeyboardRow row1 = new KeyboardRow();

		foreach (string s in keys){
			var tryToFindTextMesh = GameObject.Find ("key_" + s);
			var tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));
			Key key = new Key(tm, ("key_" + s));

		}

	}

	void HandleErrorEvent (object sender, EventArgs e)
	{
		Debug.Log(String.Format("DANGER, DANGER WILL ROBINSON: {0}", Input.mousePosition.y));
	}
	
	// Update is called once per frame
	void Update () {
		rotationX += Input.GetAxis ("Mouse X") * 1;
		
		// rotationY -= Input.GetAxis ("Mouse Y") * mouseSensitivity;
		// rotationY = Mathf.Clamp (rotationY, -viewRange, viewRange);
		// Camera.main.transform.Rotate (-rotationY, 0, 0);
		
		transform.localRotation = Quaternion.Euler (90 , rotationX,0);

		string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
						"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
						"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
						"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
						"space"};
		foreach (string s in keys){
			var tryToFindTextMesh = GameObject.Find ("key_" + s);
			var tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));
			if (Input.GetKeyDown (s)) {
				tm.color = new Color(100,0,0);
			}
			if (Input.GetKeyUp (s)) {
				tm.color = new Color(0,100,0);
			}
		}
	}
}
