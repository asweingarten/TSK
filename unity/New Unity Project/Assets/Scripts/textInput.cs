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
	
	public GameObject thingy;

	GameObject userAndKeyboard;


	class Keyboard {

		private IList rows = new List<KeyboardRow>();
		private int rowCount;

		public Keyboard(){
			this.rowCount = 0;
		}

		public void addNewRow(KeyboardRow newRow){
			this.rows.Add(newRow);
			this.rowCount++;
		}

	}

	class KeyboardRow {

		private float rowHeight;
		private float rowSpaceHeight;

		private float totalRowWidth;
		private IList keys = new List<Key>();
		private int keyCount = 0;

		public KeyboardRow(){
			this.rowHeight = 10;
			this.rowSpaceHeight = 0;
			this.totalRowWidth = 0;
		}

		public KeyboardRow(float rowHeight, float rowSpaceHeight){
			this.rowHeight = rowHeight;
			this.rowSpaceHeight = rowSpaceHeight;
			this.totalRowWidth = 0;
		}

		public void addNewKey(GameObject parent, string id){
			keyCount++;
			Key newKey = new Key(parent, id, keyCount);
			keys.Add (newKey);
			totalRowWidth = totalRowWidth + newKey.getKeyWidth() + newKey.getKeySpaceWidth();
		}
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

			cube.transform.position = new Vector3(positionX, positionY, positionZ);
			cube.transform.localScale = new Vector3(0.5f,0.05f,0.5f);
			cube.transform.parent = parent.transform;

			//textMesh = GameObject.CreatePrimitive(TextMesh);
			// cube.AddComponent("TextMesh");
			// cube.GetComponent<TextMesh>().text = id;


			this.tm = tm;
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

	// Use this for initialization
	void Start () {
		mouseX = Input.mousePosition.x;
		mouseY = Input.mousePosition.y;

		

		string[] keys = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",
						"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]", "\\",
						"a", "s", "d", "f", "g", "h", "j", "k", "l", ";", "'",
						"z", "x", "c", "v", "b", "n", "m", ",", ".", "/",
						"space"};

		string[] keys_row_1 = {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=",};
		
		Keyboard keyboard_instance = new Keyboard();
		KeyboardRow row_1 = new KeyboardRow();

		GameObject userAndKeyboard = GameObject.Find ("UserAndKeyboard");
		GameObject virtualKeybard = GameObject.Find ("Keyboard");
		foreach (string s in keys_row_1){
			row_1.addNewKey(virtualKeybard, s);
		}

		keyboard_instance.addNewRow(row_1);


		foreach (string s in keys){
			var tryToFindTextMesh = GameObject.Find ("key_" + s);
			var tm = (TextMesh)tryToFindTextMesh.GetComponent(typeof(TextMesh));
			// Key key = new Key(tm, ("key_" + s));

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
